
-- 미니 프로젝트 프로젝트 Database 계정 및 권한, Table, Column, Value, Index, Trigger 등 생성 SQL 구문 예시

-- OracleDB 한 줄 주석 처리 --

-- OracleDB 여러 줄의 코드에 주석을 붙일 때는 주석 처리할 부분의 처음과 끝에 ‘/*’와 ‘*/’를 붙여줍니다.

-- MysqlDB 한 줄 주석 처리 #

-- MysqlDB 여러 줄의 코드에 주석을 붙일 때는 주석 처리할 부분의 처음과 끝에 ‘/*’와 ‘*/’를 붙여줍니다.

-- 제목(예시) : 미니프로젝트 개발 0팀 JAVA/JSP/Spring OracleDB 게시판 프로그램

-- Oracle11g에 계정 생성 및 권한 부여 : Oracle 11g sqlplus 실행

sqlplus system/System1234

show user

CREATE USER hlms IDENTIFIED BY hlms;
GRANT CONNECT, RESOURCE TO hlms;
GRANT ALTER SESSION TO hlms;

conn hlms/hlms

show user

-- 회원 시퀀스
DROP SEQUENCE USER_SEQ;
CREATE SEQUENCE USER_SEQ;

-- 과목 시퀀스
DROP SEQUENCE COURSE_SEQ;
CREATE SEQUENCE COURSE_SEQ;

-- 게시글 시퀀스
DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ;

--게시판정보 테이블
DROP TABLE TB_BOARD CASCADE CONSTRAINT;
CREATE TABLE TB_BOARD(
  BOARD_NO VARCHAR2(30),             -- 게시글번호
  USER_CODE VARCHAR2(20),            -- 유저코드
  BOARD_TITLE VARCHAR2(1000),        -- 게시글제목
  BOARD_CONTENTS VARCHAR2(4000),     -- 게시글내용
  BOARD_DATE VARCHAR2(30),           -- 게시글작성날짜
  BOARD_HITS NUMBER DEFAULT 0,     -- 조회수
  BOARD_RATING NUMBER DEFAULT 0,   -- 좋아요
  CONSTRAINT TB_BOARD_PK PRIMARY KEY(BOARD_NO),
  CONSTRAINT TB_BOARD_FK_USER FOREIGN KEY(USER_CODE) REFERENCES TB_COM_USER(USER_CODE)
);

-- 과목분류 테이블
DROP TABLE TB_COM_CODE CASCADE CONSTRAINT;
CREATE TABLE TB_COM_CODE(
  COMM_TY_CD VARCHAR2(10),
  COMM_CD VARCHAR2(20),
  COMM_CD_NM VARCHAR2(30),
  CONSTRAINT TB_CODE_PK PRIMARY KEY(COMM_TY_CD, COMM_CD)
);

-- 과목분류코드 테스트 더미데이터 입력
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'ME', '전공필수');
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'MO', '전공선택');
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'RE', '교양필수');
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'RO', '교양선택');

commit;

select * from tab;

select count(*) from TB_COM_CODE;


-- 주소DB insert 방법

-- cmd창에서 cd workspace/HLMS/post 디렉토리로 이동한후

-- sqlldr hlms/hlms control='post.ctl' data='post.csv' 명령어 실행