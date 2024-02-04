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
                                   (18, now(), 1, 1, 1,
                                    'Pushkin street, Kolotushkin house, num 5',
                                    'email@gmail.com',
                                    'name', 'UNISEX', 'lastname', 'name','password', '+7 999 999 99 99')
