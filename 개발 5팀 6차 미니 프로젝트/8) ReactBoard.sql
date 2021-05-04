sqlplus system/System1234

CREATE USER REACT IDENTIFIED BY REACT;
GRANT CONNECT, RESOURCE, DBA TO REACT;
GRANT ALTER SESSION TO REACT;

conn REACT / REACT

show user

DROP TABLE ACCOUNTS CASCADE CONSTRAINT;
CREATE TABLE ACCOUNTS 
(
  USER_NO NUMBER NOT NULL 
, USERID VARCHAR2(20 BYTE) 
, USERNAME VARCHAR2(20 BYTE) 
, USERPASSWORD VARCHAR2(20 BYTE) 
, USERMAIL VARCHAR2(30 BYTE) 
, USERPHONE VARCHAR2(20 BYTE) 
, USERPROFILE VARCHAR2(100 BYTE) 
, USERFULLADDRESS VARCHAR2(100 BYTE) 
, USERSUBADDRESS VARCHAR2(100 BYTE) 
, CONSTRAINT ACCOUNTS_PK PRIMARY KEY(USER_NO)
) ;


-- 게시판 테이블

DROP TABLE POST;
CREATE TABLE POST 
(	
POST_NO NUMBER, 
USERID VARCHAR2(20 BYTE), 
USERNAME VARCHAR2(20 BYTE), 
TITLE VARCHAR2(200 BYTE), 
CONTENT LONG, 
IMAGE VARCHAR2(200 BYTE),
CONSTRAINT POST_PK PRIMARY KEY(POST_NO),
CONSTRAINT POST_FK FOREIGN KEY(USERID, USERNAME) REFERENCES ACCOUNTS_FK(USERID, USERNAME)
) ;


-- 게시판 카운트 테이블

DROP TABLE POST_COMMENT;
CREATE TABLE POST_COMMENT 
(	
USER_NO NUMBER, 
POST_NO NUMBER, 
GOOD NUMBER(1,0) DEFAULT 0, 
STAR NUMBER(2,0) DEFAULT 0
CONSTRAINT POST_COMMENT_U_FK FOREIGN KEY(USERNO) REFERENCES ACCOUNTS_FK(USERNO),
CONSTRAINT POST_COMMENT_P_FK FOREIGN KEY(POST_NO) REFERENCES POST_FK(POST_NO)
)  ;



commit;