drop table if exists users;

create table users(
username varchar not null primary key,
password varchar not null,
enabled boolean not null
);

drop table if exists authorities;

create table authorities(
username varchar not null,
authority varchar not null,
constraint fk_user_name foreign key (username) references users(username)
);