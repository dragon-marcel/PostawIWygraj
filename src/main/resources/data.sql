INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL,NAME,SURNAME,PAID,BLOCKED) VALUES (1,'Marcel','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2','marcel.dragon@o2.pl','Marcel','Dragon','200',false);
INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL,NAME,SURNAME,PAID,BLOCKED) VALUES (2,'Adam','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2','marcel.dragon@o2.pl','Marcel','Dragon','200',false);
INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL,NAME,SURNAME,PAID,BLOCKED) VALUES (3,'Karina','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2','marcel.dragon@o2.pl','Marcel','Dragon','200',false);

INSERT INTO ROLES (ID,NAME) VALUES (1,'ADMIN');
INSERT INTO ROLES (ID,NAME) VALUES (2,'USER');

INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (1,1);
INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (1,2);



INSERT INTO PROVIDER (ID,NAME) VALUES (1,'JEDEN');
INSERT INTO PROVIDER (ID,NAME) VALUES (2,'DWA');
INSERT INTO PROVIDER (ID,NAME) VALUES (3,'TRZ');

INSERT INTO ASSORTMENT (ID,NAME) VALUES (1,'JEDEN');
INSERT INTO ASSORTMENT (ID,NAME) VALUES (2,'DWA');
INSERT INTO ASSORTMENT (ID,NAME) VALUES (3,'TRZ');

INSERT INTO CUSTOMER (ID,NAME) VALUES (1,'JEDEN');
INSERT INTO CUSTOMER (ID,NAME) VALUES (2,'DWA');
INSERT INTO CUSTOMER (ID,NAME) VALUES (3,'TRZ');