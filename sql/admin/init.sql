create database if not exists gemini_core default character set utf8;

CREATE USER 'gemini_core'@'%' IDENTIFIED BY 'gemini_core';

grant all privileges on gemini_core.* to 'gemini_core'@'%' identified by 'gemini_core';

flush privileges;