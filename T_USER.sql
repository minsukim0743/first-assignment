CREATE TABLE T_USER
(
	ID VARCHAR(16) PRIMARY KEY NOT NULL,
	PWD VARCHAR(32) NOT NULL,
	NAME VARCHAR(128) NOT NULL,
	LEVEL CHAR(1) NOT NULL,
	DESCRIPTION VARCHAR(256) NULL,
	REG_DATE TIMESTAMP NOT NULL
);