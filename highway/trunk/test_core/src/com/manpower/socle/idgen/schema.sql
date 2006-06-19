drop table db2admin.IDGEN;

create table db2admin.IDGEN (
   CNAME CHAR(10) NOT NULL , 
   CVALUE DECIMAL(19,0),
   primary key(CNAME)
);   
   
insert into db2admin.IDGEN values ('DEFAULT', 1);

commit;
