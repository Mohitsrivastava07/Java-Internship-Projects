create database studentresult_db;

use studentresult_db;

create table newstudent_tb (
	id int primary key auto_increment,
	name varchar(255) not null,
    total int not null,
    percentage double not null,
	result varchar(10) not null
);