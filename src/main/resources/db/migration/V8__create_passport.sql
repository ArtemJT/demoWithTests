create table flyway.passport (
                                 id integer primary key not null default nextval('passport_id_seq'::regclass),
                                 expire_date timestamp,
                                 is_deleted boolean default false,
                                 number bigint,
                                 series varchar(255),
                                 uuid varchar(255)
);
