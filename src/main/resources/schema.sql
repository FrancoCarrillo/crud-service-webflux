CREATE TABLE IF NOT EXISTS currency (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE exchange_type (
    id BIGINT NOT NULL AUTO_INCREMENT,
    exchangeRate DOUBLE,
    originCurrency_id BIGINT,
    destinationCurrency_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (originCurrency_id) REFERENCES currency(id),
    FOREIGN KEY (destinationCurrency_id) REFERENCES currency(id)
);

CREATE TABLE exchange (
    id BIGINT NOT NULL AUTO_INCREMENT,
    originAmount DOUBLE,
    destinationAmount DOUBLE,
    user_id BIGINT,
    exchangeType_id BIGINT,
    createdDate DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (exchangeType_id) REFERENCES exchange_type(id)
);


CREATE TABLE IF NOT EXISTS role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  user_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    names VARCHAR(255),
    lastNames VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  user_rol (
    user_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, rol_id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (rol_id) REFERENCES role(id)
);


INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');

INSERT INTO currency (name) VALUES ('PEN');
INSERT INTO currency (name) VALUES ('USD');
INSERT INTO currency (name) VALUES ('EUR');

