CREATE TABLE address (
	id BIGINT(20) AUTO_INCREMENT,
    address VARCHAR(50),
    address_complement VARCHAR(20),
    number VARCHAR(10),
    neighborhood VARCHAR(30),
    zipcode VARCHAR(9),
    city VARCHAR(30),
    state VARCHAR(30),
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE customer (
	id BIGINT(20) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN,
    address_id BIGINT(20) UNIQUE,
    PRIMARY KEY(id),
    FOREIGN KEY(address_id) REFERENCES address(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO address (address, address_complement, number, neighborhood, zipcode, city, state) values ('Estrada Areia Branca', 'ap 305', '400', 'Areia Branca', '76808730', 'Porto Velho', 'Roraima');
INSERT INTO customer (name, active, address_id) values ('Aparecida Alice Almeida', true, 1);
INSERT INTO address (address, address_complement, number, neighborhood, zipcode, city, state) values ('Rua Ouvídio A. Passos', '', '527', 'Centro', '06700155', 'Cotia', 'São Paulo');
INSERT INTO customer (name, active, address_id) values ('Igor Marcos Oliveira', true, 2);
INSERT INTO address (address, address_complement, number, neighborhood, zipcode, city, state) values ('Rua das Violetas', '', '115', 'Jardim das Oliveiras', '78552376', 'Sinop', 'Mato Grosso');
INSERT INTO customer (name, active, address_id) values ('Lara Daniela Alves', true, 3);
