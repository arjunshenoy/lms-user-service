CREATE TABLE IF NOT EXISTS `department` (
    `department_id` int NOT NULL,
    `department_name` varchar(255)  NOT NULL,
    `head_id` INT (255),
	`created_timestamp` TIMESTAMP,
	`updated_timestamp` TIMESTAMP,
	`created_by` VARCHAR (255),
	`updated_by` VARCHAR (255),
	PRIMARY KEY (`department_id`)
);

CREATE TABLE  IF NOT EXISTS `user` (
    `employee_id` int  NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255)  NOT NULL,
    `middle_name` varchar(255),
    `last_name` varchar(255)  NOT NULL,
	`phone_number` varchar(15),
	`email` varchar(255),
	`gender` varchar(15),
	`dob` Date  NOT NULL,
	`date_of_joining` DATE  NOT NULL,
	`is_permanent` BOOLEAN,
	`department_id` int,
	`role` varchar(255),
    `address` varchar(255),
	`created_timestamp` TIMESTAMP,
	`updated_timestamp` TIMESTAMP,
	`created_by` VARCHAR (255),
	`updated_by` VARCHAR (255),
    `is_active` BOOLEAN,
	 PRIMARY KEY (`employee_id`),
     FOREIGN KEY (`department_id`) REFERENCES department(`department_id`)
);


CREATE TABLE IF NOT EXISTS `notification` (
    `notification_id` INT NOT NULL,
    `employee_id`INT NOT NULL,
    `message` VARCHAR(30) NOT NULL,
    `is_read` BOOLEAN DEFAULT FALSE,
    `title` VARCHAR(30) NOT NULL,
    `date` DATE,
    PRIMARY KEY(`notification_id`)
);


CREATE TABLE IF NOT EXISTS `substitution` (
	`substitute_id` INT NOT NULL,
	`leave_id` INT NOT NULL,
	`date_of_approval` DATE NOT NULL,
	PRIMARY KEY (`substitute_id`)
);

CREATE TABLE `login` (
    `employee_id` INT NOT NULL,
    `username` VARCHAR(128) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `active` BOOLEAN DEFAULT TRUE,
    `roles` VARCHAR(512), 
    `created_ts` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(`username`)
);


INSERT INTO login (employee_id,username, password,active, roles, created_ts) VALUES (1,'admin','$2a$10$/A05G9vqMiWmWnFTxa8zDu5IbVhAx19XQ6V4I6ZO6aqClaNpwGFrW',true,'ADMIN', current_timestamp());
INSERT INTO login (employee_id,username, password,active, roles, created_ts) VALUES (2,'arjun','qwerty',true,'STAFF', current_timestamp());
INSERT INTO login (employee_id,username, password,active, roles, created_ts) VALUES (3,'chinmay','qwertyu',true,'ADMIN', current_timestamp());




INSERT INTO `department` (`department_id`,`department_name`,`head_id`,`created_timestamp`,`updated_timestamp`,`created_by`,`updated_by`) VALUES(1,'ComputerScience',2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'admin','admin'); 

INSERT INTO `user` (`employee_id`,`first_name` ,`middle_name` ,`last_name` ,`phone_number`,`email` ,`gender` ,`dob` ,`date_of_joining` ,`is_permanent` ,`department_id` ,`role` ,`address` ,`created_timestamp` ,`updated_timestamp` ,`created_by` ,`updated_by` ,`is_active`) VALUES (1,'ARJUN','KRISHNA','SHENOY','121212121','arjunkshenoy.mec@gmail.com','M','1994-03-09','1994-03-09','1',1,'Staff','asasasasa',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin','admin', '1');



INSERT INTO `user` (`employee_id`,`first_name` ,`middle_name` ,`last_name` ,`phone_number` ,`email`,`gender` ,`dob` ,`date_of_joining` ,`is_permanent` ,`department_id` ,`role` ,`address` ,`created_timestamp` ,`updated_timestamp` ,`created_by` ,`updated_by` ,`is_active`) VALUES (2,'CHINMAY','K','JOSE','121212121','aksv1994@gmail.com','M','1994-03-09','1994-03-09','1',1,'HOD','asasasasa',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin','admin', '1');






