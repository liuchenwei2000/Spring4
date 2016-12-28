CREATE TABLE
  s_book
(
  id    VARCHAR(20) NOT NULL,
  title VARCHAR(255),
  price DECIMAL(9, 2),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8