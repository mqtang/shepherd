-- core_user_info
create table if not exists core_user_info
(
    user_id         bigint unsigned  not null auto_increment comment '主键标识',
    username        varchar(20) comment '用户姓名',
    nickname        varchar(30) comment '昵称',
    gender          tinyint unsigned not null default 0 comment '性别: 0-未知, 1-男, 2-女',
    identity_number varchar(20) comment '身份证号码',
    avatar_id       bigint unsigned comment '用户头像',
    birthday        datetime comment '出生日期, 阳历',
    birthday_lunar  datetime comment '出生日期, 农历',
    birthday_type   tinyint unsigned not null default 0 comment '生日类型偏好: 0-阳历, 1-农历',
    constellation   tinyint unsigned not null default 0 comment '星座: 0-未知',
    member_id       varchar(30) comment '用户自定义唯一标识',
    email           varchar(30) comment '邮箱地址',
    cellphone       varchar(20) comment '手机号',
    website         varchar(90) comment '个人网站',
    account_type    tinyint unsigned not null default 0 comment '用户账号类型: 0-普通用户 1-管理员',
    account_status  tinyint unsigned not null default 0 comment '用户账号状态: 0-正常, 1-锁定, 2-冻结',
    register_ip     int unsigned comment '注册时ip地址',
    register_type   tinyint unsigned comment '注册来源',
    is_complete     tinyint unsigned not null default 0 comment '信息是否完整: 0-不完整, 1-完整',
    is_delete       tinyint unsigned not null default 0 comment '是否删除: 0-未删除, 1-删除',
    create_time     datetime         not null default current_timestamp comment '注册时间',
    update_time     datetime         not null default current_timestamp on update current_timestamp comment '上次更新时间',
    primary key pk_user_id (user_id) comment '主键索引',
    unique key uk_member_id (member_id) comment '',
    unique key uk_email (email) comment '',
    unique key uk_cellphone (cellphone) comment ''
) engine = InnoDB
  auto_increment = 1000
  default character set = utf8mb4 comment '用户基础信息表';

-- core_user_logon_info
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

-- core_logon_history
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
  default character set = utf8mb4 comment '用户登录流水表';

