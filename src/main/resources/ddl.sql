CREATE SCHEMA clzblog;

USE clzblog;

CREATE TABLE `user` (
  `id`       int(8)       NOT NULL AUTO_INCREMENT,
  `name`     varchar(20)  NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_uindex` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;