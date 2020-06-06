CREATE TABLE common_personal_data
(
    id          bigserial               NOT NULL,
    description character varying(4000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE common_professional_data
(
    id          bigserial               NOT NULL,
    description character varying(4000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE weighting_factor
(
    id               bigserial               NOT NULL,
    name             character varying(4000) NOT NULL,
    weighting_factor smallint                NOT NULL,
    UNIQUE (name),
    UNIQUE (weighting_factor),
    PRIMARY KEY (id)
);

CREATE TABLE project_employee_role
(
    id               bigserial              NOT NULL,
    employee_role    character varying(400) NOT NULL,
    employees_number bigint                 NOT NULL DEFAULT 0,
    project_id       bigint                 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id)
        REFERENCES project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE project_employee_role_personal_data
(
    id                       bigserial NOT NULL,
    common_personal_data_id  bigint    NOT NULL,
    weighting_factor_id      bigint    NOT NULL,
    project_employee_role_id bigint    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (common_personal_data_id)
        REFERENCES common_personal_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (weighting_factor_id)
        REFERENCES weighting_factor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (project_employee_role_id)
        REFERENCES project_employee_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE project_employee_role_professional_data
(
    id                          bigserial NOT NULL,
    common_professional_data_id bigint    NOT NULL,
    weighting_factor_id         bigint    NOT NULL,
    project_employee_role_id    bigint    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (common_professional_data_id)
        REFERENCES common_professional_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (weighting_factor_id)
        REFERENCES weighting_factor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (project_employee_role_id)
        REFERENCES project_employee_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE employee
(
    id                       bigserial              NOT NULL,
    email                    character varying(255) NOT NULL,
    first_name               character varying(255) NOT NULL,
    last_name                character varying(255) NOT NULL,
    position                 character varying(255) NOT NULL,
    project_employee_role_id bigint DEFAULT NULL,
    project_id               bigint DEFAULT NULL,
    UNIQUE (email),
    PRIMARY KEY (id),
    FOREIGN KEY (project_id)
        REFERENCES project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET DEFAULT,
    FOREIGN KEY (project_employee_role_id)
        REFERENCES project_employee_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET DEFAULT
);

CREATE TABLE employee_personal_data
(
    id                      bigserial NOT NULL,
    common_personal_data_id bigint    NOT NULL,
    weighting_factor_id     bigint    NOT NULL,
    employee_id             bigint    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id)
        REFERENCES employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (common_personal_data_id)
        REFERENCES common_personal_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (weighting_factor_id)
        REFERENCES weighting_factor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE employee_professional_data
(
    id                          bigserial NOT NULL,
    common_professional_data_id bigint    NOT NULL,
    weighting_factor_id         bigint    NOT NULL,
    employee_id                 bigint    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id)
        REFERENCES employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (common_professional_data_id)
        REFERENCES common_professional_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    FOREIGN KEY (weighting_factor_id)
        REFERENCES weighting_factor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);