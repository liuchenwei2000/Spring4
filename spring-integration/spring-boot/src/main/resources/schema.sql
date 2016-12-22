/* 根路径下的 schema.sql 文件会被应用在启动的时候自动加载并执行 */
CREATE TABLE boot_book
    (
        id VARCHAR(20) NOT NULL,
        name VARCHAR(255),
        author VARCHAR(255),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into boot_book (id, name, author) values ('A0003', 'Effective Java', 'Joshua Bloch');
insert into boot_book (id, name, author) values ('A0004', 'Practical Java', 'Peter Haggar ');
insert into boot_book (id, name, author) values ('A0001', 'Spring in Action', 'Craig Walls');
insert into boot_book (id, name, author) values ('A0002', 'Thinking in Java', 'Bruce Eckel');
