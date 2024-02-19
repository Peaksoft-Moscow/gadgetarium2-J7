
     CREATE TABLE baskets(
      discount float(53) not null,
      total float(53) not null,
      id bigserial primary key
      );

     CREATE TABLE brands (
         id bigserial not null,
         image varchar(255),
         name varchar(255),
         primary key (id)
     );

     CREATE TABLE categories (
         id bigserial not null,
         name varchar(255),
         sub_category varchar(255) check (sub_category in ('WATCHBANDS','CHARGING_DEVICE','SCREEN_PROTECTION','CASES','COASTERS','CABLES_ADAPTERS')),
         primary key (id));

     CREATE TABLE CREATE_news_letter (
         id bigserial not null,
         name varchar(255),
         primary key (id));

     CREATE TABLE deliveries (
         id bigserial not null,
         name varchar(255),
         status varchar(255) check (status in ('DELIVERED','ON_MY_WAY','CANCELLATION','IN_PROCESSING')),
         primary key (id));

     CREATE TABLE discounts (
         id bigserial not null,
         date_end varchar(255),
         date_start varchar(255),
         size_discount varchar(255),
         primary key (id));

     CREATE TABLE orders (
         sum integer not null,
         basket_id bigint,
         delivery_id bigint,
         id bigserial not null,
         pay_card_id bigint,
         address varchar(255),
         user_address varchar(255),
         primary key (id));

     CREATE TABLE payment_pay_cards (
         number_card integer not null,
         id bigserial not null,
         users_id bigint,
         cvc varchar(3),
         mm varchar(2),
         owner_name varchar(255),
         yy varchar(4),
         primary key (id));

     CREATE TABLE product_orders (
         order_id bigint not null,
         product_id bigint not null);

     CREATE TABLE products (
         article integer not null,
         creat_date date,
         discount float(53) not null,
         in_stock integer not null,
         price float(53) not null,
         quantity integer not null,
         sim_card integer not null,
         total_price integer not null,
         weight integer not null,
         brands_id bigint,
         category_id bigint,
         id bigserial not null,
         brand_name varchar(255),
         case_shape varchar(255) check (case_shape in ('SQUARE','ROUND','OVAL','RECTANGULAR')),
         color varchar(255),
         description varchar(255),
         gender varchar(255) check (gender in ('FEMALE','MALE','UNISEX')),
         guarantee varchar(255),
         image varchar(255),
         memory varchar(255) check (memory in ('GB_128','GB_64','GB_32','GB_16','GB_8','GB_4')),
         name varchar(255),
         operating_system varchar(255) check (operating_system in ('ANDROID','WINDOWS','IOS')),
         operation_memory varchar(255) check (operation_memory in ('GB_128','GB_64','GB_32','GB_16','GB_8','GB_4')),
         pdf varchar(255),
         processor varchar(255),
         rating varchar(255),
         screen varchar(255),
         screen_size varchar(255),
         smart_watch_size varchar(255),
         sub_category varchar(255) check (sub_category in ('WATCHBANDS','CHARGING_DEVICE','SCREEN_PROTECTION','CASES','COASTERS','CABLES_ADAPTERS')),
         the_material_of_the_case varchar(255),
         video varchar(255),
         watchband varchar(255),
         water_resistance varchar(255) check (water_resistance in ('YES','NO')),
         wireless_interface varchar(255) check (wireless_interface in ('BLUETOOTH','WI_FI','GPS','NFC')),
         primary key (id));

     CREATE TABLE roles (
         id bigserial not null,
         name varchar(255),
         primary key (id));

     CREATE TABLE user_orders (
         order_id bigint,
         user_id bigint not null,
         primary key (user_id));

     CREATE TABLE users (
         age integer not null,
         create_date date,
         basket_id bigint unique,
         id bigserial not null,
         news_letter_id bigint unique,
         address varchar(255),
         email varchar(255),
         first_name varchar(255),
         gender varchar(255) check (gender in ('FEMALE','MALE','UNISEX')),
         last_name varchar(255),
         name varchar(255),
         password varchar(255),
         tel_number varchar(255),
         primary key (id));

     CREATE TABLE users_and_roles (
         role_id bigint not null,
         user_id bigint not null);

     CREATE TABLE users_baskets (
         baskets_id bigint not null,
         users_id bigint not null);

     alter TABLE if exists orders
         add constraint order_basket_fk
             foreign key (basket_id) references baskets;

     alter TABLE if exists orders
         add constraint order_delivery_fk
             foreign key (delivery_id) references deliveries;

     alter TABLE if exists orders
         add constraint order_pay_card_fk
             foreign key (pay_card_id) references payment_pay_cards;

     alter TABLE if exists payment_pay_cards
         add constraint payment_pay_cards_user_fk
             foreign key (users_id) references users;

     alter TABLE if exists product_orders
         add constraint product_order_order_fk
             foreign key (order_id) references orders;

     alter TABLE if exists product_orders
         add constraint product_order_product_fk
             foreign key (product_id) references products;

     alter TABLE if exists products
         add constraint product_brand_fk
             foreign key (brands_id) references brands;

     alter TABLE if exists products
         add constraint product_category_fk
             foreign key (category_id) references categories;

     alter TABLE if exists user_orders
         add constraint user_order_order_fk
             foreign key (order_id) references orders;

     alter TABLE if exists user_orders
         add constraint user_order_user_fk
             foreign key (user_id) references users;

     alter TABLE if exists users
         add constraint user_basket_fk
             foreign key (basket_id) references baskets;

     alter TABLE if exists users
         add constraint user_new_letter_fk
             foreign key (news_letter_id) references create_news_letter;

     alter TABLE if exists users_and_roles
         add constraint user_role_role_fk
             foreign key (role_id) references roles;

     alter TABLE if exists users_and_roles
         add constraint user_role_user_fk
             foreign key (user_id) references users;

     alter TABLE if exists users_baskets
         add constraint user_basket_basket_fk
             foreign key (baskets_id) references products;

     alter TABLE if exists users_baskets
         add constraint user_basket_user_fk
             foreign key (users_id) references baskets;