CREATE TABLE
    myapp_employee
    (
        id VARCHAR(20) NOT NULL,
        code VARCHAR(100) NOT NULL,
        password VARCHAR(255) NOT NULL,
        name VARCHAR(100) NOT NULL,
        dept VARCHAR(50),
        salary DECIMAL(9,2),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;