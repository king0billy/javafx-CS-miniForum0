##命令行create datebase
CREATE TABLE USERS(  
user_account VARCHAR(11) PRIMARY KEY, 
user_password VARCHAR(20) NOT NULL,
user_nickname VARCHAR(20),
user_sex ENUM('男','man','女','woman','保密','secret','LGBTQ'),
user_address VARCHAR(500),
user_descript MEDIUMTEXT,
##user_descript VARCHAR(2184),
user_enroll_date DATETIME DEFAULT '2021-01-01' NOT NULL,
user_privilege SMALLINT
);##TYPE =INNODB;##ENGINE INNODB;