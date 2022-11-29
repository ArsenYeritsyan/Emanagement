-- Table: employee
CREATE TABLE my_db.employee (
    id int NOT NULL AUTO_INCREMENT,
    firstname varchar(100) NOT NULL,
    surname varchar(100) NOT NULL,
    department varchar(100) NOT NULL,
    position varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
-- Table: task
CREATE TABLE my_db.task (
    id int NOT NULL AUTO_INCREMENT,
    header varchar(100) NOT NULL,
    body varchar(255) NOT NULL,
    priority varchar(100) NOT NULL,
    status varchar(100) NOT NULL,
    employee_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id) REFERENCES my_db.employee(id)
);
-- Table: user
CREATE TABLE my_db.user (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(255) NOT NULL,
    is_account_non_expired tinyint(1) NOT NULL,
    is_account_non_locked tinyint(1) NOT NULL,
    is_credentials_non_expired tinyint(1) NOT NULL,
    is_enabled tinyint(1) NOT NULL,
    employee_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id) REFERENCES my_db.employee(id)
                        );
-- Table: role
CREATE TABLE my_db.role (
    id int NOT NULL AUTO_INCREMENT,
    role varchar(100) NOT NULL,
    PRIMARY KEY (id)
                               );
-- Table for mapping user and roles: user_role
CREATE TABLE my_db.user_role (
    user_id int NOT NULL,
    role_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES my_db.user(id),
    FOREIGN KEY (role_id) REFERENCES my_db.role(id),
    UNIQUE (user_id, role_id)
                             );
-- Table: permission
CREATE TABLE my_db.permission (
    id int NOT NULL AUTO_INCREMENT,
    permission varchar(100) NOT NULL,
    PRIMARY KEY (id)
                              );
-- Table for mapping role and permissions: role_permission
CREATE TABLE my_db.role_permission (
    role_id int NOT NULL,
    permission_id int NOT NULL,
    FOREIGN KEY (role_id) REFERENCES my_db.role(id),
    FOREIGN KEY (permission_id) REFERENCES my_db.permission(id),
    UNIQUE (role_id, permission_id)
                                   );
-- Insert basic data
INSERT INTO my_db.role VALUES (1, 'ROLE_ADMIN');
INSERT INTO my_db.role VALUES (2, 'ROLE_TEAMLEAD');
INSERT INTO my_db.role VALUES (3, 'ROLE_EMPLOYEE');

INSERT INTO my_db.permission VALUES (1, 'USER_READ');
INSERT INTO my_db.permission VALUES (2, 'USER_WRITE');
INSERT INTO my_db.permission VALUES (3, 'EMPLOYEE_READ');
INSERT INTO my_db.permission VALUES (4, 'EMPLOYEE_WRITE');
INSERT INTO my_db.permission VALUES (5, 'TASK_READ');
INSERT INTO my_db.permission VALUES (6, 'TASK_WRITE');

INSERT INTO my_db.role_permission VALUES (1, 1);
INSERT INTO my_db.role_permission VALUES (1, 2);
INSERT INTO my_db.role_permission VALUES (1, 3);
INSERT INTO my_db.role_permission VALUES (1, 4);
INSERT INTO my_db.role_permission VALUES (1, 5);
INSERT INTO my_db.role_permission VALUES (1, 6);

INSERT INTO my_db.role_permission VALUES (2, 3);
INSERT INTO my_db.role_permission VALUES (2, 4);
INSERT INTO my_db.role_permission VALUES (2, 5);
INSERT INTO my_db.role_permission VALUES (2, 6);

INSERT INTO my_db.role_permission VALUES (3, 5);
INSERT INTO my_db.role_permission VALUES (3, 6);

-- Insert ROLE_ADMIN user
INSERT INTO my_db.employee VALUES (1, 'Arsen', 'Yeritsyan', 'IT', 'Java Developer');
INSERT INTO my_db.task (header, body, priority, status, employee_id) VALUES ('Task 1', 'Do smth admins and interesting.', 'Low', 'In Progress', 1);
INSERT INTO my_db.task (header, body, priority, status, employee_id) VALUES ('Task 2', 'Do smth admins and boring.', 'Hight', 'In Progress', 1);
INSERT INTO my_db.user VALUES (1, 'admin', '$2a$10$LaUT71g.ofUoHT1KAoL9iOcW2COnAU2G7rldUSt3PbbFZmg4BNOvm', 1, 1, 1, 1, 1);

INSERT INTO my_db.user_role VALUES (1, 1);

-- Insert ROLE_TEAMLEAD user
INSERT INTO my_db.employee VALUES (1, 'Kirill', 'Sergeevich', 'IT', 'Java Developer/Teamlead');
INSERT INTO my_db.task (header, body, priority, status, employee_id) VALUES ('Task 1', 'Do smth teamleads and interesting.', 'Low', 'In Progress', 2);
INSERT INTO my_db.task (header, body, priority, status, employee_id) VALUES ('Task 2', 'Do smth teamleads and boring.', 'Hight', 'In Progress', 2);
INSERT INTO my_db.user VALUES (2, 'teamlead', '$2a$10$LaUT71g.ofUoHT1KAoL9iOcW2COnAU2G7rldUSt3PbbFZmg4BNOvm', 1, 1, 1, 1, 1);

INSERT INTO my_db.user_role VALUES (2, 2);

-- Insert ROLE_EMPLOYEE user
INSERT INTO my_db.employee VALUES (1, 'Sergey', 'Fedorenko', 'Pavlovich', 'IT', 'Java Developer');
INSERT INTO my_db.task (header, body, priority, status, employee_id) VALUES ('Task 1', 'Do smth Java-crazy.', 'Low', 'In Progress', 3);
INSERT INTO my_db.task (header, body, priority, status, employee_id) VALUES ('Task 2', 'Do smth Java-crazy again.', 'Hight', 'In Progress', 3);
INSERT INTO my_db.user VALUES (3, 'employee', '$2a$10$LaUT71g.ofUoHT1KAoL9iOcW2COnAU2G7rldUSt3PbbFZmg4BNOvm', 1, 1, 1, 1, 1);

INSERT INTO my_db.user_role VALUES (3, 3);





