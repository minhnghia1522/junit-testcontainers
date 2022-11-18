CREATE TABLE public.employee
(
   id SERIAL PRIMARY KEY,
   full_name varchar (30) NULL,
   email varchar (30) NULL,
   "position" varchar (30) NULL,
   birth_day date NULL,
   phone bpchar (10) NULL,
   department varchar (30) NULL
);