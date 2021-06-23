alter table post add column visible TINYINT NOT NULL DEFAULT 1;
alter table post alter column thumbs_up_count set default 0;
alter table post alter column favorite_count set default 0;
alter table post alter column remark_count set default 0;

alter table client alter column client_privilege set default 4;