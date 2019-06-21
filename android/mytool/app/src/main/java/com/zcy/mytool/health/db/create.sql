create table health_record_food (
    id integer not null primary key autoincrement,
    health_type integer not null,  -- 类型
    content text not null, -- 内容
    dates timestamp) -- 记录时间

create table health_record_exercise (
    id integer not null primary key autoincrement,
    health_type integer not null,  -- 类型
    content text not null, -- 内容
    dates timestamp); -- 记录时间