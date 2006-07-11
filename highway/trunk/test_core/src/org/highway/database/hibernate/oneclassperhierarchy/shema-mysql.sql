drop table highway.PAYMENT1;
create table highway.PAYMENT1 (
	ID INTEGER(10) primary key,
	ORDERNAME VARCHAR(20),
	CARDTYPE VARCHAR(20),
	CURRENCY VARCHAR(10),
	AMOUNT VARCHAR(20),
	PAYMENT_TYPE VARCHAR(20) not null
);
