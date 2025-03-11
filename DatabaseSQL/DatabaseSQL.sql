CREATE
DATABASE green_waste_management;

-- Dados para serem usados como role e status
CREATE TYPE user_role AS ENUM ('ADMIN', 'SMAS', 'MUNICIPALITY');
CREATE TYPE association_status AS ENUM ('ACTIVE', 'INACTIVE');

-- Tabelas não dependentes
CREATE TABLE "User"
(
    USER_ID     SERIAL PRIMARY KEY,
    Name        VARCHAR(255)        NOT NULL,
    Username    VARCHAR(100) UNIQUE NOT NULL,
    Password    VARCHAR(255)        NOT NULL,
    Email       VARCHAR(255) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(16),
    Role        user_role           NOT NULL
);

CREATE TABLE "PostalCode"
(
    POSTAL_CODE_ID SERIAL PRIMARY KEY,
    PostalCode     VARCHAR(20)  NOT NULL UNIQUE,
    County         VARCHAR(100) NOT NULL,
    District       VARCHAR(100) NOT NULL
);

-- Tabelas dependentes
CREATE TABLE "Address"
(
    USER_ID        INT PRIMARY KEY,
    FloorDetails   VARCHAR(255),
    FloorNumber    INT,
    DoorNumber     INT,
    Street         VARCHAR(255),
    POSTAL_CODE_ID INT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES "User" (USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (POSTAL_CODE_ID) REFERENCES "PostalCode" (POSTAL_CODE_ID) ON DELETE RESTRICT
);

CREATE TABLE "Admin"
(
    USER_ID         INT PRIMARY KEY,
    CitizenCardCode VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES "User" (USER_ID) ON DELETE CASCADE
);

CREATE TABLE "SMAS"
(
    USER_ID         INT PRIMARY KEY,
    Position        VARCHAR(255),
    EmployeeCode    VARCHAR(50) UNIQUE NOT NULL,
    CitizenCardCode VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES "User" (USER_ID) ON DELETE CASCADE
);

CREATE TABLE "Municipality"
(
    USER_ID         INT PRIMARY KEY,
    CitizenCardCode VARCHAR(50) UNIQUE NOT NULL,
    NIF             VARCHAR(9) UNIQUE  NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES "User" (USER_ID) ON DELETE CASCADE
);

CREATE TABLE "Bucket"
(
    BUCKET_ID    SERIAL PRIMARY KEY,
    Capacity     DECIMAL(14, 2) NOT NULL,
    IsAssociated BOOLEAN DEFAULT FALSE
);

CREATE TABLE "Container"
(
    CONTAINER_ID       SERIAL PRIMARY KEY,
    Capacity           DECIMAL(14, 2) NOT NULL,
    Localization       VARCHAR(255)   NOT NULL,
    CurrentVolumeLevel DECIMAL(14, 2) DEFAULT 0
);

CREATE TABLE "Bucket_Municipality"
(
    ASSOCIATION_ID         SERIAL PRIMARY KEY,
    USER_ID                INT                NOT NULL,
    BUCKET_ID              INT                NOT NULL,
    TimeStampOfAssociation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Status                 association_status NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES "User" (USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (BUCKET_ID) REFERENCES "Bucket" (BUCKET_ID) ON DELETE CASCADE
);

CREATE TABLE "Bucket_Municipality_Container"
(
    DEPOSIT_ID       SERIAL PRIMARY KEY,
    ASSOCIATION_ID   INT            NOT NULL,
    CONTAINER_ID     INT            NOT NULL,
    DepositTimeStamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    DepositAmount    DECIMAL(14, 2) NOT NULL,
    FOREIGN KEY (ASSOCIATION_ID) REFERENCES "Bucket_Municipality" (ASSOCIATION_ID) ON DELETE CASCADE,
    FOREIGN KEY (CONTAINER_ID) REFERENCES "Container" (CONTAINER_ID) ON DELETE CASCADE
);

CREATE TABLE "Container_Unloading"
(
    DISCHARGE_ID       SERIAL PRIMARY KEY,
    CONTAINER_ID       INT            NOT NULL,
    USER_ID            INT            NOT NULL,
    UnloadedQuantity   DECIMAL(14, 2) NOT NULL,
    UnloadingTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CONTAINER_ID) REFERENCES "Container" (CONTAINER_ID) ON DELETE CASCADE,
    FOREIGN KEY (USER_ID) REFERENCES "User" (USER_ID) ON DELETE CASCADE
);
