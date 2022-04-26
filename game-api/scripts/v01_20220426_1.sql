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
delete from background;
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