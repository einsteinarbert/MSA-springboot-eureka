alter table users add gender_type integer(1) comment '1: male, 2:female';
--
alter table characters change column `stand`  `stand_id` varchar(255);
--
alter table characters
    add face_smile_id varchar(255),
    add face_sad_id varchar(255),
    add face_angry_id varchar(255),
    add face_surprise_id varchar(255);
--
--
truncate table background;
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0100', 'officeEX_day','picture','怪奇局外観（大きな神社）　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0110', 'officeEX_sunset','picture','怪奇局外観（大きな神社）　夕方',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0200', 'office_day','picture','怪奇局事務所　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0300', 'oldshrineEX_day','picture','廃れた神社　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0310', 'oldshrineEX_sunset','picture','廃れた神社　夕方',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0400', 'bamboo_day','picture','竹の怪　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0410', 'bamboo_sunset','picture','竹の怪　夕方',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0500', 'oldtown_day','picture','古い町並み　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0510', 'oldtown_sunset','picture','古い町並み　夕方',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0600', 'newtown_day','picture','市街地　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0610', 'newtown_sunset','picture','市街地　夕方',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0700', 'park_day','picture','公園　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0710', 'park_sunset','picture','公園　夕方',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0800', 'terraceEX_day','picture','まちてらす外観　昼',current_date(),current_date(), current_date(), current_date());
insert into background (item_id, background_token, name, picture, description, start_date, end_date, created_at, updated_at)  values (2, 'bg0810', 'terraceEX_sunset','picture','まちてらす外観　夕方',current_date(),current_date(), current_date(), current_date());

drop table if exists skills;
create table skills(
    id bigint auto_increment primary key,
    name       varchar(255) not null,
    skill_type int          not null,
    skill_option_id bigint,
    change_type int,
    pattern_type int,
    skill_coin_boost_id bigint,
    created_at timestamp    null,
    updated_at timestamp    null


);
--
drop table if exists skill_options;
create table skill_options(
    id bigint auto_increment primary key ,
    name varchar(255) not null,
    level1_number varchar(255) not null ,
    level1_probability varchar(255) not null ,
    level2_number varchar(255) not null ,
    level2_probability varchar(255) not null ,
    level3_number varchar(255) not null ,
    level3_probability varchar(255) not null ,
    level4_number varchar(255) not null ,
    level4_probability varchar(255) not null ,
    level5_number varchar(255) not null ,
    level5_probability varchar(255) not null ,
    level6_number varchar(255) not null ,
    level6_probability varchar(255) not null ,
    created_at timestamp,
    updated_at timestamp
);
--
drop table if exists skill_coin_boosts;
create table skill_coin_boosts(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    level1_number_of_move integer not null,
    level1_time integer not null,
    level1_rate integer not null,
    level2_number_of_move integer not null,
    level2_time integer not null,
    level2_rate integer not null,
    level3_number_of_move integer not null,
    level3_time integer not null,
    level3_rate integer not null,
    level4_number_of_move integer not null,
    level4_time integer not null,
    level4_rate integer not null,
    level5_number_of_move integer not null,
    level5_time integer not null,
    level5_rate integer not null,
    level6_number_of_move integer not null,
    level6_time integer not null,
    level6_rate integer not null,
    created_at timestamp,
    updated_at timestamp
);
--
insert into special_items (item_id, special_item_token, name, special_item_type, picture, description, start_date, end_date, created_date, updated_date)
values (2000, '300000', '300000', 4, 'itm300000', 'Item A（trong puzzle）', current_date , current_date , current_date , current_date );
insert into special_items (item_id, special_item_token, name, special_item_type, picture, description, start_date, end_date, created_date, updated_date)
values (2000, '300001', '300001', 4, 'itm300001', 'Item B（trong puzzle）', current_date , current_date , current_date , current_date );
insert into special_items (item_id, special_item_token, name, special_item_type, picture, description, start_date, end_date, created_date, updated_date)
values (2000, '300002', '300002', 4, 'itm300002', 'Item C（trong puzzle）', current_date , current_date , current_date , current_date );
insert into special_items (item_id, special_item_token, name, special_item_type, picture, description, start_date, end_date, created_date, updated_date)
values (2000, '310000', '310000', 4, 'itm310000', 'Item A（trước puzzle）', current_date , current_date , current_date , current_date );
insert into special_items (item_id, special_item_token, name, special_item_type, picture, description, start_date, end_date, created_date, updated_date)
values (2000, '310001', '310001', 4, 'itm310001', 'Item B（trước puzzle）', current_date , current_date , current_date , current_date );
