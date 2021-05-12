
-- �̴� ������Ʈ ������Ʈ Database ���� �� ����, Table, Column, Value, Index, Trigger �� ���� SQL ���� ����

-- OracleDB �� �� �ּ� ó�� --

-- OracleDB ���� ���� �ڵ忡 �ּ��� ���� ���� �ּ� ó���� �κ��� ó���� ���� ��/*���� ��*/���� �ٿ��ݴϴ�.

-- MysqlDB �� �� �ּ� ó�� #

-- MysqlDB ���� ���� �ڵ忡 �ּ��� ���� ���� �ּ� ó���� �κ��� ó���� ���� ��/*���� ��*/���� �ٿ��ݴϴ�.

-- ����(����) : �̴�������Ʈ ���� 0�� JAVA/JSP/Spring OracleDB �Խ��� ���α׷�

-- Oracle11g�� ���� ���� �� ���� �ο� : Oracle 11g sqlplus ����

sqlplus system/System1234

show user

CREATE USER hlms IDENTIFIED BY hlms;
GRANT CONNECT, RESOURCE TO hlms;
GRANT ALTER SESSION TO hlms;

conn hlms/hlms

show user

-- ȸ�� ������
DROP SEQUENCE USER_SEQ;
CREATE SEQUENCE USER_SEQ;

-- ���� ������
DROP SEQUENCE COURSE_SEQ;
CREATE SEQUENCE COURSE_SEQ;

-- �Խñ� ������
DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ;

--�Խ������� ���̺�
DROP TABLE TB_BOARD CASCADE CONSTRAINT;
CREATE TABLE TB_BOARD(
  BOARD_NO VARCHAR2(30),             -- �Խñ۹�ȣ
  USER_CODE VARCHAR2(20),            -- �����ڵ�
  BOARD_TITLE VARCHAR2(1000),        -- �Խñ�����
  BOARD_CONTENTS VARCHAR2(4000),     -- �Խñ۳���
  BOARD_DATE VARCHAR2(30),           -- �Խñ��ۼ���¥
  BOARD_HITS NUMBER DEFAULT 0,     -- ��ȸ��
  BOARD_RATING NUMBER DEFAULT 0,   -- ���ƿ�
  CONSTRAINT TB_BOARD_PK PRIMARY KEY(BOARD_NO),
  CONSTRAINT TB_BOARD_FK_USER FOREIGN KEY(USER_CODE) REFERENCES TB_COM_USER(USER_CODE)
);

-- ����з� ���̺�
DROP TABLE TB_COM_CODE CASCADE CONSTRAINT;
CREATE TABLE TB_COM_CODE(
  COMM_TY_CD VARCHAR2(10),
  COMM_CD VARCHAR2(20),
  COMM_CD_NM VARCHAR2(30),
  CONSTRAINT TB_CODE_PK PRIMARY KEY(COMM_TY_CD, COMM_CD)
);

-- ����з��ڵ� �׽�Ʈ ���̵����� �Է�
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'ME', '�����ʼ�');
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'MO', '��������');
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'RE', '�����ʼ�');
INSERT INTO TB_COM_CODE(COMM_TY_CD, COMM_CD, COMM_CD_NM) VALUES('CODE0102', 'RO', '���缱��');

commit;

select * from tab;

select count(*) from TB_COM_CODE;


-- �ּ�DB insert ���

-- cmdâ���� cd workspace/HLMS/post ���丮�� �̵�����

-- sqlldr hlms/hlms control='post.ctl' data='post.csv' ��ɾ� ����