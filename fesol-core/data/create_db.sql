/*
-- Revoke privileges from 'public' role
REVOKE CREATE ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON DATABASE mydatabase FROM PUBLIC;

-- Read-only role
CREATE ROLE readonly;
GRANT CONNECT ON DATABASE mydatabase TO readonly;
GRANT USAGE ON SCHEMA myschema TO readonly;
GRANT SELECT ON ALL TABLES IN SCHEMA myschema TO readonly;
ALTER DEFAULT PRIVILEGES IN SCHEMA myschema GRANT SELECT ON TABLES TO readonly;

-- Read/write role
CREATE ROLE readwrite;
GRANT CONNECT ON DATABASE mydatabase TO readwrite;
GRANT USAGE, CREATE ON SCHEMA myschema TO readwrite;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA myschema TO readwrite;
ALTER DEFAULT PRIVILEGES IN SCHEMA myschema GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO readwrite;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA myschema TO readwrite;
ALTER DEFAULT PRIVILEGES IN SCHEMA myschema GRANT USAGE ON SEQUENCES TO readwrite;

-- Users creation
CREATE USER reporting_user1 WITH PASSWORD 'some_secret_passwd';
CREATE USER reporting_user2 WITH PASSWORD 'some_secret_passwd';
CREATE USER app_user1 WITH PASSWORD 'some_secret_passwd';
CREATE USER app_user2 WITH PASSWORD 'some_secret_passwd';

-- Grant privileges to users
GRANT readonly TO reporting_user1;
GRANT readonly TO reporting_user2;
GRANT readwrite TO app_user1;
GRANT readwrite TO app_user2;
*/

CREATE TABLE user_account (
	id VARCHAR(255) not null,
	username VARCHAR(255) not null,
    password VARCHAR(255) not null,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
    

CREATE TABLE user_profile (
    id integer not null,
    name varchar(255) not null,
    PRIMARY KEY (id)
);

select * from user_profile

CREATE TABLE user_account_user_profile (
  user_account_id VARCHAR(255) NOT NULL,
  user_profile_id INTEGER NOT NULL,
  CONSTRAINT user_account_user_profile_fk1 FOREIGN KEY (user_account_id) REFERENCES user_account(id),
  CONSTRAINT user_account_user_profile_fk2 FOREIGN KEY (user_profile_id) REFERENCES user_profile(id),
  PRIMARY KEY (user_account_id, user_profile_id)
);

INSERT INTO USER_PROFILE VALUES (1, 'ADMIN');
INSERT INTO USER_PROFILE VALUES (2, 'ISSUER');
INSERT INTO USER_PROFILE VALUES (3, 'RECEIVER');

INSERT INTO USER_ACCOUNT VALUES ('1', 'jdyanquen', '123', 'Jesus', 'Yanquen', 'jdyanquen@yopmail.com');
INSERT INTO USER_ACCOUNT VALUES ('2', 'dacorrea', '123', 'David', 'Correa', 'dacorrea@yopmail.com');
INSERT INTO USER_ACCOUNT VALUES ('3', 'latorres', '123', 'Laura', 'Torres', 'latorres@yopmail.com');

INSERT INTO USER_ACCOUNT_USER_PROFILE(user_account_id, user_profile_id) VALUES ('1', 1);
INSERT INTO USER_ACCOUNT_USER_PROFILE(user_account_id, user_profile_id) VALUES ('2', 2);
INSERT INTO USER_ACCOUNT_USER_PROFILE(user_account_id, user_profile_id) VALUES ('3', 3);
