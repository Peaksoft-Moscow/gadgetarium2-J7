insert into roles (id, name) VALUES (1, 'ADMIN'), (2,'USER');
insert into baskets (discount, total, id) VALUES (0.5,0.56, 1);
insert into create_news_letter (id, name) VALUES (1, 'News Letter');
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
