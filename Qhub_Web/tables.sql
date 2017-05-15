CREATE TABLE exams(
	id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(255) NOT NULL
);

CREATE TABLE topics(
	id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(255) NOT NULL
);


CREATE TABLE questions(
	id int PRIMARY KEY AUTO_INCREMENT,
	question text NOT NULL,
	answer varchar(255) NOT NULL,
	explanation text,
	optA varchar(255) NOT NULL,
	optB varchar(255) NOT NULL,
	optC varchar(255) NOT NULL,
	optD varchar(255) NOT NULL
);

CREATE TABLE exams_topics(
	id int PRIMARY KEY AUTO_INCREMENT,
	eId int NOT NULL,
	tId int NOT NULL,
	UNIQUE(eId, tId)
);

CREATE TABLE topics_questions(
	id int PRIMARY KEY AUTO_INCREMENT,
	tId int NOT NULL,
	qId int NOT NULL,
	UNIQUE(tId, qId)
);

CREATE TABLE exams_topics_questions(
	id int PRIMARY KEY AUTO_INCREMENT,
	eId int NOT NULL,
	tId int NOT NULL,
	qId int NOT NULL,
	UNIQUE(eId, tId, qId)
);






