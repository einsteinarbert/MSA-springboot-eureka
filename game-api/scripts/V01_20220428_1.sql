create table user_quests(
    id bigint auto_increment primary key,
    user_id bigint not null,
    quest_id bigint not null,
    created_at timestamp,
    updated_at timestamp
)