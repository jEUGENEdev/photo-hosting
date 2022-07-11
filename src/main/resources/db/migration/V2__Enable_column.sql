alter table user add column enable bool;

update user set enable = TRUE where enable IS NULL;