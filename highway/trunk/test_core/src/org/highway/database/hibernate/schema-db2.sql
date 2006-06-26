drop table db2admin.MAPPING_TEST;
drop table db2admin.TRIM_TEST;

create table db2admin.MAPPING_TEST (
	PRIMITIVE_LONG bigint not null,
	PRIMITIVE_BOOLEAN char not null,
	PRIMITIVE_CHAR char not null,
	PRIMITIVE_DOUBLE integer not null,
	PRIMITIVE_FLOAT integer not null,
	PRIMITIVE_INT integer not null,
	PRIMITIVE_SHORT integer not null,
	LONG bigint,
	BOOLEAN char,
	CHARACTER char,
	DOUBLE integer,
	FLOAT integer,
	INTEGER integer,
	SHORT integer,
	STRING varchar(20),
	SQL_DATE date,
	UTIL_DATE timestamp,
	TIME time,
	TIMESTAMP timestamp,
	BIG_DECIMAL decimal(10,2),
	BIG_INTEGER decimal(10,2),
	DECIMAL decimal(10,2),
	CHAR_ENUM char,
	SHORT_ENUM integer,
	STRING_ENUM char(2),
	BLOB blob(100),
	CLOB clob(100),
	primary key (PRIMITIVE_LONG)
);

create table db2admin.TRIM_TEST (
	ID bigint not null,
	PROPERTY_1 char(20),
	PROPERTY_2 char(20),
	PROPERTY_3 char(20),
	primary key (ID)
);

commit;
