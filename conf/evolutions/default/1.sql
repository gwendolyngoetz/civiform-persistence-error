# --- JSON-based tables

# --- !Ups
create table if not exists applicants (
    id bigserial primary key,
    object jsonb not null
);

# --- !Downs

drop table if exists applicants cascade;
