--DROP TABLE test_project.listing;
--DROP TABLE test_project.marketplace;
--DROP TABLE test_project.location;
--DROP TABLE test_project.listing_status;

--DROP SCHEMA test_project ;

-- SCHEMA: test_project

CREATE SCHEMA test_project
    AUTHORIZATION test_project;

COMMENT ON SCHEMA test_project
    IS 'Schema of the application.';
	
-- Table: test_project.marketplace

CREATE TABLE test_project.marketplace
(
    id numeric(1,0) NOT NULL,
    marketplace_name character varying NOT NULL,
    CONSTRAINT marketplace_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE test_project.marketplace
    OWNER to test_project;
COMMENT ON TABLE test_project.marketplace
    IS 'Table for Marketplaces.';
	
-- Table: test_project.location

CREATE TABLE test_project.location
(
    id uuid NOT NULL,
    manager_name character varying,
    phone character varying,
    address_primary character varying,
    address_secondary character varying,
    country character varying,
    town character varying,
    postal_code character varying,
    CONSTRAINT location_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE test_project.location
    OWNER to test_project;
COMMENT ON TABLE test_project.location
    IS 'Table for locations.';
	
-- Table: test_project.listing_status

CREATE TABLE test_project.listing_status
(
    id numeric(1,0) NOT NULL,
    status_name character varying NOT NULL,
    CONSTRAINT listing_status_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE test_project.listing_status
    OWNER to test_project;
COMMENT ON TABLE test_project.listing_status
    IS 'Table for Listing Statuses.';

-- Table: test_project.listing

CREATE TABLE test_project.listing
(
    id uuid NOT NULL,
    title character varying NOT NULL,
    description character varying NOT NULL,
    location_id uuid NOT NULL,
    listing_price numeric(10,2) CHECK (listing_price > 0) NOT NULL,
    currency char(3) NOT NULL,
    quantity numeric CHECK (quantity > 0) NOT NULL,
    listing_status numeric NOT NULL,
    marketplace numeric NOT NULL,
    upload_time date NOT NULL,
    owner_email_address character varying,
    CONSTRAINT listing_pkey PRIMARY KEY (id),
    CONSTRAINT listing_status_fkey FOREIGN KEY (listing_status)
        REFERENCES test_project.listing_status (id),
    CONSTRAINT location_fkey FOREIGN KEY (location_id)
        REFERENCES test_project.location (id),
    CONSTRAINT marketplace_fkey FOREIGN KEY (marketplace)
        REFERENCES test_project.marketplace (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE test_project.listing
    OWNER to test_project;
COMMENT ON TABLE test_project.listing
    IS 'Table for listings.';