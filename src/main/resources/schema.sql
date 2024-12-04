CREATE TABLE todos (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    text VARCHAR(2000) NOT NULL,
    due_date DATE NOT NULL,
    priority VARCHAR(50) NOT NULL
);

CREATE TABLE users (
	id varchar(36) PRIMARY KEY,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(200) NOT NULL
);