INSERT INTO user (id_user, username, password) VALUES (1, 'raduvasile', '123456');
INSERT INTO user (id_user, username, password) VALUES (2, 'alparov91', '123456');

INSERT INTO readers (id_reader, address, email, first_name, phone, second_name, user_id_user) VALUES (1, 'address', 'radu_vasile@yahoo.com', 'Radu', '123', 'Vasile',1);
INSERT INTO admins (id_admin, address, email, first_name, phone, second_name, user_id_user) VALUES (1, 'address', 'lprv91@yahoo.com', 'John', '123', 'Doe',2);

INSERT INTO books (id_book, author, availability, pages, publisher, section, title, year) VALUES (1, 'Max', 'yes', 500, 'Pakt', 'IT', 'Payne stories', '2020');

INSERT INTO review (id_review, date, review, user, book_id_book) VALUES (1, '2020.10.20', 'asfdjgafglsah', 'raduvasile', 1);

INSERT INTO notification (id_notification, date, message, username, book_id_book) VALUES (1, '2020-10-20', 'Book is due at', 'raduvasile', 1);

