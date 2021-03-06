const jwt = require('jsonwebtoken') // 인증 모듈
const oracledb = require('oracledb') // 오라클 모듈
const config = require('../../../config') // 설정파일
oracledb.autoCommit = true // DB 설정

const getRows = (sql) => {
	// 데이터를 수정하는게 아닌,  받기만 할때 사용하는 함수입니다.
	return new Promise((resolve, reject) => {
	  oracledb.getConnection(
		// 오라클과 커넥션
		{
		  user: "REACT", // DB의 USER 이름
		  password: "REACT", // DB의 비밀번호
		  connectString: "orcl", // DB의 이름
		},
		function (err, connection) {
		  if (err) {
			// 에러가났을때
			console.error(err.message); // 콘솔로 메세지를 뿌려줍니다.
			reject(err); // 동일
			return;
		  } else {
			connection.execute(sql).then((res) => {
			  //매개변수로 받은 sql을 실행하고, 결과를 return합니다.
			  resolve(res.rows); // 리턴
			});
		  }
		}
	  );
	});
  };
  
const getResult = (sql) => {
	// 데이터를 추가, 입력, 삭제 등 "수정"할때 사용하는 함수 입니다.
	return new Promise((resolve, reject) => {
	  oracledb.getConnection(
		// 위와동일
		{
		  user: "REACT",
		  password: "REACT",
		  connectString: "orcl",
		},
		function (err, connection) {
		  // 위와동일
		  if (err) {
			console.error(err.message);
			reject(err);
			return;
		  } else {
			connection.execute(sql).then((res) => {
			  // 위와 동일
			  resolve(res);
			});
		  }
		}
	  );
	});
  };

  exports.register = async (req, res) => {
	// /api/register 주소에 post요청이 날라왔을때 동작합니다. 다른점은, 프로필사진 업로드때문에, profile은 req.file로 받습니다.
	const { userId, userPassword, userMail, userPhone, userName, userFullAddress, userSubAddress } = req.body; // 일반 텍스트들은 body에서 똑같이 받습니다.
	const filename = req.file.filename; // multer에서 req.file로 받아줍니다. db에 파일경로를 저장하기 위해서, filename을 받아옵니다.
	
	const db = await oracledb.getConnection(config.db)
	const query = `INSERT INTO MEMBER (PK, ID, PASSWORD, MAIL, PHONE, PROFILE, NAME, FULLADDRESS, SUBADDRESS) VALUES (MEMBER_SEQ.nextval, '${userId}', '${userPassword}', '${userMail}', '${userPhone}', '/uploads/${filename}', '${userName}', '${userFullAddress}', '${userSubAddress}')`
	const result = await db.execute(query)
	db.close() // db close
  };

// # APIs
exports.login = async (req, res, next) => {
	const { id, password } = req.body // 바디에서 데이터를 읽어옴
	// 예외처리
	if(!id || !password) return res.status(422).json({ msg: '필수로 요구되는 항목입니다' })

	// Oracle DB에 연결
	const db = await oracledb.getConnection(config.db)
	const query = `SELECT * FROM MEMBER WHERE ID='${id}' AND PASSWORD='${password}'` // 사용자 정보 체크
	const result = await db.execute(query)
	db.close() // db close

	// 계정이 올바르지 않은 경우
	if(!result.rows.length) return res.status(400).json({ msg: '일치하는 계정이 없습니다' })
	
	// 인증정보 생성
	const user = result.rows[0]
	const today = new Date()
  const exp = new Date(today)
  exp.setDate(today.getDate() + 60)

	// 토큰 생성
  const token = jwt.sign({
		pk: user[0],
		id: user[1],
		name: user[3],
		exp: parseInt(exp.getTime() / 1000),
  }, config.secret)

	// 결과 리턴
	return res.json({
		msg: '로그인하였습니다',
		token, name: user[3],
	})
}

exports.naverlogin = async(req, res) => {
  let email = req.body.email;
  let name = req.body.name;

  getRows(`SELECT ID FROM MEMBER WHERE NAME = '${name}' AND MAIL = '${email}'`).then((result) => {
    if(result.length < 1) {
      getResult(`INSERT INTO MEMBER (MAIL, NAME, PASSWORD, ID) VALUES ('${email}', '${name}', 'naver', 'naver')`).then(result => {
		res.json({success: true, msg: `${name}님 가입완료되었습니다.`});
	})
	console.log('가입완료')
    }else {
	  res.json({success:true, msg: `${name}님 환영합니다.`});
	  console.log('환영합니다')
	}
  });
}
