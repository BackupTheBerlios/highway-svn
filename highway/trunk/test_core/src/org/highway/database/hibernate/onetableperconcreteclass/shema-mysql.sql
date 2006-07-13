drop table highway.CREDIT_PAYMENT3;
create table highway.CREDIT_PAYMENT3 (
	ID INTEGER(10) primary key,
	AMOUNT VARCHAR(20),
	CARDTYPE VARCHAR(20)
);
drop table highway.CASH_PAYMENT3;
create table highway.CASH_PAYMENT3 (
	ID INTEGER(10) primary key,
	AMOUNT VARCHAR(20),
	CURRENCY VARCHAR(10)
);
drop table highway.CHEQUE_PAYMENT3;
create table highway.CHEQUE_PAYMENT3 (
	ID INTEGER(10) primary key,
	AMOUNT VARCHAR(20),
	ORDERNAME VARCHAR(20)
);

