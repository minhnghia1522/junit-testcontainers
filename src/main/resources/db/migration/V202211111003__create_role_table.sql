CREATE TABLE public.role
(
   id SERIAL PRIMARY KEY NOT NULL,
   name varchar NULL
);
INSERT INTO public.role (name) VALUES ('USER');
INSERT INTO public.role (name) VALUES ('CHECKER');
INSERT INTO public.role (name) VALUES ('CONFORMER');