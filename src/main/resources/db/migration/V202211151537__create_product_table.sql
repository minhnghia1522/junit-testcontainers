CREATE TABLE public.product
(
   id SERIAL PRIMARY KEY NOT NULL,
   picture varchar,
   title varchar,
   description varchar,
   old_price NUMERIC
   (
      10,
      2
   ),
   new_price NUMERIC
   (
      10,
      2
   ),
   shop_name varchar
);