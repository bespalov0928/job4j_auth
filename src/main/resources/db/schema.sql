create table roles
(
    id        serial primary key not null,
    authority varchar(2000)
);


create table persons
(
    id       serial primary key not null,
    username varchar(2000),
    password varchar(2000),
    empid    int
--                         employee_id int not null references employee_id(id)
);


create table persons_roles
(
    person_id INTEGER REFERENCES persons(id),
    roles_id  INTEGER REFERENCES roles(id)
);
-- insert into persons (username, password, empid) values ('parsentev', '123', 1);
-- insert into persons (username, password, empid) values ('ban', '123', 2);
-- insert into persons (username, password, empid) values ('ivan', '123', 3);


create table employee
(
    id        serial primary key not null,
    name      varchar(2000),
    lastName  varchar(2000),
    inn       varchar(2000),
    dateStart TIMESTAMP
);
-- insert into employee(id, name, lastname, inn, datestart) VALUES (1, 'parsentev', 'Patch', '000000000', '2022-01-01');
-- insert into employee(id, name, lastname, inn, datestart) VALUES (2, 'ban', 'Patch1', '11111111', '2022-01-01');
-- insert into employee(id, name, lastname, inn, datestart) VALUES (3, 'ivan', 'Patch2', '22222222', '2022-01-01');


create table employee_persons
(
    employee_id INTEGER REFERENCES employee(id),
    persons_id  INTEGER REFERENCES persons(id)
);


create table rooms
(
    id   serial primary key not null,
    name varchar(2000)
);


create table message
(
    id        serial primary key not null,
    mes       varchar(2000),
    person_id INTEGER REFERENCES persons(id),
    room_id   INTEGER REFERENCES rooms(id)
);
