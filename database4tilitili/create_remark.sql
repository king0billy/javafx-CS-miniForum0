CREATE TABLE remark(  
##REPLY_id VARCHAR(15)PRIMARY KEY,
father_id BIGINT,
floor INT DEFAULT 1,
to_floor int default 0,
client_id BIGINT, 
remark_new_date DATETIME DEFAULT '2021-01-01' NOT NULL,
remark_edit_date DATETIME DEFAULT '2022-01-01',
remark_article LONGTEXT,
thumbs_up_count INT default 0,
visible tinyint default 1,
PRIMARY KEY(father_id,floor),
FOREIGN KEY(client_id) REFERENCES client(client_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(father_id) REFERENCES POST(post_id) ON DELETE CASCADE ON UPDATE CASCADE 
);##TYPE =INNODB;##ENGINE INNODB;