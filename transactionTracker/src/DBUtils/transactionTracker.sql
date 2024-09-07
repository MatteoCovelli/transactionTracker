
-- CREAZIONE TABELLA USERS
create table users (
                       user_id varchar(50) not null primary key,
                       pw char(68) not null,
                       active int not null);



INSERT INTO users
VALUES
    ('luca','{bcrypt}$2a$12$ldwRJDnb22pEYOZvSXE6e.11FVPGjC0.BYUdNYg7rbHL.sjo8P9Ha',1),
    ('mary','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);


create table roles (
                       user_id varchar(50) references users(user_id),
                       ruolo varchar(50) not null);

INSERT INTO roles
VALUES
    ('luca','ROLE_UTENTE'),
    ('mary','ROLE_UTENTE');



create sequence seq_transazioni
    increment by 1
    start with 1
    minvalue 1
    no maxvalue;

create table transazioni
(
    id bigint primary key default nextval ('seq_transazioni'),
    nome_transazione varchar,
    data_transazione date,
    importo float,
    user_id varchar(50) references users(user_id)
);




insert into transazioni(nome_transazione, data_transazione, importo, user_id)
values
    ('transazione di prova 2', '07-09-2024', 40.50, 'luca' );


insert into transazioni(nome_transazione, data_transazione, importo, user_id)
values
    ('transazione', '07-09-2024', 40.50, 'mary' );













