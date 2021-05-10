import express from "express";
import cors from "cors"; //local서버 환경에서 API 연동을 위해서 cors 라이브러리를 설치했습니다.
import bodyParser from "body-parser"; // 요청을 parser해주는 라이브러리
import multer from "multer"; // 파일 업로드 라이브러리
import path from "path"; // 경로 관련 라이브러리
import { getRows, getResult } from "./db"; // db.js에서 설명완료

const app = express();
app.use(cors());
const port = process.env.PORT || 3001; // 포트 3001
app.use(bodyParser.json());
app.use(express.static("public")); // 사용자가 접근가능한 폴더를 public 폴더로 설정했습니다.

const upload = multer({
  // 파일업로드 설정 사항입니다.
  storage: multer.diskStorage({
    destination: function (req, file, cb) {
      cb(null, "public/uploads/"); // 이경로로 저장됩니다.
    },
    filename: function (req, file, cb) {
      cb(null, new Date().valueOf() + path.extname(file.originalname)); // 파일이름을 업로드한시각을 기준으로 Timestamp로 변경해서 저장하는 역할을 합니다.
    },
  }),
});

app.post("/api/login", (req, res) => {
  // /api/login 주소에 post 요청이 날라왓을때 동작합니다.
  const { userId, userPw } = req.body.formData; // react단으로부터 id와 pw를 받아옵니다.
  getRows(
    `SELECT *FROM MEMBER WHERE ID = '${userId}' AND PASSWORD = '${userPw}'` // ID와 비밀번호를 확인하는 쿼리문을 날립니다.
  ).then((result) => {
    if (result.length < 1) {
      // 길이가 1 미만이면, 일치하는게 1개도 없다는 것이니, 회원가입해달라는 메세지를 뿌려주면 됩니다.
      res.json({ success: false, msg: "회원가입을 해주세요." });
    } else {
      // 그 반대로는 계정이 존재한다는 소리이니, 환영한다는 메세지를 뿌려줍니다.

      res.json({ success: true, msg: `${result[0][1]}님 환영합니다.` });
    }
    res.end();
  });
  return;
});

app.post("/api/register", upload.single("profile"), (req, res) => {
  // /api/register 주소에 post요청이 날라왔을때 동작합니다. 다른점은, 프로필사진 업로드때문에, profile은 req.file로 받습니다.
  const { userId, userPassword, userMail, userPhone, userName, userFullAddress, userSubAddress } = req.body; // 일반 텍스트들은 body에서 똑같이 받습니다.
  const filename = req.file.filename; // multer에서 req.file로 받아줍니다. db에 파일경로를 저장하기 위해서, filename을 받아옵니다.
  getResult(
    //받은 정보로 회원가입 쿼리를 날립니다.
    `INSERT INTO MEMBER (ID, PASSWORD, MAIL, PHONE, PROFILE, NAME, FULLADDRESS, SUBADDRESS) VALUES ('${userId}', '${userPassword}', '${userMail}', '${userPhone}', '/uploads/${filename}', '${userName}', '${userFullAddress}', '${userSubAddress}')`
  ).then((result) => {
    // 성공하면, 회원가입이 됬다는 메세지를 뿌려줍니다.
    res.json({ success: true, msg: "회원 가입이 되셨습니다." });
  });
});

app.get("/api/users/:page*?", (req, res) => {
  // /api/users/:page get으로 요청하면 작동합니다. 다른점은, :page*? 이부분인데, URL의 Params를 받아옵니다. ex) /api/users/1
  getRows(`SELECT COUNT(*) FROM MEMBER`).then((result) => {
    let page = req.params.page ? req.params.page : 1; // params가 없다면, 1로 자동 세팅하게 해두었습니다.
    let list = 10; // 보여줄 게시글 수
    let startRow = (page - 1) * list + 1; // 1페이지일시 1번째 row부터, 2페이지일시 11번재 row부터, 3페이지일시 21번째 row부터
    let endRow = page * list; // 1페이지일시, 10번째 row까지. 2페이지일시 20번째 row까지. 3페이지일시 30번째 row까지. 페이지가 달라질수록 값이 변합니다.
    let totalRow = result[0][0]; // 전체 Row의 갯수
    let totalPage = Math.ceil(totalRow / list); // 30개면 3.
    // if (endRow > totalPage) endRow = totalPage; // 전체Rows가 26개인데, endRow가 30으로 계산됬을시, 26으로 숫자를 같게 맞춰줍니다
    getRows(
      // 위의 변수들을 활용하여, :page 값에 따라 다른 데이터들이 조회됩니다.
      `
      SELECT *
      FROM
        (
          SELECT /*+ INDEX(T1 PK1) */
              T1.PK,T1.ID, T1.NAME, T1.MAIL, T1.PHONE, T1.PROFILE, T1.FULLADDRESS, T1.SUBADDRESS
          FROM
            MEMBER T1
          WHERE
              ROWNUM <= ${endRow} ORDER BY T1.PK ASC
        )
      WHERE
          ${startRow} <= ROWNUM
      `
    ).then((result) => {
      res.json({ result, totalPage }); // 회원정보들과, 총 페이지의 수를 Return 합니다.
    });
  });
});

