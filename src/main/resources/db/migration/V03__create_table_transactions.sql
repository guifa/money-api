CREATE TABLE transaction (
	id BIGINT(20) AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    amount DECIMAL(10,2) NOT NULL,
    note VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    category_id BIGINT(20) NOT NULL,
    customer_id BIGINT(20) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(category_id) REFERENCES category(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO transaction (description, due_date, payment_date, amount, note, type, category_id, customer_id) values ('Salary', '2019-08-05', '2019-08-05', 6500.00, 'Monthly Salary', 'INCOME', 1, 1);
INSERT INTO transaction (description, due_date, payment_date, amount, note, type, category_id, customer_id) values ('Bahamas', '2019-08-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO transaction (description, due_date, payment_date, amount, note, type, category_id, customer_id) values ('Top Club', '2019-08-10', null, 120, null, 'INCOME', 3, 3);