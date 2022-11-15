CREATE TABLE public.menu
(
   menu_id SERIAL PRIMARY KEY,
   menu_name varchar (255) NULL,
   menu_path varchar (255) NULL,
   created_at date NULL,
   update_at date NULL
);