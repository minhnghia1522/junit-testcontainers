CREATE TABLE IF NOT EXISTS public.post
(
   id SERIAL PRIMARY KEY,
   title varchar (30) NULL,
   post_name varchar (30) NULL,
   post_description varchar (30) NULL,
   created_at date NULL,
   updated_at date NULL
);