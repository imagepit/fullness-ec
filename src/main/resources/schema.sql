-- 外部キー制約のあるテーブルを先に削除
drop table if exists order_detail;
drop table if exists orders;
drop table if exists product_stock;
drop table if exists product;
drop table if exists product_category;
drop table if exists employee_account;
drop table if exists employee;
drop table if exists customer;
drop table if exists order_status;
drop table if exists department;

-- シーケンスの削除
drop sequence if exists order_detail_id_seq;
drop sequence if exists orders_id_seq;
drop sequence if exists product_stock_id_seq;
drop sequence if exists product_id_seq;
drop sequence if exists product_category_id_seq;
drop sequence if exists employee_id_seq;
drop sequence if exists customer_id_seq;
drop sequence if exists order_status_id_seq;
drop sequence if exists department_id_seq;

-- テーブル作成
create table department(
  id serial primary key,
  name varchar(100) not null
);

create table employee(
  id serial primary key,
  name varchar(100) not null,
  name_kana varchar(100) not null,
  department_id integer references department(id)
);

create table employee_account(
  id serial primary key,
  name varchar(20) not null,
  password varchar(200) not null,
  employee_id integer references employee(id)
);

create table product_category(
  id serial primary key,
  name varchar(100) not null
);

create table product(
  id serial primary key,
  name varchar(20) not null,
  price integer not null,
  image_url varchar(200) not null,
  product_category_id integer not null references product_category(id),
  delete_flag integer not null default 0
);

create table product_stock(
  id serial primary key,
  quantity integer not null,
  product_id integer not null references product(id)
);

create table order_status(
  id serial primary key,
  name varchar(100) not null
);

create table customer(
  id serial primary key,
  name varchar(20) not null,
  name_kana varchar(20) not null,
  address1 varchar(100) not null,
  address2 varchar(100),
  phone_number varchar(20) not null,
  mail_address varchar(200) not null,
  username varchar(30) not null,
  password varchar(30) not null,
  register_date timestamp with time zone not null
);

create table orders(
  id serial primary key,
  order_date timestamp with time zone not null,
  amount_total integer not null,
  customer_id integer not null references customer(id),
  order_status_id integer not null references order_status(id)
);

create table order_detail(
  id serial primary key,
  order_id integer not null references orders(id),
  product_id integer not null references product(id),
  count integer not null,
  customer_id integer not null references customer(id)
);