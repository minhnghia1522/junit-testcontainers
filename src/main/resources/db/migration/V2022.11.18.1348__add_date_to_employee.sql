insert into public.employee
(
   username,
   pass_word
)
values
(
   'admin',
   '$2a$10$SVpXf/eP9qnYinnyZf1cUujIEn5oYuouGRaJd9QX86OWEw2MN61ZW'
);
insert into public.employee_role
(
   role_id,
   employee_id
)
values
(
   1,
   1
)