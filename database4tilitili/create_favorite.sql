CREATE TABLE FAVORITE(
post_id BIGINT,
client_id BIGINT,
favorite_new_date DATETIME DEFAULT '2021-01-01' NOT NULL,
tag VARCHAR(500),
PRIMARY KEY(post_id,client_id),
FOREIGN KEY(client_id) REFERENCES client(client_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(post_id) REFERENCES POST(post_id) ON DELETE CASCADE ON UPDATE CASCADE
);