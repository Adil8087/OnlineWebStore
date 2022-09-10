use pracproject;
show tables;

select * from category;
select * from product;
select * from roles;
select * from user_role;
select * from users;

desc user;
desc user_role;
insert into roles 
values(1,'ROLE_ADMIN');

insert into roles 
values(2,'ROLE_USER');

insert into users 
values(1,'admin@gmail.com','admin','admin','$2a$10$C/xjeYKwQvUhaKTcVRWtiepLFLA2L3DgFU5NTnbJH6igvdxhnzj5q');

insert into User_role
values (1,1);
insert into User_role
values (1,2);