create table member
(
    member_id varchar(10),
    money     integer not null default 0,
    primary key (member_id)
);