--
-- Create database 'studentbarter'
--
DROP DATABASE studentbarter;
CREATE DATABASE IF NOT EXISTS studentbarter;
USE studentbarter;

--
-- Table1 structure for table 'role'
--
DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

--
-- Dumping data for table 'role'
--
LOCK TABLES role WRITE;
INSERT INTO role VALUES (1,'ROLE_USER');
INSERT INTO role VALUES (2,'ROLE_ADMIN');
INSERT INTO role VALUES (3,'ROLE_MODERATOR');
UNLOCK TABLES;

--
-- Table2 structure for table 'account'
--
DROP TABLE IF EXISTS account;
CREATE TABLE account (
  accountId int(11) NOT NULL AUTO_INCREMENT,
  studentId int(10),
  username varchar(255) UNIQUE NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (accountId)
);

--
-- Dumping data for table 'account'
--
LOCK TABLES account WRITE;
INSERT INTO account values(1,800100200,'ssridh10@gmail.com','12345678');
INSERT INTO account values(2,800100001,'jgreen157@uncc.edu','12345678');
INSERT INTO account values(3,800102002,'rvyas@uncc.edu','12345678');
INSERT INTO account values(4,800002003,'anair5@uncc.edu','12345678');
INSERT INTO account values(5,801002004,'nverma4@uncc.edu','12345678');
INSERT INTO account values(6,801002005,'bkaruman@uncc.edu','12345678');
INSERT INTO account values(7,811002006,'vande@uncc.edu','12345678');
INSERT INTO account values(8,822002007,'bpothina@uncc.edu','12345678');
INSERT INTO account values(9,833002008,'ssridh12@uncc.edu','12345678');
INSERT INTO account values(10,834102009,'pradha5@uncc.edu','12345678');
INSERT INTO account values(11,800602010,'afrank120@uncc.edu','12345678');
INSERT INTO account values(12,800502011,'cjohn25@uncc.edu','12345678');
INSERT INTO account values(13,800772012,'rpadma@uncc.edu','12345678');
INSERT INTO account values(14,800108813,'sroz10@uncc.edu','12345678');
INSERT INTO account values(15,800100264,'dpaul11@uncc.edu','12345678');

UNLOCK TABLES;

--
-- Table3 structure for table `user`
--
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userId int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(255) DEFAULT NULL,
  middleName varchar(255) DEFAULT NULL,
  lastName varchar(255) DEFAULT NULL,
  dateOfBirth date DEFAULT NULL,
  phoneNumber varchar(10) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  accountId int(11) NOT NULL,
  PRIMARY KEY (userId),
  KEY account_fk (accountId),
  CONSTRAINT account_fk FOREIGN KEY (accountId) REFERENCES account (accountId)  
);

--
-- Dumping data for table 'user'
--
LOCK TABLES user WRITE;
INSERT INTO user values(1,'Henry','','Dejong','1992-09-29','9804300935','9519 utd',1);
INSERT INTO user values(2,'Joshua','','Green','1993-03-19','9804300937','95190 utd',2);
INSERT INTO user values(3,'Ravi','','Vyas','1994-01-09','9804300934','95192 utd',3);
INSERT INTO user values(4,'Abhijit','','Nair','1995-01-01','9804300933','95193 utd',4);
INSERT INTO user values(5,'Naman','','Verma','1987-11-02','9804300932','95193 utd',5);
INSERT INTO user values(6,'Bhanu','','Sri','1988-12-03','9804300931','951955 utd',6);
INSERT INTO user values(7,'Rohit','','Padma','1997-11-04','9804300938','95197 utd',7);
INSERT INTO user values(8,'virinchi','','ande','1987-10-12','9804300939','95199 utd',8);
INSERT INTO user values(9,'Bharat','','Pothina','1990-01-14','9804300990','95190 utd',9);
INSERT INTO user values(10,'Sindhuja','','Sridharan','1981-02-15','9804300937','95149 utd',10);
INSERT INTO user values(11,'Priya','','Raj','1983-03-19','9804300437','95139 utd',11);
INSERT INTO user values(12,'Anne','','Frank','1984-04-22','9804303937','95129 utd',12);
INSERT INTO user values(13,'Chri','','Johnathan','1985-05-23','9804334937','95199 utd',13);
INSERT INTO user values(14,'Simon','','Roz','1986-06-25','9804354937','093 utd',14);
INSERT INTO user values(15,'Debbie','','Paul','1986-07-27','9804344937','999 utd',15);

