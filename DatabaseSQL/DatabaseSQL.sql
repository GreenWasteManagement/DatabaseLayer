CREATE DATABASE green_waste_management;

-- Dados para serem usados como role e status
CREATE TYPE user_role AS ENUM ('ADMIN', 'SMAS', 'MUNICIPALITY');
CREATE TYPE association_status AS ENUM ('ACTIVE', 'INACTIVE');

-- Tabelas nÃ£o dependentes
CREATE TABLE "user"
(
    user_id     SERIAL PRIMARY KEY,
    name        VARCHAR(255)        NOT NULL,
    username    VARCHAR(100) UNIQUE NOT NULL,
    password    VARCHAR(255)        NOT NULL,
    email       VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(16),
    role        user_role           NOT NULL
);

CREATE TABLE postal_code
(
    postal_code_id SERIAL PRIMARY KEY,
    postal_code     VARCHAR(20)  NOT NULL UNIQUE,
    county         VARCHAR(100) NOT NULL,
    district       VARCHAR(100) NOT NULL
);

-- Tabelas dependentes
CREATE TABLE address
(
    user_id        INT PRIMARY KEY,
    floor_details   VARCHAR(255),
    floor_number    INT,
    door_number     INT,
    street         VARCHAR(255),
    postal_code_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE,
    FOREIGN KEY (postal_code_id) REFERENCES postal_code (postal_code_id) ON DELETE RESTRICT
);

CREATE TABLE admin
(
    user_id         INT PRIMARY KEY,
    citizen_card_code VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE
);

CREATE TABLE smas
(
    user_id         INT PRIMARY KEY,
    position        VARCHAR(255),
    employee_code    VARCHAR(50) UNIQUE NOT NULL,
    citizen_card_code VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE
);

CREATE TABLE municipality
(
    user_id         INT PRIMARY KEY,
    citizen_card_code VARCHAR(50) UNIQUE NOT NULL,
    nif             VARCHAR(9) UNIQUE  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE
);

CREATE TABLE bucket
(
    bucket_id    SERIAL PRIMARY KEY,
    capacity     DECIMAL(14, 2) NOT NULL,
    is_associated BOOLEAN DEFAULT FALSE
);

CREATE TABLE container
(
    container_id       SERIAL PRIMARY KEY,
    capacity           DECIMAL(14, 2) NOT NULL,
    localization       VARCHAR(255)   NOT NULL,
    current_volume_level DECIMAL(14, 2) DEFAULT 0
);

CREATE TABLE bucket_municipality
(
    association_id         SERIAL PRIMARY KEY,
    user_id                INT                NOT NULL,
    bucket_id              INT                NOT NULL,
    timestamp_of_association TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status                 association_status NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE,
    FOREIGN KEY (bucket_id) REFERENCES bucket (bucket_id) ON DELETE CASCADE
);

CREATE TABLE bucket_municipality_container
(
    deposit_id       SERIAL PRIMARY KEY,
    association_id   INT            NOT NULL,
    container_id     INT            NOT NULL,
    deposit_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deposit_amount    DECIMAL(14, 2) NOT NULL,
    FOREIGN KEY (association_id) REFERENCES bucket_municipality (association_id) ON DELETE CASCADE,
    FOREIGN KEY (container_id) REFERENCES container (container_id) ON DELETE CASCADE
);

CREATE TABLE container_unloading
(
    discharge_id       SERIAL PRIMARY KEY,
    container_id       INT            NOT NULL,
    user_id            INT            NOT NULL,
    unloaded_quantity   DECIMAL(14, 2) NOT NULL,
    unloading_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (container_id) REFERENCES container (container_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE
);