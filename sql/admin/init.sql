create database if not exists boot_admin default character set utf8;

CREATE USER 'boot_admin'@'%' IDENTIFIED BY 'boot_admin';

grant all privileges on boot_admin.* to 'boot_admin'@'%' identified by 'boot_admin';

flush privileges;