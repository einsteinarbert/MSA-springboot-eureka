drop table if exists present_box_items;

create table present_box_items(
    id bigint auto_increment primary key not null,
    present_box_id bigint not null ,
    item_id bigint not null ,
    item_number int,
    created_at timestamp,
    updated_at timestamp
)
drop table if exists login_rewards;
create table login_rewards
(
    id bigint auto_increment primary key not null ,
    name varchar(255) not null,
    login_reward_type int not null,
    status int not null,
    start_date timestamp not null,
    end_date timestamp,
    created_at timestamp,
    updated_at timestamp
);
drop table if exists login_reward_options;
create table login_reward_options
(
    id bigint auto_increment primary key not null ,
    login_reward_id bigint not null,
    day int not null,
    created_at timestamp,
    updated_at timestamp
);
drop table if exists login_reward_items;
create table login_reward_items
(
    id bigint auto_increment primary key not null ,
    login_reward_option_id bigint not null,
    item_id bigint not null,
    number int not null,
    created_at timestamp,
    updated_at timestamp
);

alter table quests
    add status int default 0 null comment '0: closed, 1: opening';

alter table quests
    add start_date timestamp null;

alter table quests
    add end_date timestamp null;

alter table user_quests
    add status int default 1 not null;
