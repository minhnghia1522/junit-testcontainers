CREATE TABLE public.department
(
   id SERIAL PRIMARY KEY,
   sc961_id int4 NOT NULL,
   company varchar (255) NULL,
   roll varchar (255) NULL,
   dept varchar (255) NULL,
   line_g varchar (255) NULL,
   priority varchar (255) NULL,
   valid_start_date date NULL,
   valid_end_date date NULL,
   created_at date NULL,
   update_at date NULL,
   CONSTRAINT fk_sc961_department FOREIGN KEY (sc961_id) REFERENCES sc961 (id)
);