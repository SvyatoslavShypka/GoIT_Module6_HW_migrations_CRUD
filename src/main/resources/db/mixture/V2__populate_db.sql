INSERT INTO worker(NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    ('Jurko Jurchenko',      '1980-01-02',      'Trainee',       900),
    ('Ivan Ivanenko',        '1985-12-25',      'Senior',       8000),
    ('Petro Petrenko',       '1990-01-02',      'Junior',       1500),
    ('Stepan Stepanenko',    '1971-03-03',      'Senior',       8000),
    ('Andriy Andrijenko',    '1980-01-02',      'Trainee',       500),
    ('Anna Radchenko',       '1983-01-02',      'Middle',       2000),
    ('Vasylyna Vasylenko',   '1977-01-02',      'Senior',       6000),
    ('Iryna Irynenko',       '1971-03-03',      'Trainee',       700),
    ('Vasyl Vasylenko',      '2001-01-02',      'Middle',       5500),
    ('Marija Marynenko',     '2001-01-02',      'Junior',       1680);

INSERT INTO client (NAME)
VALUES
    ('Katarzyna Wasilewicz'),
    ('Agnieszka Klimowicz'),
    ('Andrzej Kowalczyk'),
    ('Monika Stas'),
    ('Joanna Wawryszkiewicz');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
    (1, '2022-01-01', '2023-01-01'),
    (2, '2012-01-01', '2019-12-13'),
    (1, '2013-01-01', '2020-12-01'),
    (4, '2017-01-01', '2023-01-01'),
    (3, '2019-01-01', '2023-01-01'),
    (1, '2021-01-01', '2023-01-01'),
    (4, '2011-01-01', '2014-01-01'),
    (5, '2020-01-01', '2021-01-01'),
    (4, '2022-01-01', '2023-01-01'),
    (3, '2020-01-01', '2022-01-15');

INSERT INTO PROJECT_WORKER (PROJECT_ID, WORKER_ID)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10,10),
    (1, 4),
    (1, 7),
    (1, 8),
    (7, 3),
    (7, 5),
    (7, 9),
    (7, 10),
    (4, 5),
    (5, 6),
    (6, 9);
