
INSERT INTO users (user_id, name, password, account_non_locked, failed_attempt, lock_time) VALUES
    (10, 'ANNA', '$2a$10$O6RcZj8/S7mjYqVgOfpBguZk9JqBAT3TzlR0/YfMWuq6Voa.a8msW', true, 0, '2020-07-10 15:00:00.000'),
    (11, 'MARK', '$2a$10$O6RcZj8/S7mjYqVgOfpBguZk9JqBAT3TzlR0/YfMWuq6Voa.a8msW', true, 0, '2020-07-10 15:00:00.000');

INSERT INTO animals (animal_id, species, birth_date, sex, pet_name, user_id) VALUES
    (10, 'Cat', '2020-10-03', 'Male', 'GARFIELD', 10),
    (11, 'Dog', '2021-11-05', 'Male', 'REX', 11);



