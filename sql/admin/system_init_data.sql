
-- 组织架构
drop table if exists t_sys_org;
create table t_sys_org (
  id               int not null auto_increment comment '架构ID(集团O,公司C,部门D)',
  pid        int comment '上级架构，如果为null，则为顶级架构',
  name             varchar(30) not null comment '架构名称',
  type             char(1) not null comment '架构类型(集团O,公司C,部门D)',
  sort                 tinyint(1) not null default 1 comment '排序',
  status               tinyint(1) not null default 1 comment '状态(0=无效,1=有效)',
  opt_id       varchar(20) not null comment '上一次更新人account',
  opt_name     varchar(20) not null comment '上一次更新人(冗余)',
  opt_time     timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '上一次更新时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (id)
)COMMENT '组织架构' ENGINE = innoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 用户表
drop table if exists t_sys_user;
create table t_sys_user (
  account              varchar(20) not null comment '用户账号（姓名拼音）',
  name            varchar(20) not null comment '用户名称',
  password             varchar(50) not null comment '密码(MD5加密)',
  picture         varchar(50) comment '用户头像',
  id               int comment '所属部门',
  create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间(YYYY-MM-DD HH:MM:SS)',
  status               tinyint(1) not null default 1 comment '状态(0=无效,1=有效)',
  opt_id       varchar(20) not null comment '上一次更新人account',
  opt_name     varchar(20) not null comment '上一次更新人(冗余)',
  opt_time     timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '上一次更新时间(YYYY-MM-DD HH:MM:SS)',
  primary key (account)
)COMMENT '用户表' ENGINE = innoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 角色表
drop table if exists t_sys_role;
create table t_sys_role (
  id              int not null auto_increment comment '角色ID',
  name            varchar(20) not null comment '角色名称',
  sort                 tinyint(1) not null default 1 comment '排序',
  status               tinyint(1) not null default 1 comment '状态(0=无效,1=有效)',
  opt_id       varchar(20) not null comment '上一次更新人account',
  opt_name     varchar(20) not null comment '上一次更新人(冗余)',
  opt_time     timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '上一次更新时间(YYYY-MM-DD HH:MM:SS)',
  primary key (id)
)COMMENT '角色表' ENGINE = innoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 用户角色表
drop table if exists t_sys_user_role;
create table t_sys_user_role (
  account              varchar(20) not null comment '用户账号（姓名拼音）',
  id              int not null comment '角色ID'
)COMMENT '用户角色表' ENGINE = innoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 菜单表
drop table if exists t_sys_menu;
create table t_sys_menu (
  id              int not null auto_increment comment '菜单ID',
  pid       int comment '上级菜单，如果为null，则为顶级菜单',
  name            varchar(20) not null comment '菜单名称',
  type            char(1) not null comment '菜单类型（1=一级菜单，2=二级菜单，3=三级菜单，4=按钮）',
  url             varchar(100) comment '菜单url',
  icon            varchar(100) comment '菜单icon',
  permissions_code     varchar(100) comment '权限编码',
  sort                 tinyint(1) not null default 1 comment '排序',
  status               tinyint(1) not null default 1 comment '状态(0=无效,1=有效)',
  opt_id       varchar(20) not null comment '上一次更新人account',
  opt_name     varchar(20) not null comment '上一次更新人(冗余)',
  opt_time     timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '上一次更新时间(YYYY-MM-DD HH:MM:SS)',
  primary key (id)
)COMMENT '菜单表' ENGINE = innoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 权限表
drop table if exists t_sys_permissions;
create table t_sys_permissions (
  id              int not null comment '菜单ID',
  permissions_id       varchar(20) not null comment '权限ID（组织架构，用户，角色，用户组）',
  permissions_type     char(1) not null comment '权限类型（组织架构=O，用户=U，角色=R，用户组=G）'
)COMMENT '权限表' ENGINE = innoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- 组织架构
INSERT INTO t_sys_org VALUE(1,null,'集团','O','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_org VALUE(2,1,'一铭','C','2','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_org VALUE(3,2,'总裁办','D','3','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_org VALUE(4,2,'行政部','D','4','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_org VALUE(5,2,'财务部','D','5','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_org VALUE(6,2,'业务部','D','6','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_org VALUE(7,2,'技术部','D','7','1','admin','系统管理员','2017-06-27 00:00:00');
-- 用户表
-- 用户表
INSERT INTO t_sys_user VALUE('admin','系统管理员','038bdaf98f2037b31f1e75b5b4c9b26e',null,'2017-06-27 00:00:00','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_user VALUE('xiaoming','CEO小明','dd845e5e5412ba7752defdf383bf394f',null,'2017-06-27 00:00:00','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_user VALUE('xingzheng','行政专员','e62925764de9added8768eff575b1cec',null,'2017-06-27 00:00:00','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_user VALUE('caiwu','财务专员','7275a81391a0589a6e6e748d1cab4c6c',null,'2017-06-27 00:00:00','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_user VALUE('yewu','业务专员','ff8c815170b44a7f99897d9972cb0059',null,'2017-06-27 00:00:00','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_user VALUE('jishu','技术专员','3bdc50caa135d00879d552d67b4f1dae',null,'2017-06-27 00:00:00','1','admin','系统管理员','2017-06-27 00:00:00');
-- 角色表
INSERT INTO t_sys_role VALUE(1,'系统管理员','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_role VALUE(2,'ceo','2','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_role VALUE(3,'行政专员','3','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_role VALUE(4,'财务专员','4','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_role VALUE(5,'业务专员','5','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_role VALUE(6,'技术专员','6','1','admin','系统管理员','2017-06-27 00:00:00');
-- 用户角色表
INSERT INTO t_sys_user_role VALUE('admin',1);
INSERT INTO t_sys_user_role VALUE('xiaoming',2);
INSERT INTO t_sys_user_role VALUE('xingzheng',3);
INSERT INTO t_sys_user_role VALUE('caiwu',4);
INSERT INTO t_sys_user_role VALUE('yewu',5);
INSERT INTO t_sys_user_role VALUE('jishu',6);
-- 菜单表
INSERT INTO t_sys_menu VALUE(1,null,'系统管理','1','','/img/icon/set.png','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(2,1,'权限设置','2','','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(3,2,'组织架构','3','/org/gotoList','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(4,2,'角色管理','3','/role/gotoList','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(5,2,'用户管理','3','/user/gotoList','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(6,2,'菜单管理','3','/menu/gotoList','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(7,6,'查询','4','','','sys:menu:query','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(8,6,'增加','4','','','sys:menu:add','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(9,6,'修改','4','','','sys:menu:edit','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(10,6,'删除','4','','','sys:menu:del','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(11,1,'后台设置','2','','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(12,1,'日志管理','2','','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(17,12,'登陆日志','3','/menu/gotoList','','','1','1','admin','系统管理员','2017-06-27 00:00:00');

INSERT INTO t_sys_menu VALUE(13,null,'业务管理','1','/business/management','/img/icon/business.png','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(14,null,'行政管理','1','/administrative/management','/img/icon/administrative.png','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(18,14,'员工管理','2','/menu/gotoList','','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(15,null,'财务管理','1','/financial/management','/img/icon/financial.png','','1','1','admin','系统管理员','2017-06-27 00:00:00');
INSERT INTO t_sys_menu VALUE(16,null,'报表管理','1','/report/management','/img/icon/report.png','','1','1','admin','系统管理员','2017-06-27 00:00:00');


-- 权限表
-- 系统管理员拥有的菜单
INSERT INTO t_sys_permissions VALUE(1,1,'R');
INSERT INTO t_sys_permissions VALUE(2,1,'R');
INSERT INTO t_sys_permissions VALUE(3,1,'R');
INSERT INTO t_sys_permissions VALUE(4,1,'R');
INSERT INTO t_sys_permissions VALUE(5,1,'R');
INSERT INTO t_sys_permissions VALUE(6,1,'R');
INSERT INTO t_sys_permissions VALUE(7,1,'R');
INSERT INTO t_sys_permissions VALUE(8,1,'R');
INSERT INTO t_sys_permissions VALUE(9,1,'R');
INSERT INTO t_sys_permissions VALUE(10,1,'R');
INSERT INTO t_sys_permissions VALUE(11,1,'R');
INSERT INTO t_sys_permissions VALUE(12,1,'R');

INSERT INTO t_sys_permissions VALUE(13,1,'R');
INSERT INTO t_sys_permissions VALUE(14,1,'R');
INSERT INTO t_sys_permissions VALUE(15,1,'R');
INSERT INTO t_sys_permissions VALUE(16,1,'R');
INSERT INTO t_sys_permissions VALUE(17,1,'R');
INSERT INTO t_sys_permissions VALUE(18,1,'R');

-- ceo拥有的菜单
INSERT INTO t_sys_permissions VALUE(1,2,'R');
INSERT INTO t_sys_permissions VALUE(2,2,'R');
INSERT INTO t_sys_permissions VALUE(3,2,'R');
INSERT INTO t_sys_permissions VALUE(4,2,'R');
INSERT INTO t_sys_permissions VALUE(5,2,'R');
INSERT INTO t_sys_permissions VALUE(6,2,'R');
INSERT INTO t_sys_permissions VALUE(7,2,'R');
INSERT INTO t_sys_permissions VALUE(8,2,'R');
INSERT INTO t_sys_permissions VALUE(9,2,'R');
INSERT INTO t_sys_permissions VALUE(10,2,'R');
INSERT INTO t_sys_permissions VALUE(11,2,'R');
INSERT INTO t_sys_permissions VALUE(12,2,'R');

INSERT INTO t_sys_permissions VALUE(13,2,'R');
INSERT INTO t_sys_permissions VALUE(14,2,'R');
INSERT INTO t_sys_permissions VALUE(15,2,'R');
INSERT INTO t_sys_permissions VALUE(16,2,'R');

-- 业务拥有的菜单
INSERT INTO t_sys_permissions VALUE(13,5,'R');
-- 行政拥有的菜单
INSERT INTO t_sys_permissions VALUE(14,3,'R');
-- 财务拥有的菜单
INSERT INTO t_sys_permissions VALUE(15,4,'R');

