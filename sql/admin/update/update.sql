1添加表字段

alter table table1 add transactor varchar(10) not Null;

2.修改某个表的字段类型及指定为空或非空
alter table 表名称 change 字段名称 字段名称 字段类型 [是否允许非空];
alter table 表名称 modify 字段名称 字段类型 [是否允许非空];

alter table 表名称 modify 字段名称 字段类型 [是否允许非空];

3.修改某个表的字段名称及指定为空或非空
alter table 表名称 change 字段原名称 字段新名称 字段类型 [是否允许非空

4.如果要删除某一字段,可用命令
ALTER TABLE mytable DROP 字段名;

alter table t_sys_user add id int comment '所属部门' after picture