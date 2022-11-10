-- public.employees definition

-- Drop table

-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	employee_id int4 NOT NULL,
	full_name varchar(30) NULL,
	email varchar(30) NULL,
	"position" varchar(30) NULL,
	birth_day date NULL,
	phone bpchar(10) NULL,
	department varchar(30) NULL,
	CONSTRAINT employees_pkey PRIMARY KEY (employee_id)
);
