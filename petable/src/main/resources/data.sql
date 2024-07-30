INSERT INTO `users` (id, username, email, password)
VALUES (1, 'admin', 'admin@test.com', 'admin123');
INSERT INTO `users` (id, username, email, password)
VALUES (2, 'sharko', 'sharko@test.com', 'sharko123');
INSERT INTO `users` (id, username, email, password)
VALUES (3, 'pesho', 'pesho@test.com', 'pesho123');

INSERT INTO `shelters` (`id`, `name`, location, special_number)
VALUES (2, 'Sharko', 'Plovdiv', '12345');

INSERT INTO `clients` (`id`, first_name, last_name)
VALUES (3, 'Pesho', 'Peshev');

INSERT INTO `roles` (id, role) VALUES (1, 'ADMIN');
INSERT INTO `roles` (id, role) VALUES (2, 'CLIENT');
INSERT INTO `roles` (id, role) VALUES (3, 'SHELTER');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 3);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 2);

INSERT INTO `pets` (id, `name`, gender, type, birthdate, location, status, short_description)
VALUES (1, 'Test Cat', 'FEMALE', 'CAT', '2018-01-07', 'Plovdiv', 'RESERVED', 'short description for test purposes');
