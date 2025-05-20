create table admin
(
    name     varchar(100),
    username varchar(50),
    password varchar(100),
    phone    varchar(20),
    email    varchar(50)
);

create table employee
(
    name     varchar(100),
    username varchar(50),
    password varchar(100),
    phone    varchar(20),
    email    varchar(50)
);

create table customer
(
    name     varchar(100),
    username varchar(50),
    password varchar(100),
    phone    varchar(20),
    email    varchar(50)
);

create table product
(
    id          int,
    name        varchar(100),
    description varchar(500),
    rate        double,
    price       double
);

create table cart
(
    id          int,
    customer    varchar(100),
    product     int,
    quantity    int,
    isConfirmed int,
    isApproved  int,
    employee    varchar(100)
);

insert into admin (name, username, password, phone, email)
values ('Shuvo Baidya', 'shuvo', '710', '01328207461', 'm.shuvobaidya710@gmail.com');

insert into employee (name, username, password, phone, email)
values ('Shuvo Baidya', 'shuvo', '710', '01328207461', 'm.shuvobaidya710@gmail.com');

insert into customer (name, username, password, phone, email)
values ('Shuvo Baidya', 'shuvo', '710', '01328207461', 'm.shuvobaidya710@gmail.com');

insert into product (id, name, description, rate, price)
values (1, 'Neem', 'Natural Medicine', 150.0, 125.0);

