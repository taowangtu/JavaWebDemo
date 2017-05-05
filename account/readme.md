演示简单的转账业务逻辑。
使用jsp+servlet + jdbc事务控制。
mvc分层设计模式。

数据库为：
CREATE DATABASE `account` DEFAULT CHARACTER SET utf8;
USE account;
 CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `money` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
insert into account(username,money) value("逃亡兔","10000"),("越狱兔","20000")