UNLOCK TABLES;

--
-- Table4 structure for table `userrole`
--
DROP TABLE IF EXISTS userrole;
CREATE TABLE userrole (
  userid int(11) NOT NULL,
  roleid int(11) NOT NULL,
  PRIMARY KEY (userid, roleid),
  KEY fk_userrole_roleid_idx (roleid),
  CONSTRAINT fk_userrole_roleid FOREIGN KEY (roleid) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_userrole_userid FOREIGN KEY (userid) REFERENCES user (userId) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table 'userrole'
--
LOCK TABLES userrole WRITE;
INSERT INTO userrole values(1,1);
INSERT INTO userrole values(2,1);
INSERT INTO userrole values(3,1);
INSERT INTO userrole values(4,1);
INSERT INTO userrole values(5,1);
INSERT INTO userrole values(6,1);
INSERT INTO userrole values(7,1);
INSERT INTO userrole values(8,1);
INSERT INTO userrole values(9,1);
INSERT INTO userrole values(10,1);
INSERT INTO userrole values(11,1);
INSERT INTO userrole values(12,1);
INSERT INTO userrole values(13,1);
INSERT INTO userrole values(14,1);
INSERT INTO userrole values(15,1);
INSERT INTO userrole values(1,2);
INSERT INTO userrole values(2,2);
UNLOCK TABLES;
	
--
-- Table5 structure for table 'itemcategories'
--
DROP TABLE IF EXISTS itemcategories;
CREATE TABLE itemcategories (
  itemcid int(11) NOT NULL AUTO_INCREMENT,
  itemcname varchar(200) DEFAULT NULL,
  PRIMARY KEY (itemcid));

--
-- Dumping data for table 'itemcategories'
--
LOCK TABLES itemcategories WRITE;
INSERT INTO itemcategories values(1,'Books');
INSERT INTO itemcategories values(2,'Furniture');
INSERT INTO itemcategories values(3,'Car');
INSERT INTO itemcategories values(4,'Appliances');
INSERT INTO itemcategories values(5,'Computers');
INSERT INTO itemcategories values(6,'Paper');
INSERT INTO itemcategories values(7,'Electronics');
INSERT INTO itemcategories values(8,'Stationary');
INSERT INTO itemcategories values(9,'Hardware');
INSERT INTO itemcategories values(10,'Software');
INSERT INTO itemcategories values(11,'Kitchen');
INSERT INTO itemcategories values(12,'Living Accessories');
INSERT INTO itemcategories values(13,'Auto Accessories');
INSERT INTO itemcategories values(14,'Electrical');
INSERT INTO itemcategories values(15,'Gardening Tools');
UNLOCK TABLES;

--
-- Table6 structure for table 'status'
--
DROP TABLE IF EXISTS status;
CREATE TABLE status (
  statusid int(11) NOT NULL AUTO_INCREMENT,
  statusname varchar(100) DEFAULT NULL,
  PRIMARY KEY (statusid));

--
-- Dumping data for table 'status'
--
LOCK TABLES status WRITE;
INSERT INTO status values(1000,'available');
INSERT INTO status values(2000,'sold');
UNLOCK TABLES;

--
-- Table7 structure for table 'items'
--
DROP TABLE IF EXISTS items;
CREATE TABLE items (
  itemid bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(300) DEFAULT NULL,
  statusid int(11) NOT NULL,
  description varchar(3000) DEFAULT NULL,
  itemworth decimal(15,2) DEFAULT NULL,
  itemcid int(11) NOT NULL,
  enduserid int(11) NULL,
  solddate datetime NULL,
  addeddate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  addedby int(11) NOT NULL,
  rowstate char(1) DEFAULT NULL,
  PRIMARY KEY (itemid),
  KEY fk_itemstb_itemcategoriestb (itemcid),
  KEY fk_itemtb_statustb (statusid),
  KEY fk_itemtb_userstb_addedby (addedby),
  CONSTRAINT fk_itemstb_itemcategoriestb FOREIGN KEY (itemcid) REFERENCES itemcategories (itemcid) ON DELETE CASCADE,
  CONSTRAINT fk_itemtb_statustb FOREIGN KEY (statusid) REFERENCES status (statusid) ON DELETE CASCADE,
  CONSTRAINT fk_itemtb_userstb_addedby FOREIGN KEY (addedby) REFERENCES user (userId)ON DELETE CASCADE);

INSERT INTO items values(100,'Bedframe',2000,'bedframe for sale','100.00',2,3,'2016-10-23','2016-10-21',1,'a');
INSERT INTO items values(101,'Bar Stool',1000,'Kitchen Items sale','30.00',2,NULL,NULL,'2016-09-21',1,'a');
INSERT INTO items values(102,'Core Java Text Book',1000,'Core java book','150.00',1,NULL,NULL,'2015-11-11',1,'a');
INSERT INTO items values(103,'Honda Accord Car',2000,'second hand car for sale','4000.00',3,2,'2015-10-19','2015-09-09',1,'a');
INSERT INTO items values(104,'Office Chair',2000,'office chair for sale','100.00',2,2,'2016-09-09','2016-08-08',1,'a');
INSERT INTO items values(105,'C++ Text',1000,'C++ book for sale','100.00',1,NULL,NULL,'2016-07-07',2,'a');
INSERT INTO items values(106,'Electric Cooker',2000,'Cooker for sale','550.00',4,3,'2016-09-09','2016-06-06',2,'a');
INSERT INTO items values(107,'Dell Laptop',1000,'i5 1TB 6gb RAM','300.00',5,NULL,NULL,'2016-05-05',2,'a');
INSERT INTO items values(108,'LG TV',1000,'48 inch smart tv','300.00',4,NULL,NULL,'2016-04-04',2,'a');
INSERT INTO items values(109,'Samsung TV',2000,'50 inch LED tv','100.00',4,1,'2016-10-23','2016-10-21',2,'a');
INSERT INTO items values(110,'Study Table',1000,'Study table for sale','30.00',2,NULL,NULL,'2016-09-21',2,'a');
INSERT INTO items values(111,'Toyota Corolla',1000,'Second hand good condition corolla..','150.00',3,NULL,NULL,'2015-11-11',3,'a');
INSERT INTO items values(112,'Logitech Mouse',2000,'Mouse for sale','4000.00',5,2,'2015-10-19','2015-09-09',3,'a');
INSERT INTO items values(113,'CPU',2000,'Dell PC CPU','100.00',5,2,'2016-09-09','2016-08-08',3,'a');
INSERT INTO items values(114,'Datawarehouse Study Guide',1000,'Datawarehouse materials','100.00',1,NULL,NULL,'2016-07-07',3,'a');
INSERT INTO items values(115,'Car Accessories',2000,'Car Accessories for sale','550.00',3,1,'2016-09-09','2016-06-06',3,'a');

--
-- Table8 structure for table 'rating'
--
DROP TABLE IF EXISTS rating;
CREATE TABLE rating (
  ratingid int(11) NOT NULL AUTO_INCREMENT,
  itemid bigint(20) NOT NULL,
  rating decimal(2,1) DEFAULT NULL,
  addeddate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ratingid),
  KEY fk_ratingtb_itemstb (itemid),
  CONSTRAINT fk_ratingtb_itemstb FOREIGN KEY (itemid) REFERENCES items (itemid) ON DELETE CASCADE);

