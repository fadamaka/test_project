-- Delete scrips

-- DROP DATABASE "test";
-- DROP USER test;

-- USER: test

CREATE USER test WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD '12345';

COMMENT ON ROLE test IS 'Access for Java Test.';

-- Database: test

CREATE DATABASE "test"
    WITH 
    OWNER = test
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "test"
    IS 'Database for the application.';
