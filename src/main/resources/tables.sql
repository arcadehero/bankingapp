CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT PRIMARY KEY,
    email         VARCHAR UNIQUE NOT NULL,
    password_hash VARCHAR        NOT NULL,
    full_name     VARCHAR,
    created_at    TIMESTAMP
);

CREATE TABLE IF NOT EXISTS accounts
(
    id         BIGINT PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id),
    currency   VARCHAR(3)     NOT NULL,
    balance    DECIMAL(18, 4) NOT NULL DEFAULT 0,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transactions
(
    id               BIGINT PRIMARY KEY,
    from_account_id  BIGINT REFERENCES accounts (id),
    to_account_id    BIGINT REFERENCES accounts (id),
    amount           DECIMAL(18, 4) NOT NULL,    -- Сумма в валюте from_account
    converted_amount DECIMAL(18, 4),             -- Если конвертация: сколько пришло на to_account
    currency_rate    DECIMAL(18, 8),             -- Курс обмена
    type             VARCHAR        NOT NULL,    -- 'transfer', 'deposit', 'withdrawal'
    description      TEXT,
    created_at       TIMESTAMP,
    status           VARCHAR DEFAULT 'completed' -- на случай будущей обработки
);

CREATE TABLE IF NOT EXISTS currency_rates
(
    id              BIGINT PRIMARY KEY,
    base_currency   VARCHAR(3)     NOT NULL,
    target_currency VARCHAR(3)     NOT NULL,
rate            DECIMAL(18, 8) NOT NULL,
fetched_at      TIMESTAMP
);


DROP TABLE currency_rates cascade;
DROP TABLE users cascade ;
DROP TABLE accounts cascade;
DROP TABLE transactions cascade;
