create table if not exists core_user_logon_info
(
    rec_id          bigint unsigned  not null auto_increment comment '自增主键',
    user_id         bigint unsigned  not null comment '用户表主键',
    auth_type       tinyint unsigned not null default 0 comment '认证类型: 0-memberId, 1-手机号, 2-邮箱, 3-微信, 4-QQ',
    auth_identity   varchar(255)     not null comment '身份标识',
    auth_key        varchar(500)     not null comment '登录秘钥',
    is_verified     tinyint unsigned not null default 0 comment '是否已认证 0-待认证, 1-已认证',
    last_logon_ip   int unsigned comment '上次登录的ip',
    last_logon_time datetime comment '上次登录时间',
    is_deleted      tinyint unsigned not null default 0 comment '是否删除: 0-未删除, 1-删除',
    create_time     datetime         not null default current_timestamp comment '创建时间',
    update_time     datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key pk_rec_id (rec_id) comment '主键索引',
    unique key uk_auth_type_identity (auth_type, auth_identity) comment '',
    index idx_user_id (user_id) comment '用户标识索引',
    index idx_user_id_auth_type_identity (user_id, auth_type, auth_identity) comment '登录查询索引'
) engine = InnoDB
  auto_increment = 1000
  default character set = utf8mb4 comment '用户登录表';