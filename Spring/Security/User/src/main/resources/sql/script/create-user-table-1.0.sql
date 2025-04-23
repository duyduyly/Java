
CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT  primary key,
    username   varchar(100) NOT NULL UNIQUE ,
    email      varchar(100) NOT NULL UNIQUE ,
    password   varchar(200) NOT NULL,
    role_id int,
    FOREIGN KEY (role_id) REFERENCES role(id)
);





