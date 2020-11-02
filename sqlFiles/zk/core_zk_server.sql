create table if not exists core_zk_server
(
    server_id      bigint unsigned  not null auto_increment comment '主键',
    user_id        bigint unsigned  not null comment '用户表主键',
    group_id       bigint unsigned  not null comment '分组表主键',
    server_name    varchar(50)      not null comment 'zk名称',
    server_host    varchar(100)     not null comment 'ip和端口',
    show_title     varchar(50) comment '标题',
    description    varchar(200) comment '描述',
    color_config   varchar(10) comment '颜色配置值',
    show_order     int unsigned comment '列表顺序',
    is_stick_top   tinyint unsigned comment '是否置顶 1-置顶, NULL/0-不置顶',
    stick_top_time datetime comment '置顶时间',
    last_conn_time datetime comment '最近一次连接时间',
    is_deleted     tinyint unsigned not null default 0 comment '是否删除: 0-未删除, 1-删除',
    create_time    datetime         not null default current_timestamp comment '创建时间',
    update_time    datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key pk_server_id (server_id) comment '主键索引'
) engine = InnoDB
  auto_increment = 1000
  default character set utf8mb4 comment 'zk服务器表'