CREATE TABLE Customers (
    id BIGINT IDENTITY PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    phoneNumber NVARCHAR(255) NOT NULL UNIQUE,
    address NVARCHAR(255) NOT NULL,
    dateOfBirth DATE,
    socialSecurityNumber NVARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Accounts (
    id BIGINT IDENTITY PRIMARY KEY,
    accountNumber NVARCHAR(255) NOT NULL UNIQUE,
    accountType NVARCHAR(255) NOT NULL,
    balance DECIMAL(19, 4) NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE Transactions (
    id BIGINT IDENTITY PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    amount FLOAT NOT NULL,
    type NVARCHAR(255) NOT NULL,
    account_id BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Accounts(id)
);
