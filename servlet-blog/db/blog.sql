drop database if exists blog;
create database blog;

use blog;



drop table IF EXISTS user;
create table user(
    id int primary key auto_increment,
    name varchar(20),
    create_time timestamp
);

drop table IF EXISTS article;
create table article(
                        id int primary key auto_increment,
                        title varchar(50) not null,
                        content mediumtext not null,
                        user_id int,
                        create_time timestamp,
                        foreign key(user_id) references user(id)
);

insert into user(name,create_time) values ('abc','2020-03-01 12:00:00');

insert  into article(title, content, user_id, create_time) values ('t1','c1',1,current_timestamp );
insert  into article(title, content, user_id, create_time) values ('t2','c2',1,current_timestamp );
insert  into article(title, content, user_id, create_time) values ('t3','c3',1,current_timestamp );