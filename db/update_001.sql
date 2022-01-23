create table person (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000),
                        employee_id int not null references employee_id(id)
);

insert into person (login, password, employee_id) values ('parsentev', '123', 1);
insert into person (login, password, employee_id) values ('ban', '123', 2);
insert into person (login, password, employee_id) values ('ivan', '123', 3);


create table employee (
                        id serial primary key not null,
                        name varchar(2000),
                        lastName varchar(2000),
                        inn varchar(2000),
                        dateStart TIMESTAMP
);



insert into employee(id, name, lastname, inn, datestart) VALUES (1, 'parsentev', 'Patch', '000000000', '2022-01-01');
insert into employee(id, name, lastname, inn, datestart) VALUES (2, 'ban', 'Patch1', '11111111', '2022-01-01');
insert into employee(id, name, lastname, inn, datestart) VALUES (3, 'ivan', 'Patch2', '22222222', '2022-01-01');

