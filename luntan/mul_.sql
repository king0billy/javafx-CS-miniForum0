alter table forums modify column topic VARCHAR(50) NOT NULL,add key(topic);

alter table posts modify column title VARCHAR(64) NOT NULL,add key(title);