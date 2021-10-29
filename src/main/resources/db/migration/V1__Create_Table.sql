CREATE DATABASE IF NOT EXISTS lms;

CREATE TABLE IF NOT EXISTS `login` (
    `employee_id` INT NOT NULL,
    `email` VARCHAR(30) NOT NULL,
	`created_timestamp` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_timestamp`  TIMESTAMP,
	`created_by` VARCHAR (255),
	`updated_by` VARCHAR (255),
    PRIMARY KEY(`employee_id`)
);

CREATE TABLE IF NOT EXISTS `department` (
    `department_id` int NOT NULL,
    `department_name` varchar(255)  NOT NULL,
    `head_id` varchar(255),
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

use lms;
INSERT INTO `department` (`department_id`,`department_name`,`head_id`,`created_timestamp`,`updated_timestamp`,`created_by`,`updated_by`) VALUES(1,'ComputerScience','4545',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'admin','admin'); 


INSERT INTO `user` (`employee_id`,`first_name` ,`middle_name` ,`last_name` ,`phone_number` ,`gender` ,`dob` ,`date_of_joining` ,`is_permanent` ,`department_id` ,`role` ,`address` ,`created_timestamp` ,`updated_timestamp` ,`created_by` ,`updated_by` ,`is_active`) VALUES (1,'ARJUN','KRISHNA','SHENOY','121212121','M','1994-03-09','1994-03-09','1',1,'Staff','asasasasa',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin','admin', '1');



INSERT INTO `user` (`employee_id`,`first_name` ,`middle_name` ,`last_name` ,`phone_number` ,`gender` ,`dob` ,`date_of_joining` ,`is_permanent` ,`department_id` ,`role` ,`address` ,`created_timestamp` ,`updated_timestamp` ,`created_by` ,`updated_by` ,`is_active`) VALUES (2,'CHINMAY','K','JOSE','121212121','M','1994-03-09','1994-03-09','1',1,'Staff','asasasasa',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin','admin', '1');






