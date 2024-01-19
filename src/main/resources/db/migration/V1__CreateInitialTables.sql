CREATE TABLE accounts (
    id BIGINT IDENTITY PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    account_type VARCHAR(255) NOT NULL,
    balance FLOAT NOT NULL
);

CREATE TABLE transactions (
    id BIGINT IDENTITY PRIMARY KEY,
    created DATETIME NOT NULL,
    updated DATETIME,
    amount FLOAT NOT NULL,
    type VARCHAR(255) NOT NULL,
    account_id BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);
