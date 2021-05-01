##命令行create datebase
CREATE TABLE client(  
##user_account VARCHAR(11) PRIMARY KEY,
client_id BIGINT PRIMARY KEY,
client_tel VARCHAR(16) UNIQUE,##2+3+11
client_password VARCHAR(20) NOT NULL,
client_nickname VARCHAR(20),
client_sex ENUM('男','man','女','woman','保密','secret','LGBTQ'),
client_address VARCHAR(500),
client_descript MEDIUMTEXT,
##user_descript VARCHAR(2184),
client_enroll_date DATETIME DEFAULT '2021-01-01' NOT NULL,
client_privilege SMALLINT
);##TYPE =INNODB;##ENGINE INNODB;