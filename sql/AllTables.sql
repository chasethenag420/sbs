DROP TABLE IF EXISTS `userpii` cascade;
DROP TABLE IF EXISTS `role` cascade;
DROP TABLE IF EXISTS `authorization` cascade;
DROP TABLE IF EXISTS `transfer` cascade;
DROP TABLE IF EXISTS `transaction` cascade;
DROP TABLE IF EXISTS `account` cascade;
DROP TABLE IF EXISTS `user` cascade;

CREATE TABLE `user` (
  `USERID` int(20) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(30) NOT NULL,
  `MIDDLENAME` varchar(30) DEFAULT NULL,
  `LASTNAME` varchar(30) NOT NULL,
  `GENDER` varchar(6) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(256) DEFAULT NULL,
  `ROLEID` int(20) DEFAULT NULL,
  `USERSTATUS` varchar(10) DEFAULT NULL,
  `REGISTRATION_DATE` date DEFAULT NULL,
  `LAST_MODIFIED_DATE` date DEFAULT NULL,
  `EMAILID` varchar(30) NOT NULL,
  `PHONENUM` varchar(15) NOT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `STATE` varchar(30) DEFAULT NULL,
  `COUNTRY` varchar(30) DEFAULT NULL,
  `ZIPCODE` varchar(10) DEFAULT NULL,
  UNIQUE KEY `USERNAME` (`USERNAME`),
  PRIMARY KEY (`USERID`)
  
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


CREATE TABLE `account` (
  `USERID` int(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNTNUM` int(20) NOT NULL,
  `ACCOUNT_TYPE` varchar(10) NOT NULL,
  `ACTIVE_FLAG` int(5) NOT NULL,
  `BALANCE` int(20) NOT NULL,
  `CREATION_DATE` date NOT NULL,
  `MODIFIED_TIMESTAMP` date NOT NULL,
  PRIMARY KEY (`ACCOUNTNUM`),
  KEY `FK_ACCOUNT` (`USERID`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `transaction` (
  `USERID` int(20) NOT NULL,
  `TRANSACTIONID` int(20) NOT NULL AUTO_INCREMENT,
  `TRANSACTION_STATUS` varchar(10) NOT NULL,
  `CREATION_TIMESTAMP` date NOT NULL,
  `TRANSACTION_TYPE` varchar(5) NOT NULL,
  `MODIFIED_BY_USERID` int(20) DEFAULT NULL,
  `MODIFIED_TIMESTAMP` date DEFAULT NULL,
  `AMOUNT` int(20) NOT NULL,
  `TRANSFER_ID` int(20) NOT NULL,
  `TRANSACTION_KIND` varchar(5) DEFAULT NULL,
  `ACCOUNTNUM` int(20) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTIONID`),
  KEY `FK_TRANS` (`ACCOUNTNUM`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`ACCOUNTNUM`) REFERENCES `account` (`ACCOUNTNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `transfer` (
  `TRANSFERID` int(20) NOT NULL AUTO_INCREMENT,
  `TRANSACTION_STATUS` varchar(5) NOT NULL,
  `user_to_transactionid` int(20) DEFAULT NULL,
  `user_from_transactionid` int(20) DEFAULT NULL,
  PRIMARY KEY (`TRANSFERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `authorization` (
  `AUTHORIZATIONID` int(20) NOT NULL AUTO_INCREMENT,
  `AUTHORIZED_BY_USERID` int(20) NOT NULL,
  `AUTHORIZED_TO_USERID` int(20) NOT NULL,
  `TRANSACTION_ID` int(20) DEFAULT NULL,
  `REQUEST_CREATION_TS` date DEFAULT NULL,
  `REQUEST_TYPE` varchar(500) DEFAULT NULL,
  `REQUEST_STATUS` varchar(10) NOT NULL,
  `APPROVAL_FLAG` varchar(10) DEFAULT NULL,
  `REQUEST_DESCRIPTION` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`AUTHORIZATIONID`),
  KEY `FK_USERBY` (`AUTHORIZED_BY_USERID`),
  /*KEY `FK_USERTO` (`AUTHORIZED_TO_USERID`),
  KEY `FK_TRANSACTION` (`TRANSACTION_ID`),*/
  CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`AUTHORIZED_BY_USERID`) REFERENCES `user` (`USERID`)
  /*CONSTRAINT `authorization_ibfk_2` FOREIGN KEY (`AUTHORIZED_TO_USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `authorization_ibfk_3` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transaction` (`TRANSACTIONID`)
  */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `role` (
  `ROLEID` int(10) NOT NULL,
  `ROLEDESCRIPTION` varchar(100) NOT NULL,
  `VIEWFLAG` int(5) NOT NULL,
  `MODIFYFLAG` int(5) NOT NULL,
  `CREATEFLAG` int(5) NOT NULL,
  `DELETEFLAG` int(5) NOT NULL,
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `userpii` (
  `USERID` int(20) NOT NULL,
  `SSN` varchar(255) NOT NULL,
  `DATEOFBIRTH` date NOT NULL,
  UNIQUE KEY `SSN` (`SSN`),
  KEY `FK_USERID` (`USERID`),
  CONSTRAINT `userpii_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into role values(5,'admin',1,1,1,1);
insert into role values(4,'manager',1,1,1,1);
insert into role values(3,'regular',1,1,0,0);
insert into role values(2,'merchant',1,0,0,0);
insert into role values(1,'individual',1,0,0,0);