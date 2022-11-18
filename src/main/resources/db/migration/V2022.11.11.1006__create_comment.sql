CREATE TABLE IF NOT EXISTS public.comment
(
   id int4 PRIMARY KEY,
   post_id int4,
   comment_container varchar (30) NULL,
   created_at date NULL,
   updated_at date NULL,
   CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES post (id)
);