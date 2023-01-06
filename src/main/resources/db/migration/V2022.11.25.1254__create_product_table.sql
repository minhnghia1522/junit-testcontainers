CREATE TABLE public.product (
	id serial4 NOT NULL,
	picture varchar NULL,
	title varchar NULL,
	description varchar NULL,
	old_price numeric(10, 2) NULL,
	new_price numeric(10, 2) NULL,
	shop_name varchar NULL,
	version_no int8  DEFAULT 0,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);