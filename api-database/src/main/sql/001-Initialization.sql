--liquibase formatted sql

--changeset maxikg:usersTable
CREATE TABLE `users` (
   `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `ign` varchar(16) DEFAULT NULL,
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid_UNIQUE` (`uid`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8