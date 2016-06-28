CREATE DATABASE `spark-sample`;

USE `spark-sample`;

CREATE TABLE `Users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `Users` (
  `name`,
  `email`
) VALUES
("name1" ,"name1@sample.com"),
("name2" ,"name2@sample.com"),
("name3" ,"name3@sample.com"),
("name4" ,"name4@sample.com"),
("name5" ,"name5@sample.com");