--
-- Dumping data for table 'rating'
--
LOCK TABLES rating WRITE;
INSERT INTO rating values(1,100,5.0,'2016-11-09');
INSERT INTO rating values(2,103,4.5,'2015-10-20');
INSERT INTO rating values(3,104,4.0,'2016-09-10');
UNLOCK TABLES;

--
-- Table9 structure for table 'emailmessage'
--
DROP TABLE IF EXISTS emailmessage;
CREATE TABLE emailmessage (
  messageid bigint(20) NOT NULL AUTO_INCREMENT,
  message varchar(10000) DEFAULT NULL,
  fromuserid int(11) NOT NULL,
  touserid int(11) NOT NULL,
  addeddate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rowstate char(1) DEFAULT NULL,
  PRIMARY KEY (messageid),  
  KEY fk_messagetb_userstb_fromuser (fromuserid),
  KEY fk_messagetb_userstb_touser (touserid),
  CONSTRAINT fk_messagetb_userstb_fromuser FOREIGN KEY (fromuserid) REFERENCES user (userId) ON DELETE CASCADE,
  CONSTRAINT fk_messagetb_userstb_touser FOREIGN KEY (touserid) REFERENCES user (userId) ON DELETE CASCADE);

--
-- Dumping data for table 'emailmessage'
--
LOCK TABLES emailmessage WRITE;
INSERT INTO emailmessage values(1000,'Hi',1,2,'2016-10-21','A');
INSERT INTO emailmessage values(1001,'Hello',1,3,'2016-11-11','A');
INSERT INTO emailmessage values(1002,'Hola!',1,4,'2016-09-09','D');
INSERT INTO emailmessage values(1003,'Hey there',1,5,'2016-08-08','A');
INSERT INTO emailmessage values(1004,'Knock Knock!',2,1,'2016-07-07','A');
INSERT INTO emailmessage values(1005,'Hey James!',2,3,'2016-07-07','A');
INSERT INTO emailmessage values(1006,'Can we meet up to discuss on your post?',2,4,'2016-07-07','A');
INSERT INTO emailmessage values(1007,'What is the total cost of the items on your post?',2,5,'2016-07-07','A');
INSERT INTO emailmessage values(1008,'Hi',1,10,'2016-10-21','A');
INSERT INTO emailmessage values(1009,'Hello',11,3,'2016-11-11','A');
INSERT INTO emailmessage values(1010,'Hola!',1,14,'2016-09-09','D');
INSERT INTO emailmessage values(1011,'Hey there',11,5,'2016-08-08','A');
INSERT INTO emailmessage values(1012,'Knock Knock!',12,11,'2016-07-07','A');
INSERT INTO emailmessage values(1013,'Hey John!',12,13,'2016-07-07','A');
INSERT INTO emailmessage values(1014,'Can we meet up to discuss on your post?',12,14,'2016-07-07','A');
INSERT INTO emailmessage values(1015,'What is the total cost of the items on your post?',2,12,'2016-07-07','A');
UNLOCK TABLES;

--
-- Table10 structure for table 'websitefeedback'
--
DROP TABLE IF EXISTS websitefeedback;
CREATE TABLE websitefeedback (
  feedbackid int(11) NOT NULL AUTO_INCREMENT,
  description varchar(3000) DEFAULT NULL,
  addedby int(11) DEFAULT NULL,
  addeddate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (feedbackid),
  KEY fk_websitefeedbacktb_userstb (addedby),
  CONSTRAINT fk_websitefeedbacktb_userstb FOREIGN KEY (addedby) REFERENCES user (userId) ON DELETE CASCADE
);

--
-- Dumping data for table 'websitefeedback'
--
LOCK TABLES websitefeedback WRITE;
INSERT INTO websitefeedback values(1,'Good UI',1,'2016-10-10');
INSERT INTO websitefeedback values(2,'Easy to use',1,'2016-09-10');
INSERT INTO websitefeedback values(3,'Not very complicated',3,'2016-08-10');
UNLOCK TABLES;