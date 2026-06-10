-- create the database of bankmanagement_db
create database bankmanagement_db;

-- used bankmanagement_db
use bankmanagement_db;

-- create the table of createaccount
create table createaccount (
	account_number bigint primary key not null,
    name varchar(255) not null,
    age int(3) not null,
    mobile_number varchar(10) not null,
    address varchar(355) not null,
    account_type enum ('saving', 'current') not null,
    aadhaar_number bigint(12) not null,
    balance double not null,
    date_time timestamp default current_timestamp
);

-- create the table of deposits
create table deposits (
	deposit_id varchar(5) primary key not null,
    account_number bigint(14) not null,
    deposits_amount double not null,
    deposit_date timestamp default current_timestamp,
    foreign key (account_number) references createaccount(account_number) on delete cascade
);

-- create the table of withdrawals
create table withdrawals (
	withdrawal_id varchar(5) primary key not null,
    account_number bigint(14) not null,
    withdrawal_amount double not null,
    withdrawal_date timestamp default current_timestamp,
    foreign key (account_number) references createaccount(account_number) on delete cascade
);

-- create the table of transaction_history
create table transactionhistory (
	transaction_id varchar(5) primary key not null,
    account_number bigint(14) not null,
    transaction_type enum ('online', 'cash') not null,
    transaction_amount double not null,
    transaction_date timestamp default current_timestamp,
    foreign key (account_number) references createaccount(account_number) on delete cascade
);

-- Create trigger for deposits
DROP TRIGGER IF EXISTS before_insert_deposits;
DELIMITER $$
CREATE TRIGGER before_insert_deposits
BEFORE INSERT ON deposits
FOR EACH ROW
BEGIN
    DECLARE next_id INT;
    SELECT IFNULL(MAX(CAST(SUBSTRING(deposit_id, 2) AS UNSIGNED)), 0) + 1
    INTO next_id
    FROM deposits;
    SET NEW.deposit_id = CONCAT('D', LPAD(next_id, 5, '0'));
END$$
DELIMITER ;

-- Create trigger for withdrawals
DROP TRIGGER IF EXISTS before_insert_withdrawals;
DELIMITER $$
CREATE TRIGGER before_insert_withdrawals
BEFORE INSERT ON withdrawals
FOR EACH ROW
BEGIN
    DECLARE next_id INT;
    SELECT IFNULL(MAX(CAST(SUBSTRING(withdrawal_id, 2) AS UNSIGNED)), 0) + 1
    INTO next_id
    FROM withdrawals;
    SET NEW.withdrawal_id = CONCAT('W', LPAD(next_id, 5, '0'));
END$$
DELIMITER ;

-- Create trigger for transactionhistory
DROP TRIGGER IF EXISTS before_insert_transactionhistory;
DELIMITER $$
CREATE TRIGGER before_insert_transactionhistory
BEFORE INSERT ON transactionhistory
FOR EACH ROW
BEGIN
    DECLARE next_id INT;
    SELECT IFNULL(MAX(CAST(SUBSTRING(transaction_id, 2) AS UNSIGNED)), 0) + 1
    INTO next_id
    FROM transactionhistory;
    SET NEW.transaction_id = CONCAT('T', LPAD(next_id, 5, '0'));
END$$
DELIMITER ;

-- retrieve the all table
select * from createaccount;
select * from deposits;
select * from withdrawals;
select * from transactionhistory;

-- view triggers tables
show triggers;

-- checking the description of all table 
describe deposits;
describe withdrawals;
describe transactionhistory;

-- modify the deposit_id varchar(5) not null to deposit_id varchar(6) not null
ALTER TABLE deposits MODIFY deposit_id VARCHAR(6) NOT NULL;

-- modify the withdrawal_id varchar(5) not null to withdrawal_id varchar(6) not null
ALTER TABLE withdrawals MODIFY withdrawal_id VARCHAR(6) NOT NULL;

-- modify the transaction_id varchar(5) not null to transaction_id varchar(6) not null
ALTER TABLE transactionhistory MODIFY transaction_id VARCHAR(6) NOT NULL;
