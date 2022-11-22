ALTER TABLE public.post ADD COLUMN created_date date NULL;
ALTER TABLE public.post ADD COLUMN modified_date date NULL;
ALTER TABLE public.post ADD COLUMN created_by varchar (30) NULL;
ALTER TABLE public.post ADD COLUMN modified_by varchar (30) NULL;