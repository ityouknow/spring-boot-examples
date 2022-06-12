DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    userName  varchar(32) DEFAULT NULL COMMENT '用户名',
    passWord  varchar(32) DEFAULT NULL COMMENT '密码',
    user_sex  varchar(32) DEFAULT NULL,
    nick_name varchar(32) DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (userName, passWord, user_sex, nick_name)
VALUES ('matin', 'password', 'MAN', 'matin');