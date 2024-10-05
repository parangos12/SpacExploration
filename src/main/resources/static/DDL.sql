-- Creation of crew members table
CREATE TYPE enum_gender AS ENUM ('Male','Female','Other');
CREATE TYPE enum_civil_status AS ENUM ('Married','Single','Widowed','Divorced','Other');

CREATE TABLE crew_member(
	id varchar(30) PRIMARY KEY,
	family_id integer NOT NULL,
	name varchar(60) NOT NULL,
	surname varchar(60) NOT NULL,
	age smallint NOT NULL,
	gender enum_gender,
	civil_status enum_civil_status,
	children smallint,
	salary decimal,
	trips_achieved smallint
);

-- Creation of spaceship table
CREATE TABLE spaceship(
	id integer PRIMARY KEY,
	capacity integer NOT NULL,
	cabin_capacity integer NOT NULL
);

-- Creation of exploration table
CREATE TABLE exploration(
	id integer,
	spaceship_id integer NOT NULL,
	cabin_id integer NOT NULL,
	crew_member_id varchar(30) NOT NULL,
	PRIMARY KEY(id, cabin_id),
	FOREIGN KEY (spaceship_id) REFERENCES spaceship(id) ,
	FOREIGN KEY (crew_member_id) REFERENCES crew_member(id)
) PARTITION BY HASH (cabin_id);

CREATE INDEX idx_exploration_cabin_id ON exploration(cabin_id);

