
 create table baskets (
     discount float(53) not null,
     total float(53) not null,
     id bigserial not null,
     primary key (id));

     create table categories (
         id bigserial not null,
         name varchar(255),
         sub_category varchar(255) check (sub_category in ('WATCHBANDS','CHARGING_DEVICE','SCREEN_PROTECTION','CASES','COASTERS','CABLES_ADAPTERS','EXTERNAL_BATTERIES')),
         primary key (id));

     create table create_news_letter (
         id bigserial not null,
         name varchar(255),
         primary key (id));

     create table deliveries (
         id bigserial not null,
         name varchar(255),
         status varchar(255) check (status in ('DELIVERED','ON_MY_WAY','CANCELLATION','IN_PROCESSING')),
         primary key (id));

     create table discounts
     (id bigserial not null,
      date_end varchar(255),
      date_start varchar(255),
      size_discount varchar(255),
      primary key (id));

     create table orders (
         sum integer not null,
         basket_id bigint,
         delivery_id bigint,
         id bigserial not null,
         pay_card_id bigint,
         address varchar(255),
         user_address varchar(255),
         primary key (id));

     create table payment_pay_cards (
         number_card integer not null,
         id bigserial not null,
         users_id bigint,
         cvc varchar(255),
         mm varchar(255),
         owner_name varchar(255),
         yy varchar(255),
         primary key (id));

     create table products (
         creat_date date,
         discount float(53) not null,
         price float(53) not null,
         sim_card integer not null,
         waterproof boolean not null,
         weight integer not null,
         category_id bigint,
         id bigserial not null,
         case_shape varchar(255) check (case_shape in ('SQUARE','ROUND','OVAL','RECTANGULAR')),
         color varchar(255),
         guarantee varchar(255),
         memory varchar(255) check (memory in ('GB_128','GB_64','GB_32','GB_16','GB_8','GB_4')),
         name varchar(255),
         processor varchar(255),
         rating varchar(255),
         screen_size varchar(255),
         sub_category varchar(255) check (sub_category in ('WATCHBANDS','CHARGING_DEVICE','SCREEN_PROTECTION','CASES','COASTERS','CABLES_ADAPTERS','EXTERNAL_BATTERIES')),
         w_i varchar(255) check (w_i in ('BLUETOOTH','WI_FI','GPS','NFC')),
         primary key (id));

     create table products_and_orders (
         orders_id bigint not null,
         products_id bigint not null);

     create table roles (
         id bigserial not null,
         name varchar(255),
         primary key (id));

     create table user_orders (
         order_id bigint,
         user_id bigint not null,
         primary key (user_id));

     create table users (
         age integer not null,
         create_date date,
         basket_id bigint unique,
         id bigserial not null,
         news_letter_id bigint unique,
         address varchar(255),
         email varchar(255),
         first_name varchar(255),
         gender varchar(255) check (gender in ('FEMALE','MAILE')),
         last_name varchar(255),
         name varchar(255), password varchar(255),
         tel_number varchar(255),
         primary key (id));

     create table users_baskets (
         baskets_id bigint not null,
         users_id bigint not null);

     create table users_roles (
         role_id bigint not null,
         user_id bigint not null);

     alter table if exists orders add constraint FKkeq4ha3u9m7f5nv9wqyk4lnap foreign key (basket_id) references baskets;
     alter table if exists orders add constraint FK8w9m21riko8j8eit0yvog02nr foreign key (delivery_id) references deliveries;
     alter table if exists orders add constraint FK32nul9ni892bf16p9ed3i6oci foreign key (pay_card_id) references payment_pay_cards;
     alter table if exists payment_pay_cards add constraint FKlgyxfuvaey0v2t39k5hkr6dxr foreign key (users_id) references users;
     alter table if exists products add constraint FKog2rp4qthbtt2lfyhfo32lsw9 foreign key (category_id) references categories;
     alter table if exists products_and_orders add constraint FKctsjtwlo1fqfv0k91am0kgdpi foreign key (orders_id) references orders;
     alter table if exists products_and_orders add constraint FKfvr9xb4uw3ax64vbgs6iowinb foreign key (products_id) references products;
     alter table if exists user_orders add constraint FK8lftv26t34tw8n6n8lnq45p09 foreign key (order_id) references orders;
     alter table if exists user_orders add constraint FK96dnjxiyb0w7lj9dwuu4xi045 foreign key (user_id) references users;
     alter table if exists users add constraint FKommxbvym8k63qbwomj0ikj8us foreign key (basket_id) references baskets;
     alter table if exists users add constraint FKflwrwi2aajyche2phwvg9b694 foreign key (news_letter_id) references create_news_letter;
     alter table if exists users_baskets add constraint FKdvyi685pyk0diu63ag9yqanol foreign key (baskets_id) references products;
     alter table if exists users_baskets add constraint FKcmmd8cd6vue258gek58ycym85 foreign key (users_id) references baskets;
     alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles;
     alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;