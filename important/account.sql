DROP DATABASE IF EXISTS common;

CREATE DATABASE common;

USE common;

-- 项目类型
-- 项目类型
CREATE TABLE common_project_type (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name varchar(64) NOT NULL
);

-- 用户
-- 用户
CREATE TABLE common_user (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username varchar(64) NOT NULL, -- 用户名
	password varchar(64) NOT NULL, -- 用户密码
	nickname varchar(64), -- 昵称
	icon varchar(64), -- 头像,对应file文件表id
	gender bigint, -- 性别
	phone bigint, -- 电话
	address varchar(64), -- 家庭住址
	email varchar(64), -- 电子邮件
	project_type int NOT NULL -- 项目类型
);

-- 账户
-- 账户
CREATE TABLE common_account (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id BIGINT NOT NULL, -- 用户id
	balance decimal NOT NULL DEFAULT 0, -- 账户余额
	project_type INT NOT NULL -- 项目类型
);

-- 交易记录
-- 交易记录
CREATE TABLE common_account_record (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reduce_user bigint NOT NULL, -- 减少方
	increase_user bigint NOT NULL, -- 增加方
	amount decimal NOT NULL, -- 交易金额
	type int NOT NULL, -- 交易类型
	project_type int NOT NULL -- 项目类型
);

-- 文件
-- 文件
CREATE TABLE common_file (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	real_name varchar(64), -- 原先的名字
	current_name varchar(64) NOT NULL, -- 转化后的名字
	type varchar(64) NOT NULL, -- 文件类型
	size bigint NOT NULL, -- 文件大小
	project_type int NOT NULL -- 项目类型
)