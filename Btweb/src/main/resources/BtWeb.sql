drop database if exists db_btweb;
create database db_btweb;

use db_btweb;

/**
 * 需要获取的内容：
 * 
 * 电影名
 * 照片
 * 上映时间
 * 类型
 * 地区
 * 语言
 * 主演
 * 剧情介绍
 * 名称+磁力链接
 * 插入时间
 */
create table t_media(
	id int not null auto_increment,-- 主键id
	mediatype varchar(20),				-- 视频种类，电影，电视剧
	name varchar(100),			-- 视频名
	coverurl varchar(200),		-- 封面路径
	releaseyear int,			-- 上映年份
	styles varchar(50),			-- 类型
	area varchar(20),			-- 地区
	language varchar(20),		-- 语言
	mainactors varchar(200),	-- 主演
	story text,					-- 剧情
	updatedate timestamp not null default current_timestamp on update current_timestamp,		-- 添加时间
	PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;

/**
 * 电影下载地址
 */
create table t_media_url(
	id int not null auto_increment,	-- 主键
	mediaid int not null,			-- 电影表的主键
	resolution varchar(20),			-- 清晰度
	urls text,						-- 对应清晰度的地址
	primary key (id),
	key mediaid (mediaid),			-- 添加索引，增加查询速度
	constraint t_media_url_ibfk_1 foreign key (mediaid) references t_media (id)	-- 添加外键
)engine=InnoDB default charset=utf8;

create table t_media_temp(
	id int not null auto_increment,-- 主键id
	mediatype varchar(20),				-- 视频种类，电影，电视剧
	name varchar(100),			-- 视频名
	coverurl varchar(200),		-- 封面路径
	releaseyear int,			-- 上映年份
	styles varchar(50),			-- 类型
	area varchar(20),			-- 地区
	language varchar(20),		-- 语言
	mainactors varchar(200),	-- 主演
	story text,					-- 剧情
	updatedate timestamp not null default current_timestamp on update current_timestamp,		-- 添加时间
	PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;

/**
 * 电影下载地址
 */
create table t_media_url_temp(
	id int not null auto_increment,	-- 主键
	mediaid int not null,			-- 电影表的主键
	resolution varchar(20),			-- 清晰度
	urls text,						-- 对应清晰度的地址
	primary key (id),
	key mediaid (mediaid),			-- 添加索引，增加查询速度
	constraint t_media_url_ibfk_1_temp foreign key (mediaid) references t_media_temp (id)	-- 添加外键
)engine=InnoDB default charset=utf8;






/**
 * 电视剧下载地址
 */
/*
create table t_tv_url(
	id int not null auto_increment,	-- 主键
	tvid int not null,			-- 电影表的主键
	resolution varchar(20),			-- 清晰度
	urls text,						-- 对应清晰度的地址
	primary key (id),
	key tvid (tvid),			-- 添加索引，增加查询速度
	constraint t_tvid_url_ibfk_1 foreign key (tvid) references t_media (id)	-- 添加外键
)engine=InnoDB default charset=utf8;
*/








