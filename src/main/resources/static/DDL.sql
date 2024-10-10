--DROP TABLE crew_member;
--DROP TABLE spaceship;
--DROP TABLE exploration;

-- Creation of crew members table
CREATE TABLE crew_member(
	id varchar(30) PRIMARY KEY,
	family_id integer NOT NULL,
	name varchar(60) NOT NULL,
	surname varchar(60) NOT NULL,
	age smallint NOT NULL,
	gender varchar(20),
	civil_status varchar(20),
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
	cabin_id integer NOT NULL,
	spaceship_id integer NOT NULL,
	crew_member_id varchar(30) NOT NULL,
	PRIMARY KEY(id, cabin_id),
	FOREIGN KEY (spaceship_id) REFERENCES spaceship(id) ,
	FOREIGN KEY (crew_member_id) REFERENCES crew_member(id)
) PARTITION BY RANGE (cabin_id);

--Create the partitions for the table exploration
CREATE TABLE exploration_1_100 PARTITION OF exploration FOR VALUES FROM (1) TO (101);
CREATE TABLE exploration_101_200 PARTITION OF exploration FOR VALUES FROM (101) TO (201);
CREATE TABLE exploration_201_300 PARTITION OF exploration FOR VALUES FROM (201) TO (301);
CREATE TABLE exploration_301_400 PARTITION OF exploration FOR VALUES FROM (301) TO (401);
CREATE TABLE exploration_401_500 PARTITION OF exploration FOR VALUES FROM (401) TO (501);
CREATE TABLE exploration_501_600 PARTITION OF exploration FOR VALUES FROM (501) TO (601);
CREATE TABLE exploration_601_700 PARTITION OF exploration FOR VALUES FROM (601) TO (701);
CREATE TABLE exploration_701_800 PARTITION OF exploration FOR VALUES FROM (701) TO (801);
CREATE TABLE exploration_801_900 PARTITION OF exploration FOR VALUES FROM (801) TO (901);
CREATE TABLE exploration_901_1000 PARTITION OF exploration FOR VALUES FROM (901) TO (1001);

CREATE INDEX idx_exploration_cabin_id ON exploration(cabin_id);

SELECT * FROM crew_member;
SELECT * FROM exploration;
