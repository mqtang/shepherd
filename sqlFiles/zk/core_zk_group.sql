create table if not exists core_zk_group
(
    group_id       bigint unsigned auto_increment comment '主键',
    user_id        bigint unsigned  not null comment '用户表主键',
    group_name     varchar(50)      not null comment '分组名称',
    description    varchar(100) comment '分组描述',
    color_config   varchar(10) comment '颜色配置值',
    show_order     int unsigned comment '列表顺序',
    is_stick_top   tinyint unsigned comment '是否置顶 1-置顶, NULL/0-不置顶',
    stick_top_time datetime comment '置顶时间',
    is_safe_mode   tinyint unsigned comment '安全模式 1-启用, NULL/0-不启用',
    safe_mode_pwd  varchar(50) comment '安全模式密码',
    is_deleted     tinyint unsigned not null default 0 comment '是否删除: 0-未删除, 1-删除',
    create_time    datetime         not null default current_timestamp comment '创建时间',
    update_time    datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key pk_group_id (group_id) comment '主键索引'
) engine = InnoDB
  auto_increment = 1000
  default character set utf8mb4 comment 'zk服务器分组表'