CREATE TABLE POST(  
##post_id VARCHAR(15)PRIMARY KEY,
post_id BIGINT PRIMARY KEY,client_id BIGINT, 
post_new_date DATETIME DEFAULT '2021-01-01' NOT NULL,
##post_article varchar(5000) DEFAULT'shit',

post_title varchar(100) DEFAULT (SUBSTR(post_article FROM 1 FOR 10)),##BLOB, TEXT, GEOMETRY or JSON column 'post_title' can't have a default value
post_article LONGTEXT,
##post_artic varchar(100) DEFAULT(post_article),
##post_arti varchar(100) DEFAULT (sdf),

##post_title MEDIUMTEXT,
##post_article LONGTEXT,

thumbs_up_count INT,
favorite_count INT,
remark_count INT,
FOREIGN KEY(client_id) REFERENCES client(client_id) ON DELETE CASCADE ON UPDATE CASCADE
);##TYPE =INNODB;##ENGINE INNODB;