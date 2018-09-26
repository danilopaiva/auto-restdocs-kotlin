create table if not exists customer(
  id  text primary key,
  name text not null,
  email text not null,
  document json not null,
  created_at  timestamp not null
);

create table if not exists account(
  id  text,
  customer_id text not null,
  type  text,
  status  text,
  amount  money not null,
  created_at  timestamp not null,
  primary key (id, customer_id),
  constraint customer_id_fk foreign key (customer_id)
    references customer (id) match simple
    on update no action on delete no action
);