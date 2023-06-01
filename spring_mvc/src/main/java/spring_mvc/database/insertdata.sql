use spring_mvc;

insert into role(code,name) values('ADMIN','Quản trị');
insert into role(code,name) values('USER','Người dùng');

insert into user(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1);
insert into user(username,password,fullname,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyễn văn A',1);
insert into user(username,password,fullname,status)
values('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyễn văn B',1);

INSERT INTO users_roles(user_id,role_id) VALUES (1,1);
INSERT INTO users_roles(user_id,role_id) VALUES (2,2);
INSERT INTO users_roles(user_id,role_id) VALUES (3,2);

insert into category(code, name) values ('the-thao','Thể thao');
insert into category(code, name) values ('the-gioi','Thế giới');
insert into category(code, name) values ('chinh-tri','Chính trị');
insert into category(code, name) values ('thoi-su','Thời sự');
insert into category(code, name) values ('goc-nhin','Góc nhìn');

