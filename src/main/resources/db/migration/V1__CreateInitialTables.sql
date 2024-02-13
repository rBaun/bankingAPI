DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

-- Authentication Tables
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    username NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL
);

CREATE TABLE roles (
    id INT IDENTITY(1,1) PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    name NVARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Banking Tables
CREATE TABLE customers (
    id BIGINT IDENTITY PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    name NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    phone_number NVARCHAR(255) NOT NULL UNIQUE,
    address NVARCHAR(255) NOT NULL,
    date_of_birth DATE,
    social_security_number NVARCHAR(255) NOT NULL UNIQUE,
    username NVARCHAR(255) NOT NULL
);

CREATE TABLE accounts (
    id BIGINT IDENTITY PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    title NVARCHAR(255),
    account_number NVARCHAR(255) NOT NULL UNIQUE,
    account_type NVARCHAR(255) NOT NULL,
    balance DECIMAL(19, 4) NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE transactions (
    id BIGINT IDENTITY PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    amount FLOAT NOT NULL,
    type NVARCHAR(255) NOT NULL,
    account_id BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);