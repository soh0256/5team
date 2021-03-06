sqlplus System/System1234

show user

-- 계정, 권한 생성
CREATE USER android IDENTIFIED BY android;
GRANT CONNECT, RESOURCE TO android;
GRANT ALTER SESSION TO android;

-- spring 계정으로 연결
connect android/android

show user

DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE  BOARD_SEQ;

-- 회원
DROP TABLE MEMBER CASCADE CONSTRAINT;
CREATE TABLE MEMBER(
  ID VARCHAR2(20),                  		-- 아이디	
  PASSWORD VARCHAR2(20),                   	-- 비밀번호
  NAME VARCHAR2(50),                    	-- 이름
  EMAIL VARCHAR2(100),			-- 이메일
  PHONE_NUMBER VARCHAR2(20),		-- 휴대폰 번호
  PROFILE_PIC_URL VARCHAR2(100),		-- 프로필
  CONSTRAINT TB_COM_USER_PK PRIMARY KEY(ID)
);

--게시판
DROP TABLE BOARD CASCADE CONSTRAINT;
CREATE TABLE BOARD(
  NO NUMBER,      	     			-- 게시글번호
  ID VARCHAR2(20),            		-- 아이디
  TITLE VARCHAR2(100),        		-- 게시글제목
  CONTENTS VARCHAR2(1000),     		-- 게시글내용
  DATES DATE DEFAULT SYSDATE,       -- 게시글작성날짜
  HITS NUMBER DEFAULT 0,     		-- 조회수
  IMAGE VARCHAR2(100), 				--이미지
  CONSTRAINT TB_BOARD_PK PRIMARY KEY(NO),
  CONSTRAINT TB_BOARD_FK_USER FOREIGN KEY(ID) REFERENCES MEMBER(ID) ON DELETE CASCADE -- 회원탈퇴를 하면 해당 회원이 쓴 게시글도 삭제
);