app.get("/api/user/:idx", (req, res) => {
  // idx는 DB에서 고유한 키입니다. 1명의 유저의 정보를 조회할때 사용합니다.
  const idx = req.params.idx;
  getRows(
    // 조회 쿼리 동일
    `SELECT ID, MAIL, PHONE, PROFILE, NAME FROM MEMBER WHERE PK = ${idx}`
  ).then((result) => {
    res.json(result); // 결과 리턴
  });
});

app.get("/api/user/update/:idx", (req, res) => {
  const idx = req.params.idx;
  getRows(`SELECT * FROM MEMBER WHERE PK = ${idx}`).then((result) => {
    res.json(result);
  });
});

app.get("/api/user/search/:id", (req, res) => {
  // /api/user/search/:id 이부분은, 이름을 URL Params로 받아와 검색해서 일부분일치하는 결과를 알려줍니다.
  const id = req.params.id;
  var sql = `SELECT PK, NAME, ID, MAIL, PHONE, PROFILE FROM MEMBER WHERE NAME LIKE '%${id}%'`; // ${id}% 는, 예를들어, "박관" 이라고 검색했을때, "박관" 으로 시작하는 모든것을 조회합니다.

  getRows(sql).then((result) => {
    res.json(result); // 결과리턴
  });
});

app.post("/api/user/update/:idx", upload.single("profile"), (req, res) => {
  // 이 부분은 회원정보 수정을 할때의 API입니다. 회원가입과 동일하게 값을 받아와서 그냥 치환시켜줍니다.
  const {
    userId,
    userPassword,
    userMail,
    userPhone,
    userName,
    profile = null,
    userFullAddress,
    userSubAddress
  } = req.body;
  const idx = req.params.idx;
  let filename = null;
  if (req.file) {
    filename = `/uploads/${req.file.filename}`;
  } else {
    filename = profile;
  }

  getResult(
    // REGISTER과 다른점은 치환!
    `UPDATE MEMBER 
    SET ID = '${userId}',PASSWORD = '${userPassword}', PROFILE = '${filename}', MAIL = '${userMail}', PHONE = '${userPhone}', NAME = '${userName}', FULLADDRESS = '${userFullAddress}', SUBADDRESS = '${userSubAddress}'
        WHERE PK = ${idx}
        `
  ).then((result) => {
    res.json({ success: true, msg: "회원 수정이 되었습니다." });
  });
});

app.post("/api/user/delete/", (req, res) => {
  // 회원삭제 부분입니다. indexes string을 받아옵니다.
  const indexString = req.body.indexes;

  getResult(`DELETE FROM MEMBER WHERE PK In (${indexString})`).then(
    // In 키워드로 해당 인덱스가 있는 rows를 delete 시켜줍니다.
    (result) => {
      res.json({ success: true, msg : "회원 정보 삭제" });
    }
  );
});


//아이디를 찾아오는 API
app.post("/api/findid", (req, res) => {
  let userName = req.body.userName;
  let userPhone = req.body.userPhone;
  getRows(`SELECT ID FROM MEMBER WHERE NAME = '${userName}' AND PHONE = '${userPhone}'`).then((result) => {
    res.json(result);
  });
})

//비밀번호를 찾아오는 API
app.post("/api/findpw", (req, res) => {
  let userName = req.body.userName;
  let userPhone = req.body.userPhone;
  let userId = req.body.userId;
  getRows(`SELECT PASSWORD FROM MEMBER WHERE NAME = '${userName}' AND PHONE = '${userPhone}' AND ID = '${userId}'`).then((result) => {
    res.json(result);
  });
})

//네이버 로그인시, 자체 서버 DB 저장
app.post("/api/naverlogin", (req, res) => {
  let email = req.body.email;
  let name = req.body.name;

  getRows(`SELECT ID FROM MEMBER WHERE NAME = '${name}' AND MAIL = '${email}'`).then((result) => {
    if(result.length < 1) {
      getResult(`INSERT INTO MEMBER (MAIL, NAME, PASSWORD, ID) VALUES ('${email}', '${name}', 'naver', 'naver')`).then(result => {
        res.json({success: true, msg: `${name}님 가입완료되었습니다.`});
      })
    }else {
      res.json({success:true, msg: `${name}님 환영합니다.`});
    }
    
  });
})


app.listen(port, () => {
  // 서버 키는 코드
  console.log(`express is running on ${port}`);
});
