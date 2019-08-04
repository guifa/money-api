CREATE TABLE category (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO category (name) values ('Recreation');
INSERT INTO category (name) values ('Food');
INSERT INTO category (name) values ('Grocery');
INSERT INTO category (name) values ('Medicine');
INSERT INTO category (name) values ('Others');