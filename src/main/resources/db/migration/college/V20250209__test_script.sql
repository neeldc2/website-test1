CREATE TABLE IF NOT EXISTS test (
	id binary(16) default (uuid_to_bin(uuid())) NOT NULL PRIMARY KEY
);