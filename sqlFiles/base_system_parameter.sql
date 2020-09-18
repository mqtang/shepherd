create table if not exists base_system_parameter
(
    rec_id                bigint unsigned  not null auto_increment comment '参数表主键',
    parameter_type        varchar(255)     not null comment '',
    parameter_name        varchar(255)     not null comment '',
    parameter_key         varchar(255)     not null comment '',
    parameter_value       varchar(255)     not null comment '',
    parameter_value_order varchar(255)     not null comment '',
    lan_code              tinyint unsigned not null default 0 comment '语言: 0-中文, 1-英文',
    is_deleted            tinyint unsigned not null default 0 comment '是否删除: 0-未删除, 1-删除',
    create_time           datetime         not null default current_timestamp comment '注册时间',
    update_time           datetime         not null default current_timestamp on update current_timestamp comment '上次更新时间',
    primary key pk_id (rec_id) comment '表主键索引'
) engine = InnoDB
  auto_increment = 1000
  default character set = utfmb4 comment '';