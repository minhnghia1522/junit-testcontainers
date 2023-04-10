CREATE TABLE public.sc961
(
   id SERIAL PRIMARY KEY,
   retirement boolean NULL,
   affiliationCompany varchar (255) NULL,
   employeeNumber varchar (255) NULL,
   userCode varchar (255) NULL,
   userName varchar (255) NULL,
   userNameKana varchar (255) NULL,
   emailAddress varchar (255) NULL,
   lastPasswordChangeTime date NULL, 
   created_at date NULL,
   update_at date NULL
);


INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SSC','0001','SSC0001','山田太郎','ヤマダタロウ','taro.yamada@sunshinecity.co.jp','2024/08/10 09:00');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SSC','0002','SSC0002','山田二郎','ヤマダジロウ','jiro.yamada@sunshinecity.co.jp','2024/08/10 09:10');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SSC','0003','SSC0003','山田三郎','ヤマダサブロウ','saburo.yamada@sunshinecity.co.jp','2024/08/10 09:20');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SEP','0001','SEP0001','佐藤太郎','サトウタロウ','taro.sato@sunshine-ep.co.jp','2024/08/10 09:30');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SEP','0002','SEP0002','佐藤二郎','サトウジロウ','jiro.sato@sunshine-ep.co.jp','2024/08/10 09:40');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(true,'SEP','0003','SEP0003','佐藤三郎','サトウサブロウ','saburo.sato@sunshine-ep.co.jp','2024/08/10 09:50');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SBM','0001','SBM0001','鈴木太郎','スズキタロウ','taro.suzuki@ssc-bm.co.jp','2024/08/10 10:00');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SBM','0002','SBM0002','鈴木二郎','スズキジロウ','jiro.suzuki@ssc-bm.co.jp','2024/08/10 10:10');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress)
VALUES(false,'SBM','0003','SBM0003','鈴木三郎','スズキサブロウ','saburo.suzuki@ssc-bm.co.jp');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SBM','0004','SBM0004','鈴木四郎','スズキシロウ','shiro.suzuki@ssc-bm.co.jp','2024/08/10 09:00');

INSERT INTO public.sc961
(retirement, affiliationCompany, employeeNumber, userCode, userName, userNameKana, emailAddress, lastPasswordChangeTime)
VALUES(false,'SBM','0005','SBM0005','鈴木五郎','スズキゴロウ','goro.suzuki@ssc-bm.co.jp','2024/08/10 09:10');