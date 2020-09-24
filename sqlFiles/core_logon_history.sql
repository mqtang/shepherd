create table if not exists core_logon_history
(
    rec_id      bigint unsigned auto_increment comment '自增主键',
    user_id     bigint unsigned comment '用户表主键',
    auth_type   varchar(12) comment '认证类型: 0-memberId, 1-手机号, 2-邮箱, 3-微信, 4-QQ',
    user_agent  varchar(255) comment '登录设备',
    logon_time  datetime comment '登录时间',
    logon_ip    int unsigned comment '登录ip',
    is_deleted  tinyint unsigned not null default 0 comment '是否删除: 0-未删除, 1-删除',
    create_time datetime         not null default current_timestamp comment '创建时间',
    update_time datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key pk_rec_id (rec_id) comment '主键索引',
    index idx_user_id (user_id) comment ''
) engine = InnoDB
  auto_increment = 1000
  default character set
      = utf8mb4 comment '用户登录流水表';

