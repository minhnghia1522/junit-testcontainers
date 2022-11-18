CREATE TABLE public.employee_role
(
   role_id int4 NOT NULL,
   employee_id int4 NOT NULL,
   FOREIGN KEY (role_id) REFERENCES public.role (id),
   FOREIGN KEY (employee_id) REFERENCES public.employee (id),
   CONSTRAINT UC_employee_role UNIQUE
   (
      role_id,
      employee_id
   )
);