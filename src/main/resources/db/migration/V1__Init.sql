create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );


create table photo (
    id bigint primary key not null,
    full_name text,
    name text
);

create table user (
    id bigint primary key not null,
    password varchar(255),
    role varchar(255),
    username varchar(255)
);

insert into user (id, password, role, username) values (1, 'qNfjs493jfNru*hf', 'ROLE_CREATOR', 'creator');

create table photo_users (
    photo_id bigint not null,
    user_id bigint not null,
    primary key (photo_id, user_id),
    foreign key (photo_id) references photo (id) on delete cascade,
    foreign key (user_id) references user (id) on delete cascade
);