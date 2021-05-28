sqlplus System/System1234

show user

-- 계정, 권한 생성
CREATE USER spring IDENTIFIED BY spring;
GRANT CONNECT, RESOURCE TO spring;
GRANT ALTER SESSION TO spring;

-- spring 계정으로 연결
connect spring/spring

show user

-- 게시글 댓글 시퀀스
DROP SEQUENCE USER_REPLY_NO;
CREATE SEQUENCE USER_REPLY_NO;

-- 게시글 시퀀스
DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ
INCREMENT BY 1 --증감숫자
START WITH 1  --시작숫자
;


--회원정보
DROP TABLE TB_COM_USER CASCADE CONSTRAINT;
CREATE TABLE TB_COM_USER(
  ID VARCHAR2(20),                  		-- 아이디
  PW VARCHAR2(20),                   		-- 패스워드
  EMAIL VARCHAR2(50),                    		-- 이메일
  NAME VARCHAR2(30),                       	-- 이름
  BIRTH VARCHAR2(20),                     	-- 생일
  PHONE_NUM VARCHAR2(20),                    	-- 전화번호
  ADDRESS VARCHAR2(100),                  	-- 주소
  POST_NUM VARCHAR2(20),                   	-- 우편번호
  ENT_DATE DATE DEFAULT SYSDATE,                -- 회원가입일
  GRADE VARCHAR2(10),         	-- 회원등급
  CONSTRAINT TB_COM_USER_PK PRIMARY KEY(ID)
);

--게시판정보
DROP TABLE TB_BOARD CASCADE CONSTRAINT;
CREATE TABLE TB_BOARD(
  BOARD_NO VARCHAR2(30),             		-- 게시글번호 seq
  ID VARCHAR2(20),            			-- 아이디 writer
  BOARD_TITLE VARCHAR2(100),        		-- 게시글제목 title
  BOARD_CONTENTS VARCHAR2(1000),     	-- 게시글내용 content
  BOARD_DATE DATE DEFAULT SYSDATE,          	-- 게시글작성날짜 regdate
  BOARD_HITS NUMBER DEFAULT 0,     	-- 조회수  cnt
  CONSTRAINT TB_BOARD_PK PRIMARY KEY(BOARD_NO),
  CONSTRAINT TB_BOARD_FK_USER FOREIGN KEY(ID) REFERENCES TB_COM_USER(ID) ON DELETE CASCADE -- 회원탈퇴를 하면 해당 회원이 쓴 게시글도 삭제
);


--댓글정보
DROP TABLE TB_REPLY CASCADE CONSTRAINT;
CREATE TABLE TB_REPLY(
  BOARD_NO VARCHAR2(30),             		-- 게시글번호
  ID VARCHAR2(20),            			-- 유저코드
  USER_REPLY_NO NUMBER(3),           		-- 댓글일련번호
  USER_REPLY VARCHAR2(500),         		-- 댓글내용
  REPLY_DATE DATE DEFAULT SYSDATE,		-- 댓글날짜
  CONSTRAINT TB_REPLY_PK PRIMARY KEY(USER_REPLY_NO)
);

--댓글 좋아요/싫어요
DROP TABLE CNT CASCADE CONSTRAINT;
CREATE TABLE CNT(
  ID VARCHAR2(20),            		-- 유저코드
  USER_REPLY_NO NUMBER(3),           	-- 댓글일련번호
  GCNT NUMBER(3) DEFAULT 0,		-- 좋아요
  BCNT NUMBER(3) DEFAULT 0		-- 싫어요
);

-- 공지사항
DROP TABLE NOTICE CASCADE CONSTRAINT;
CREATE TABLE NOTICE(
  NOTICE_NO VARCHAR2(30),             -- 공지사항번호
  NOTICE_TITLE VARCHAR2(1000),        -- 공지사항제목
  NOTICE_CONTENTS VARCHAR2(4000),     -- 공지사항내용
  NOTICE_DATE DATE DEFAULT SYSDATE,           -- 공지사항작성날짜
  NOTICE_HITS NUMBER DEFAULT 0,     -- 조회수
  CONSTRAINT TB_NOTICE_PK PRIMARY KEY(NOTICE_NO)
);

--유저파일테이블
create table user_file(
	file_idx number,
	id varchar2(20),
	origin_file_name varchar2(400),
	file_name varchar2(400),
	file_path varchar2(400),
	constraint user_file_fk foreign key(id) references tb_com_user(id)
);
commit;



-----------------------------------------------------------------------------------------------------------------------

* tb_board테이블 시퀀스 입력
insert into tb_board(BOARD_NO ,id, board_title) VALUES(BOARD_SEQ.nextval ,'tt', 'test'); 

* 테이블 삭제, 생성후 테이블 목록에 BIN$... 으로 시작하는 테이블이 생긴 경우
SQL > PURGE RECYCLEBIN;