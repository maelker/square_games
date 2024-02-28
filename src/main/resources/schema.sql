DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id varchar(36) NOT NULL,
                       address_id varchar(36),
                       avatar varchar(255),
                       birth_date timestamp(6),
                       creation_date timestamp(6),
                       fav_payment varchar(255),
                       first_name varchar(255),
                       id_parent varchar(36),
                       last_name varchar(255),
                       login varchar(255),
                       mail varchar(255),
                       password varchar(255),
                       primary key (id)
);
DROP TABLE IF EXISTS address;
CREATE TABLE address (
                         id_address varchar(36) NOT NULL,
                         address_city varchar(255),
                         address_postal_code varchar(255),
                         address_street_name varchar(255),
                         address_street_number varchar(255),
                         primary key (id_address)
);
