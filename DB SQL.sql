DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS account_holder CASCADE;
DROP TYPE IF EXISTS user_role;
DROP TYPE IF EXISTS status;

CREATE TYPE user_role AS ENUM ('customer', 'admin', 'employee');

CREATE TYPE status AS ENUM ('pending', 'active', 'closed');

CREATE TABLE person (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	username VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(320),
	pwd VARCHAR(35) NOT NULL,
	first_name VARCHAR(45) NOT NULL,
	last_name VARCHAR(45) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	user_role user_role NOT NULL
);

CREATE TABLE address (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	user_id BIGINT NOT NULL UNIQUE, -- one person should ONLY list one main address
	street VARCHAR(100) NOT NULL,
	city VARCHAR(40) NOT NULL,
	state VARCHAR(2) NOT NULL,
	zip VARCHAR(5),
	FOREIGN KEY (user_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE account (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	balance BIGINT DEFAULT 0,
	status status NOT NULL DEFAULT 'pending'
);

CREATE TABLE account_holder (
	user_id BIGINT NOT NULL,
	account_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES person(id) ON DELETE CASCADE,
	FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

-- my data
INSERT INTO person
(username, email, pwd, first_name, last_name, phone, user_role)
VALUES
('jdoe454', 'jdoe@google.com', 'password', 'John', 'Doe', '1234567899', 'customer'),
('admin', 'smdaniel@ykz.com', 'password', 'Daniel', 'Smith', '1234567899', 'admin'),
('sklein', 'sklein@ykz.com', 'password', 'Sophia', 'Klein', '1234567899', 'employee'),
('lpeters', 'peterslau@verizon.net', 'password', 'Lauren', 'Peterson', '4132642985', 'customer'),
('jpeters', 'jpeter@gmail.com', 'password', 'Jake', 'Peterson', '4132648529', 'customer');

-- my data 
INSERT INTO address (user_id, street, city, state, zip) VALUES(1, '404 Example Rd', 'New York', 'NY', '10017'); 
INSERT INTO address (user_id, street, city, state, zip) VALUES(2, '403 Example Rd', 'New York', 'NY', '10017'); 
INSERT INTO address (user_id, street, city, state, zip) VALUES( 3, '8917 Turcotte Tunnel Apt. 175', 'Morrisberg', 'VT', '63234' ); 
INSERT INTO address (user_id, street, city, state, zip) VALUES( 4, '1700 Zula Summit', 'Stantontown', 'NC', '27817' ); 
INSERT INTO address (user_id, street, city, state, zip) VALUES( 5, '1700 Zula Summit', 'Stantontown', 'NC', '27817' );

--my data
BEGIN;
INSERT INTO account (user_id, balance, status) VALUES(1, 10000, 'active');
INSERT INTO account(user_id, balance, status) VALUES(4, 5412300, 'active');
INSERT INTO account (user_id, balance, status) VALUES(5, 514067, 'active');
INSERT INTO account (user_id, balance, status) VALUES(5, 514067, 'active');
INSERT INTO account (user_id, balance, status) VALUES(1, 10000, 'pending');
INSERT INTO account_holder (user_id, account_id) VALUES (1, 1);
INSERT INTO account_holder (user_id, account_id) VALUES (1, 5);
-- Jake Peters and Lauren Peters have a joint account
INSERT INTO account_holder (user_id, account_id) VALUES (4, 2), (5, 2);
-- Jake also has account of his own
INSERT INTO account_holder (user_id, account_id) VALUES (5, 3);
END;

-- random data
insert into person (username, email, pwd, first_name, last_name, phone, user_role) values ('jparffrey0', 'jparffrey0@newyorker.com', 'password', 'Jewell', 'Parffrey', '3305388174', 'customer');
insert into person (username, email, pwd, first_name, last_name, phone, user_role) values ('jsayward1', 'jsayward1@earthlink.net', 'password', 'Jackie', 'Sayward', '9542625960', 'customer');
insert into person (username, email, pwd, first_name, last_name, phone, user_role) values ('bgofford2', 'bgofford2@symantec.com', 'password', 'Bunny', 'Gofford', '5525158800', 'customer');
insert into person (username, email, pwd, first_name, last_name, phone, user_role) values ('leardley3', 'leardley3@hibu.com', 'password', 'Lucian', 'Eardley', '5047324000', 'customer');
insert into person (username, email, pwd, first_name, last_name, phone, user_role) values ('ssibthorp4', 'ssibthorp4@github.com', 'password', 'Sisile', 'Sibthorp', '1366630935', 'customer');

-- random data
insert into address (user_id, street, city, state, zip) values (8, '35792 Grayhawk Terrace', 'Chicago', 'IL', '60609');
insert into address (user_id, street, city, state, zip) values (9, '8307 Armistice Hill', 'Tampa', 'FL', '33686');
insert into address (user_id, street, city, state, zip) values (7, '1824 West Park', 'Pittsburgh', 'PA', '15205');
insert into address (user_id, street, city, state, zip) values (6, '093 Redwing Point', 'Panama City', 'FL', '32405');
insert into address (user_id, street, city, state, zip) values (10, '9 Kensington Junction', 'Fort Pierce', 'FL', '34981');

-- random data
BEGIN;
insert into account (user_id, balance, status) values (8, 762998, 'active');
insert into account (user_id, balance, status) values (10, 0, 'closed');
insert into account (user_id, balance, status) values (6, 7496489, 'active');
insert into account (user_id, balance, status) values (7, 9278221, 'active');
insert into account (user_id, balance, status) values (9, 1486440, 'pending');

insert into account_holder (user_id, account_id) values (6, 6);
insert into account_holder (user_id, account_id) values (7, 7);
insert into account_holder (user_id, account_id) values (8, 8);
insert into account_holder (user_id, account_id) values (9, 9);
insert into account_holder (user_id, account_id) values (10, 10);
END;


------ CREATING STORED FUNCTION , for transfering money --------

DROP FUNCTION transfer_money(bigint,bigint,bigint);

CREATE OR REPLACE FUNCTION transfer_money(src_account_id BIGINT, dest_account_id BIGINT, amount BIGINT)
RETURNS boolean
LANGUAGE plpgsql
AS $$

DECLARE 
	acc_count int;
	src_bal bigint;

BEGIN
	SELECT count(*) INTO acc_count 
    FROM account 
    WHERE (id = src_account_id OR id = dest_account_id)
    AND status = 'active';
   
	IF acc_count = 2 THEN
		SELECT balance INTO src_bal FROM account WHERE id = src_account_id;
		IF amount <= src_bal THEN
			UPDATE account SET balance = balance - amount WHERE id = src_account_id;
			UPDATE account SET balance = balance + amount WHERE id = dest_account_id;
			RETURN true;
		END IF;
	END IF;
	RETURN FALSE;
END;
$$