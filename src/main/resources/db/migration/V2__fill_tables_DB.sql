insert into roles (id, name) VALUES (1, 'ADMIN'), (2,'USER');
insert into baskets (discount, total, id) VALUES (0.5,0.56, 1);
insert into create_news_letter (id, name) VALUES (1, 'News Letter');
insert into brands (id, image, name) VALUES (1, null, 'Samsung');
insert into users (age, create_date,
                   basket_id, id,
                   news_letter_id,
                   address,
                   email,
                   first_name,
                   gender,
                   last_name,
                   name,
                   password,
                   tel_number) VALUES
                                   (18, now(), null, 1, null,
                                    'Unknown street, num 5',
                                    'admin@gmail.com',
                                    'name', 'UNISEX', 'lastname', 'Admin','password', '+7 999 999 99 99'),
                                   (18,now(),1,2,1,'Unknown street, num 12','user@gmail.com','name','UNISEX','lastname','User','password','+7 900 900 90 90');
insert into users_and_roles (role_id, user_id) VALUES (1,1), (2,1);
insert into products (article, creat_date, discount, in_stock, price, quantity, sim_card, total_price, weight, brands_id, category_id, brand_name, case_shape, color, description, gender, guarantee, image, memory, name, operating_system, operation_memory, pdf, processor, rating, screen, screen_size, smart_watch_size, sub_category, the_material_of_the_case, video, watchband, water_resistance, wireless_interface) VALUES
                    (123453, now(), 0.3, 100, 45000, 1, 120000, 90000, 145, 1, null, 'Samsung','RECTANGULAR','black', null, 'UNISEX','1 year',null,'GB_128','Samsung S24 Ultra','ANDROID','GB_8',null , 'Snapdragon 8 gen 3', '5.0', null, null, null, 'CASES', 'Titan', null, null,'YES', 'WI_FI')
