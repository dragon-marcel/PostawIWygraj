INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL) VALUES (1,'Marcel','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2
','marcel.dragon@o2.pl');
INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL) VALUES (2,'Adam','$2a$10$IB5PCpNh7ZktlfVjB9HgtuzoWFqgfPsDY6AROIg494i/ZQQvCZ8m.','adam.dragon@o2.pl');

INSERT INTO ROLES (ID,NAME) VALUES (1,'admin');
INSERT INTO ROLES (ID,NAME) VALUES (2,'user');

INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (1,1);
INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (2,2);