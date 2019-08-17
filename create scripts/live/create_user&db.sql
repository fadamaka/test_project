-- USER: test_project
-- DROP DATABASE "DB";
-- DROP USER test_project;

CREATE USER test_project WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD '12345';

COMMENT ON ROLE test_project IS 'Access for Java application.';

-- Database: DB

CREATE DATABASE "DB"
    WITH 
    OWNER = test_project
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "DB"
    IS 'Database for the application.';
	