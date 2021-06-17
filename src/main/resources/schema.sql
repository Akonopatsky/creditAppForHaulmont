CREATE TABLE IF NOT EXISTS banks
(
    id   binary(255)  not null,
    name varchar(255) not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS credits
(
    id            binary(255) not null,
    credit_limit  double,
    interest_rate double,
    period        integer,
    bank_id       binary(255),
    primary key (id),
    foreign key (bank_id)
        references banks (id) on delete cascade
);

CREATE TABLE IF NOT EXISTS clients
(
    id              binary(255) not null,
    email           varchar(255),
    name            varchar(255),
    passport_number varchar(255),
    phone_number    varchar(255),
    primary key (id)
);

CREATE TABLE IF NOT EXISTS bank_client
(
    bank_id   binary(255) not null,
    client_id binary(255) not null,
    primary key (bank_id, client_id),
    foreign key (client_id)
        references clients (id) on delete cascade,
    foreign key (bank_id)
        references banks (id) on delete restrict
);

CREATE TABLE IF NOT EXISTS credit_offers
(
    id            binary(255) not null,
    begin_date    date,
    credit_amount double,
    pay_strategy  varchar(255),
    client_id     binary(255),
    credit_id     binary(255),
    primary key (id),
    foreign key (client_id)
        references clients on delete cascade,
    foreign key (credit_id)
        references credits on delete cascade
);

CREATE TABLE IF NOT EXISTS payments
(
    id                 binary(255) not null,
    amount_of_body     double,
    amount_of_interest double,
    amount_of_paymant  double,
    date               date,
    offer_id           binary(255),
    primary key (id),
    foreign key (offer_id)
        references credit_offers on delete cascade
);


