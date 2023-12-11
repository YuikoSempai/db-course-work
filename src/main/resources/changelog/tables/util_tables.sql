create sequence users_seq;

create table users (
    id int default nextval('users_seq') primary key ,
    username text,
    password text,
    unique (username, password)
)
