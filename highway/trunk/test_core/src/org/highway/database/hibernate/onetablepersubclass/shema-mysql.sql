drop table highway.PAYMENT2;
create table highway.PAYMENT2 (
	ID INTEGER(10) primary key,
	AMOUNT VARCHAR(20)
);
drop table highway.CREDIT_PAYMENT2;
create table highway.CREDIT_PAYMENT2 (
	CARDTYPE VARCHAR(20),
	ID INTEGER(10) not null REFERENCES highway.PAYMENT2(ID)
);
drop table highway.CASH_PAYMENT2;
create table highway.CASH_PAYMENT2 (
	CURRENCY VARCHAR(10),
	ID INTEGER(10) not null REFERENCES highway.PAYMENT2(ID)
);
drop table highway.CHEQUE_PAYMENT2;
create table highway.CHEQUE_PAYMENT2 (
	ORDERNAME VARCHAR(20),
	ID INTEGER(10) not null REFERENCES highway.PAYMENT2(ID)
);

