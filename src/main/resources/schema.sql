create table banks
(
    id   binary(255)  not null,
    name varchar(255) not null,
    primary key (id)
);

create table clients
(
    id              binary(255) not null,
    email           varchar(255),
    name            varchar(255),
    passport_number varchar(255),
    phone_number    varchar(255),
    primary key (id)
);

create table bank_client
(
    bank_id   binary(255) not null,
    client_id binary(255) not null,
    primary key (bank_id, client_id)
);

create table credits
(
    id            binary(255) not null,
    credit_limit  double,
    interest_rate double,
    period        integer,
    bank_id       binary(255),
    primary key (id)
);

create table credit_offers
(
    id            binary(255) not null,
    begin_date    date,
    credit_amount double,
    pay_strategy  varchar(255),
    client_id     binary(255),
    credit_id     binary(255),
    primary key (id)
);

create table payments
(
    id                 binary(255) not null,
    amount_of_body     double,
    amount_of_interest double,
    amount_of_paymant  double,
    date               date,
    offer_id           binary(255),
    primary key (id)
);

alter table credit_offers
    add constraint FKjaio5buq4xxw081c8ill1b5da
        foreign key (credit_id)
            references credits;

alter table credits
    add constraint FKt3c6cd1lq2lsiclu6bh2pux2y
        foreign key (bank_id)
            references banks;

alter table payments
    add constraint FKje8kfcgubvupfdnhaj1keyy47
        foreign key (offer_id)
            references credit_offers;