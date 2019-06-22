create table health_record (
    id integer not null primary key autoincrement,
    health_type integer not null,  -- 类型
    title varchar not null,
    create_time timestamp not null) -- 记录时间

create table health_record_food (
    id integer not null primary key autoincrement,
    record_id integer not null)

create table health_record_exercise (
    id integer not null primary key autoincrement,
    record_id integer not null)