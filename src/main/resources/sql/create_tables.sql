CREATE TABLE url
(
  id bigserial NOT NULL,
  url varchar(3000),
  CONSTRAINT url_pkey PRIMARY KEY (id)
);