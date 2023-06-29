CREATE TABLE address
(
    postal_code   INT          NOT NULL,
    street_number INT          NOT NULL,
    id            BIGINT AUTO_INCREMENT NOT NULL,
    city          VARCHAR(255) NOT NULL,
    country       VARCHAR(255) NOT NULL,
    district      VARCHAR(255) NOT NULL,
    region        VARCHAR(255) NOT NULL,
    street_name   VARCHAR(255) NOT NULL,
    CONSTRAINT PK_ADDRESS PRIMARY KEY (id)
);

CREATE TABLE building
(
    entrances INT          NOT NULL,
    floors    INT          NOT NULL,
    address_id BIGINT       NOT NULL,
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255) NOT NULL,
    CONSTRAINT PK_BUILDING PRIMARY KEY (id),
    UNIQUE (address_id)
);

CREATE TABLE comment
(
    date            DATE NOT NULL,
    id              BIGINT AUTO_INCREMENT NOT NULL,
    notification_id BIGINT NOT NULL,
    text            VARCHAR(255) NOT NULL,
    sender_id       BIGINT NOT NULL,
    CONSTRAINT PK_COMMENT PRIMARY KEY (id),
    CONSTRAINT FK_COMMENT_SENDER FOREIGN KEY (sender_id) REFERENCES user(id),
    CONSTRAINT FK_COMMENT_NOTIFICATION FOREIGN KEY (notification_id) REFERENCES notification(id)
);


CREATE TABLE notification
(
    date        DATE NOT NULL,
    id          BIGINT AUTO_INCREMENT NOT NULL,
    information VARCHAR(255) NOT NULL,
    message     VARCHAR(255) NOT NULL,
    building_id BIGINT NOT NULL,
    sender_id   BIGINT NOT NULL,
    CONSTRAINT PK_NOTIFICATION PRIMARY KEY (id),
    CONSTRAINT FK_NOTIFICATION_BUILDING FOREIGN KEY (building_id) REFERENCES building(id),
    CONSTRAINT FK_NOTIFICATION_SENDER FOREIGN KEY (sender_id) REFERENCES user(id)
);


CREATE TABLE `role`
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    user_role VARCHAR(255) NOT NULL,
    CONSTRAINT PK_ROLE PRIMARY KEY (id)
);

INSERT INTO role (id, user_role)
VALUES (1, 'USER'), (2, 'ADMIN');



CREATE TABLE unit
(
    ideal_sqm      FLOAT(12) NOT NULL,
    invoice_amount FLOAT(12) ,
    residents      INT       ,
    sqm            FLOAT(12) NOT NULL,
    taxable_pets   INT       ,
    unit_number    INT       NOT NULL,
    building_id    BIGINT    NOT NULL,
    id             BIGINT AUTO_INCREMENT NOT NULL,
    unit_type_id   BIGINT    NOT NULL,
    CONSTRAINT PK_UNIT PRIMARY KEY (id)
);

CREATE TABLE unit_owner
(
    units_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL
);

CREATE TABLE unit_type
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    unit_type VARCHAR(255) NOT NULL,
    CONSTRAINT PK_UNIT_TYPE PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    unit_id    BIGINT       ,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    password   VARCHAR(255) ,
    CONSTRAINT PK_USER PRIMARY KEY (id),
    UNIQUE (email),
);

CREATE TABLE user_role_building
(
    building_id BIGINT NOT NULL ,
    id          BIGINT AUTO_INCREMENT NOT NULL,
    role_id     BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    CONSTRAINT PK_USER_ROLE_BUILDING PRIMARY KEY (id)
);

CREATE INDEX FKa4dhtd4pomrnb3ox5090usq9x ON user_role_building (user_id);

CREATE INDEX FKaxfkbh7p7y2ihawocbcda56m9 ON unit_owner (units_id);

CREATE INDEX FKcjsdlph5v8ywnu5wqfkvk9mj8 ON user (unit_id);

CREATE INDEX FKedjuo26h9m22ny520l8r2uawu ON user_role_building (role_id);

CREATE INDEX FKeu4jewlpkddwf4gll339i15lm ON unit_owner (user_id);

CREATE INDEX FKfpa61gje7ge4eb06ovx81hbo4 ON unit (unit_type_id);

CREATE INDEX FKlk4o90b8dpywn0h666n5er8hs ON comment (notification_id);

CREATE INDEX FKnleuf2nmalpv9wbcyjkuijwqh ON user_role_building (building_id);

CREATE INDEX FKp0hq1evgtn9mkl6epaipd3g3e ON unit (building_id);

ALTER TABLE user_role_building
    ADD CONSTRAINT FKa4dhtd4pomrnb3ox5090usq9x FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE unit_owner
    ADD CONSTRAINT FKaxfkbh7p7y2ihawocbcda56m9 FOREIGN KEY (units_id) REFERENCES unit (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user
    ADD CONSTRAINT FKcjsdlph5v8ywnu5wqfkvk9mj8 FOREIGN KEY (unit_id) REFERENCES unit (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user_role_building
    ADD CONSTRAINT FKedjuo26h9m22ny520l8r2uawu FOREIGN KEY (role_id) REFERENCES `role` (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE unit_owner
    ADD CONSTRAINT FKeu4jewlpkddwf4gll339i15lm FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE unit
    ADD CONSTRAINT FKfpa61gje7ge4eb06ovx81hbo4 FOREIGN KEY (unit_type_id) REFERENCES unit_type (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE comment
    ADD CONSTRAINT FKlk4o90b8dpywn0h666n5er8hs FOREIGN KEY (notification_id) REFERENCES notification (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user_role_building
    ADD CONSTRAINT FKnleuf2nmalpv9wbcyjkuijwqh FOREIGN KEY (building_id) REFERENCES building (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE building
    ADD CONSTRAINT FKnw5wtw46f9jldj65iwd551yqw FOREIGN KEY (address_id) REFERENCES address (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE unit
    ADD CONSTRAINT FKp0hq1evgtn9mkl6epaipd3g3e FOREIGN KEY (building_id) REFERENCES building (id) ON UPDATE RESTRICT ON DELETE RESTRICT;