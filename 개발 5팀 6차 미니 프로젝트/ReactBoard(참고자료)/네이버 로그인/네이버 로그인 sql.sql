sqlplus system/System1234

CREATE USER TEST IDENTIFIED BY TEST123;
GRANT CONNECT, RESOURCE, DBA TO TEST;
GRANT ALTER SESSION TO TEST;

conn TEST / TEST123

show user

DROP TABLE ACCOUNTS ;
CREATE TABLE ACCOUNTS 
(
  IDX NUMBER NOT NULL 
, USERID VARCHAR2(20 BYTE) 
, USERNAME VARCHAR2(20 BYTE) 
, USERPASSWORD VARCHAR2(20 BYTE) 
, USERMAIL VARCHAR2(30 BYTE) 
, USERPHONE VARCHAR2(20 BYTE) 
, USERPROFILE VARCHAR2(100 BYTE) 
, USERFULLADDRESS VARCHAR2(100 BYTE) 
, USERSUBADDRESS VARCHAR2(100 BYTE) 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NOPARALLEL;

commit;