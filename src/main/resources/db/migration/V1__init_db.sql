CREATE TABLE IF NOT EXISTS worker
(
    ID int auto_increment,
    NAME VARCHAR NOT NULL CHECK(length(NAME) >= 2 and length(NAME) <= 1000),
    BIRTHDAY DATE CHECK(BIRTHDAY >= '1900-01-01'),
    LEVEL VARCHAR(7) NOT NULL CHECK(LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    SALARY INT CHECK(SALARY >= 100 AND SALARY <= 100000),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS client
(
    ID BIGINT AUTO_INCREMENT,
    NAME VARCHAR NOT NULL CHECK(length(NAME) >= 2 and length(NAME) <= 1000),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS project
(
    ID BIGINT AUTO_INCREMENT,
    CLIENT_ID BIGINT,
    START_DATE DATE,
    FINISH_DATE DATE,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS project_worker
(
    PROJECT_ID BIGINT,
    WORKER_ID BIGINT,
    PRIMARY KEY (PROJECT_ID, WORKER_ID),
    FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
    FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);
