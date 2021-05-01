CREATE TABLE POST(  
##post_id VARCHAR(15)PRIMARY KEY,
post_id BIGINT PRIMARY KEY,client_id BIGINT, 
post_new_date DATETIME DEFAULT '2021-01-01' NOT NULL,
post_title MEDIUMTEXT,
post_article LONGTEXT,
thumbs_up_count INT,
favorite_count INT,
remark_count INT,
FOREIGN KEY(client_id) REFERENCES client(client_id) ON DELETE CASCADE ON UPDATE CASCADE
);##TYPE =INNODB;##ENGINE INNODB;