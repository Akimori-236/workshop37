CREATE DATABASE IF NOT EXISTS `workshop37`;
USE `workshop37`;
CREATE TABLE IF NOT EXISTS `posts` (
  `post_id` varchar(8) NOT NULL,
  `comments` mediumtext,
  `picture` mediumblob NOT NULL,
  `picture_filetype` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`)
);
