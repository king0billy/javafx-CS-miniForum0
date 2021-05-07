Create table postid(
	id int(11),
	title varchar(64),
	FOREIGN KEY(id)REFERENCES forums(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(title)REFERENCES posts(title) ON DELETE CASCADE ON UPDATE CASCADE
	);