alter table post add column visible TINYINT NOT NULL DEFAULT 1;
alter table post alter column thumbs_up_count set default 0;
