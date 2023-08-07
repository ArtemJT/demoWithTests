alter table users add if not exists pass_id integer references flyway.passport (id);
alter table passport add if not exists previous_owner integer references flyway.users (id);
