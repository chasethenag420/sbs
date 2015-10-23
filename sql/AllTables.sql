DROP TABLE IF EXISTS `userpii` cascade;
DROP TABLE IF EXISTS `role` cascade;
DROP TABLE IF EXISTS `authorization` cascade;
DROP TABLE IF EXISTS `transfer` cascade;
DROP TABLE IF EXISTS `transaction` cascade;
DROP TABLE IF EXISTS `account` cascade;
DROP TABLE IF EXISTS `user` cascade;
DROP TABLE IF EXISTS `accesscontrol` cascade;
DROP TABLE IF EXISTS `securityquestions` cascade;

CREATE TABLE `accesscontrol` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(45) NOT NULL,
  `VIEWPROFILE` int(11) DEFAULT '0',
  `CANCELTRANSACTION` int(11) DEFAULT '0',
  `MODIFYEXTERNALUSER` int(11) DEFAULT NULL,
  `DELETEEXTERNALUSER` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `USERID_UNIQUE` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
   `OTP` varchar(10) DEFAULT NULL,
    `FAILED_ATTEMPTS` int(20) DEFAULT NULL,
  UNIQUE KEY `USERNAME` (`USERNAME`),
  PRIMARY KEY (`USERID`)
  
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


CREATE TABLE `account` (
  `USERID` int(20) NOT NULL,
  `ACCOUNTNUM` int(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(10) NOT NULL,
  `ACTIVE_FLAG` int(5) NOT NULL,
  `BALANCE` double  NOT NULL,
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
  `TRANSACTION_TYPE` varchar(10) NOT NULL,
  `MODIFIED_BY_USERID` int(20) DEFAULT NULL,
  `MODIFIED_TIMESTAMP` date DEFAULT NULL,
  `AMOUNT` double  NOT NULL,
  `TRANSFER_ID` int(20) NOT NULL,
  `TRANSACTION_KIND` varchar(15) DEFAULT NULL,
  `ACCOUNTNUM` int(20) DEFAULT NULL,
  `TRANSACTION_DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTIONID`),
  KEY `FK_TRANS` (`ACCOUNTNUM`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`ACCOUNTNUM`) REFERENCES `account` (`ACCOUNTNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `transfer` (
  `TRANSFERID` int(20) NOT NULL AUTO_INCREMENT,
  `TRANSACTION_STATUS` varchar(10) NOT NULL,
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
  `REQUEST_TYPE` varchar(20) DEFAULT NULL,
  `REQUEST_STATUS` varchar(10) NOT NULL,
  `ASSIGNED_TO_ROLE` varchar(10) DEFAULT NULL,
  `REQUEST_DESCRIPTION` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`AUTHORIZATIONID`),
  KEY `FK_USERBY` (`AUTHORIZED_TO_USERID`),
  /*KEY `FK_USERTO` (`AUTHORIZED_TO_USERID`),
  KEY `FK_TRANSACTION` (`TRANSACTION_ID`),*/
  CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`AUTHORIZED_TO_USERID`) REFERENCES `user` (`USERID`)
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


CREATE TABLE `securityquestions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `question1` varchar(100) DEFAULT NULL,
  `answer1` varchar(100) DEFAULT NULL,
  `question2` varchar(100) DEFAULT NULL,
  `answer2` varchar(100) DEFAULT NULL,
  `question3` varchar(100) DEFAULT NULL,
  `answer3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


insert into role values(5,'admin',1,1,1,1);
insert into role values(4,'manager',1,1,1,1);
insert into role values(3,'regular',1,1,0,0);
insert into role values(2,'merchant',1,0,0,0);
insert into role values(1,'individual',1,0,0,0);

#USERNAME,PASSWORD,SSN  --SAME EMAILS FOR ALL
#==========================
#internal1,internal1,123455,gzilpe@asu.edu
#internal2,internal2,123456,caware1@asu.edu
#internal3,internal3,123457,Jaswitha.Vankineni@asu.edu
#internal4,internal4,123458,Sameer.Goyal@asu.edu
#internal5,internal5,123459,nmyla@asu.edu

# INTERNAL USER
#===============
insert into user values(32,'Internal',null,'User1','Male','internal1','$2a$10$vLnQ1qhKA3M5nn6OPFxx2uITMzbSEX8wmTpxVEWULwqfPr6/yFub2',3,'active','2015-10-14','2015-10-17','gzilpe@asu.edu','7878787878','hhhhh','Hyd','AP','India','321987','','0');
insert into userpii values(32,'$2a$10$WOziwyOk8ovF4d.88srVre6JAA0XAlEnM8HPTJ8oL95T9gdnY6n8C','1987-01-01');

insert into user values(33,'Internal',null,'User2','Female','internal2','$2a$10$NepFLVH1e4eJR6SWDiK0l.kkqpg81MEbkvpeptd5V6DCA934XrVlm',3,'active','2015-10-14','2015-10-17','caware1@asu.edu','9878787878','hhhhh','Hyd','AP','India','321987','','0');
insert into userpii values(33,'$2a$10$1qRZBVD98JG8pv4WzIUfM.C.eByD4jnTQSg4EKg760wc9uqohZvsa','1984-01-01');

insert into user values(34,'Internal',null,'User3','Male','internal3','$2a$10$IXgZ.vaaha0WuXjAEQ7XW.dO749E30oaXfuXRBSoAzNjs7oKVzU2W',3,'active','2015-10-14','2015-10-17','Jaswitha.Vankineni@asu.edu','8878787878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(34,'$2a$10$ifcd/j6kKVeDYPjinzjP0OUBN2.bN59K7EmMcuW.cGjvnDPVnMkHK','1984-03-03');

insert into user values(35,'Internal',null,'User4','Male','internal4','$2a$10$iHSQl/CGej45x2vFCmTvnOLYJCqdp1u2frkNehPfyGi.7SCMzojXe',3,'active','2015-10-14','2015-10-17','Sameer.Goyal@asu.edu','6478787878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(35,'$2a$10$1gMvbXWFK3xuvvL3Zsbp8OsGhiIaGJ3H/adA8RhBK3007vFkh0W.6','1982-02-02');

insert into user values(36,'Internal',null,'User5','Male','internal5','$2a$10$IOVfUyVJZ47EctpXaN61Muogd9gy3Uiqen43cnXigWa7LXZeyNOXm',3,'active','2015-10-14','2015-10-17','nmyla@asu.edu','5478787878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(36,'$2a$10$FSPiFbYnztRfEknjW/rYKOxkNpedPveEsNA0NSqrwlF3Wrx2nEBrG','1981-02-02');


#USERNAME,PASSWORD,SSN
#==========================
#external1,external1,223455
#external2,external2,223456
#external3,external3,223457
#external5,external4,223458
#external4,external5,223459

insert into user values(56,'External',null,'User1','Male','external1','$2a$10$1R1u0c4yJTyLPtc/qreSf.iYYVOUWb9M.fLjqxaRKKHAduBtT3dHy',1,'active','2015-10-14','2015-10-17','gzilpe@asu.edu','5472887878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(56,'$2a$10$rvRu5pjAG/kA6GhJdTsCYe/0JIrurESp49F42C2WoyNa5iq6HAOfW','1981-02-02');

insert into user values(57,'External',null,'User1','Male','external2','$2a$10$hZfUqRao8t.Lm5kEHAZhpOSf8wPxYJ8tHseZL7fXo1JdZBu08V2qm',1,'active','2015-10-14','2015-10-17','caware1@asu.edu','5472887878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(57,'$2a$10$J0F5a7IF9gwLuPMXPbRTMO9RH00RFyWSxCi24HJI1AIdzWdalsyWS','1982-02-02');

insert into user values(58,'External',null,'User1','Male','external3','$2a$10$nWAfBkA7M2n1wUa07EuWC.m.C6Y.ax0YCpZQzgCXyFQeuRUXP9HKK',1,'active','2015-10-14','2015-10-17','Jaswitha.Vankineni@asu.edu','5472887878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(58,'$2a$10$5JzEbR6y9y.s..5lV2Q4KewTXA6YRisYxN3rcYCjEaNr/miqPhQjS','1983-02-02');

insert into user values(59,'External',null,'User1','Male','external4','$2a$10$RXLzDq3k7Br1KoK7CLldx.wb.z9ZeBp/RZQjb9hVDtlgamD6PFgSa',1,'active','2015-10-14','2015-10-17','Sameer.Goyal@asu.edu','5472887878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(59,'$2a$10$.Wpt8NRItYJPUf4LHK66ZewZYMiPtvS9VaSIS6GzjrW4lBc9IHW4q','1984-02-02');

insert into user values(60,'External',null,'User1','Male','external5','$2a$10$TTAzKv9JoxGntLLsRwXDaOzmfBgcDVlBiKEttZ1mTp4d1.s.q4ajC',1,'active','2015-10-14','2015-10-17','nmyla@asu.edu','5472887878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(60,'$2a$10$VJ0If4IUjtIIVeFuZBpEouOiIF3z12URSdGbigKmgFJwAMn/slFnS','1985-02-02');



#USERNAME,PASSWORD,SSN
#==========================
#manager1,manager1,323455
#manager2,manager2,323456
#manager3,manager3,323457
#manager4,manager4,323458
#manager5,manager5,323459

# MANAGER
#===============
insert into user values(42,'Manager',null,'User1','Male','manager1','$2a$10$nwH6B5N0KDQiwYC8r0T4xu3zQNp8YV2jRvCQ5PQ.zVgDKGlCPq0/6',4,'active','2015-10-14','2015-10-17','gzilpe@asu.edu','7278787878','hhhhh','Hyd','AP','India','321987','','0');
insert into userpii values(42,'$2a$10$2/2tL8ekcyIQnxu9VIXCMO0OT2fgXNcHMIb2GbhQUEjLBieSm1ALG','1987-01-01');

insert into user values(43,'Manager',null,'User2','Female','manager2','$2a$10$qLJ3dFa06D5uW7iZJ5tEb.oyrAzdxMznNS5Is5fB1ktYo6.LHAzZO',4,'active','2015-10-14','2015-10-17','caware1@asu.edu','9278787878','hhhhh','Hyd','AP','India','321987','','0');
insert into userpii values(43,'$2a$10$jnaiO5DOTBgxYjSSWGV6lO53Bz5GQI0ajFLsZT.F.HDMx86v8v.hC','1984-01-01');

insert into user values(44,'Manager',null,'User3','Male','manager3','$2a$10$jFDfatxqoNrXy1n.cLpqm.vG1zMMgmA6mbljka2Ac.x5oj4Igzflm',4,'active','2015-10-14','2015-10-17','Jaswitha.Vankineni@asu.edu','8872787878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(44,'$2a$10$MqDjdSMb4rAAw7JCABK3p.vt04MR834VbqQ4RnCXNY9l4.E5LGbAS','1984-03-03');

insert into user values(45,'Manager',null,'User4','Male','manager4','$2a$10$UMGHizp1q9a5n/zKwtgLfOsFRbwkKmRLbzPzfal4zdqKYRJ1/av4a',4,'active','2015-10-14','2015-10-17','Sameer.Goyal@asu.edu','6472787878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(45,'$2a$10$y9XWVwbHHvwFRiJVhYv2C.K7ii0m8LvpITtvL6pwdBkVXtEzqSVqm','1982-02-02');

insert into user values(46,'Manager',null,'User5','Male','manager5','$2a$10$.IssZ.C7opRE/dNg8ByLRuPHNPtBRhmJfxw1c9.WdpArd9VDeeRym',4,'active','2015-10-14','2015-10-17','nmyla@asu.edu','5472787878','hhhhh','MUM','MH','India','921987','','0');
insert into userpii values(46,'$2a$10$NjxZu6QkuVdBAq6jypWOkeqIJ8nl/a.FlU0TXQadA/eq2n8TuEkTu','1981-02-02');


#USERNAME,PASSWORD,SSN
#==========================
#admin1,admin1,423455,gzilpe@asu.edu

insert into user values(88,'Admin',null,'User1','Male','admin1','$2a$10$/TvqjAWhG1b29.acd30lWulOrsIHMXyJMPDwJwBu7tdyM8YdyoDPa',5,'active','2015-10-14','2015-10-17','gzilpe@asu.edu','7278787878','hhhhh','Hyd','AP','India','321987','','0');
insert into userpii values(88,'$2a$10$rFWtm2LwUUfkM/JJTIBjqOTPMyQBR9XSXbY4arXNija7Ue50sCSoi','1987-01-01');



