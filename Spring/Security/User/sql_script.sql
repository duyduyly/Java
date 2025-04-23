use spring_security_db;

CREATE TABLE role(
	id int  primary key,
    name varchar(100) NOT NULL
);

INSERT INTO role (id, name) VALUES 
(1, 'ADMIN'),
(2, 'USER'),
(3, 'MODERATOR');


CREATE TABLE user1
(
    id         bigInt primary key,
    username   varchar(100) NOT NULL,
    email      varchar(100) NOT NULL,
    password   varchar(200) NOT NULL,
    role_id int,
    profile_id bigInt
);

ALTER TABLE user1
ADD CONSTRAINT FK_user_role
FOREIGN KEY (role_id) REFERENCES role(id);

CREATE TABLE profile1
(
    id        bigInt primary key,
    firstName nvarchar(200),
    lastName  nvarchar(200),
    old       int,
    address   nvarchar(100),
    user_id bigInt
);

ALTER TABLE user1
    ADD CONSTRAINT fk_user_profile
        FOREIGN KEY (profile_id) REFERENCES profile1(id);

ALTER TABLE profile1
    ADD CONSTRAINT fk_profile_user
        FOREIGN KEY (user_id) REFERENCES user1(id);


