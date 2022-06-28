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
	pwd VARCHAR(161) NOT NULL,
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
	UNIQUE (user_id, account_id),
	FOREIGN KEY (user_id) REFERENCES person(id) ON DELETE CASCADE,
	FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

-- my data
INSERT INTO person
(username, email, pwd, first_name, last_name, phone, user_role)

VALUES
('jdoe454',
'jdoe@google.com',
'ff61eb9a4a6360f66abee80009b6b5bd2ba9adb17847c9ad9469823421fcd690095c66d31b0c76016be81ccbeb456fe29073e0e35f0f02714e09d8ae156f9915.c2763f8778554788b2ed4197a0314c02',
'John',
'Doe',
'1234567899',
'customer'),
('admin',
'smdaniel@ykz.com',
'6b0ddffa0c47c26c27519fb5f9208f17addac4e0f47d0fa4bf16802cb445b173934dffdaa43b4fec0e22deb5b1a26f5c33c0ebcff17a049f21d3495c171ef351.1b2ce52529f9b7df139bdd4c6caeff3c',
'Daniel',
'Smith',
'1234567899',
'admin'),
('sklein',
'sklein@ykz.com',
'7e2cafbb57440b59ec3f4d4784b6701444dbd32ece60730514955a995fe628e90c65f7d32417624968006ff9a6c5bcbb70c08c3adff0fbe079a811b87a53855e.15a4ee9b91ec4daf84ce19df4a6dec87',
'Sophia',
'Klein',
'1234567899',
'employee'),
('lpeters',
'peterslau@verizon.net',
'6b034ac788b11d85d9cd20ac16a26f8af50c0d520fab94945d2648354ddeea7d8e08fe2685d45295f18b10418121ee936db56160ff98496e4bb35f467367bd66.152abab056dd42b3c29a3119b676365c',
'Lauren',
'Peterson',
'4132642985',
'customer'),
('jpeters',
'jpeter@gmail.com',
'6277606f9a2c5eb3300d41f266d57b79629dc93cbb249257b202afbea6cfec8b1ee8e8e22bf66a038b05b2fb580f2774f2d54d3075ce6b11e2e0bef0d77448cf.9caf43a73d6efa84eff12ad1b8809568',
'Jake',
'Peterson',
'4132648529',
'customer');

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
INSERT
	INTO
	person (username,
	email,
	pwd,
	first_name,
	last_name,
	phone,
	user_role)
VALUES ('jparffrey0',
'jparffrey0@newyorker.com',
'63f5e1516703c1f91fe6e01c1ffb0697d62220aab346b22d32061efcaa3dde75961a2ba1de928fede58251f249725d317b89cfb34ae72a1332d22b06ae0dcc34.153a5cdeaca49e2379aba447b317374c',
'Jewell',
'Parffrey',
'3305388174',
'customer');

INSERT
	INTO
	person (username,
	email,
	pwd,
	first_name,
	last_name,
	phone,
	user_role)
VALUES ('jsayward1',
'jsayward1@earthlink.net',
'e8825e7c46581ac7980bbd92b4ccca2a54f907844a31e3c0268a27532a203eca458ad20dcadd829ac71947f3d8d5841d450372e2fbbf26364029af144da0e24b.588c9047571710ca5e82c648f046e479',
'Jackie',
'Sayward',
'9542625960',
'customer');

INSERT
	INTO
	person (username,
	email,
	pwd,
	first_name,
	last_name,
	phone,
	user_role)
VALUES ('bgofford2',
'bgofford2@symantec.com',
'e6981a17984fc9d4bb79336750d3fa854c0293f86dccc955c69aa083b53d2727e0f4e7102331f09fe4b6d3de7773fee60ffe6c433972d8381ad9905759787a81.fa4e2ce5edf147093d0ffb80714d39d3',
'Bunny',
'Gofford',
'5525158800',
'customer');

INSERT
	INTO
	person (username,
	email,
	pwd,
	first_name,
	last_name,
	phone,
	user_role)
VALUES ('leardley3',
'leardley3@hibu.com',
'3bd2930b542056e991a313efabb9bf1713eabd04383ae89282a718b2f2e5d7fc1ff002192eeb97798b8e6e353389cabc343e816915d66517be1909df5f2a9bf3.337cf180bad620e959cd513779bc2ee6',
'Lucian',
'Eardley',
'5047324000',
'customer');

INSERT
	INTO
	person (username,
	email,
	pwd,
	first_name,
	last_name,
	phone,
	user_role)
VALUES ('ssibthorp4',
'ssibthorp4@github.com',
'54ad0c80e7a29abe9706b72cdd872802b9f921d571fb98980380ecf8b120016ef36c48298713b98a4a84abee5c53b564c9d8facd4ee678a01b2ba9373e44daef.889b003ddecc612172a72ba93a1f6d21',
'Sisile',
'Sibthorp',
'1366630935',
'customer');

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

DROP FUNCTION IF EXISTS transfer_money(bigint,bigint,bigint);

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

DROP FUNCTION IF EXISTS add_account_holder(bigint,bigint);

CREATE OR REPLACE FUNCTION add_account_holder(u_id BIGINT, a_id BIGINT)
RETURNS boolean
LANGUAGE plpgsql
AS $$

DECLARE
	person_count int;
	account_count int;

BEGIN
	SELECT count(*) INTO person_count
    FROM person
    WHERE id = u_id;

	SELECT count(*) INTO account_count
    FROM account
    WHERE id = a_id;

	IF account_count = 1 AND person_count = 1 THEN
		INSERT INTO account_holder (user_id, account_id) VALUES (u_id, a_id);
		RETURN true;
	END IF;
	RETURN FALSE;
END;
$$