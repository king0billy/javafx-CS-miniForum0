CREATE TABLE thumbs_up(
post_id BIGINT,
client_id BIGINT,
thumbs_up_new_date DATETIME DEFAULT '2021-01-01' NOT NULL,
thumbs_up_drop_date DATETIME DEFAULT '2021-01-01',
visible tinyint(1) NOT NULL DEFAULT 1,
PRIMARY KEY(post_id,client_id),
FOREIGN KEY(client_id) REFERENCES client(client_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(post_id) REFERENCES post(post_id) ON DELETE CASCADE ON UPDATE CASCADE
);