CREATE TABLE employee
(
    id         bigserial              NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name  character varying(255) NOT NULL,
    position   character varying(255) NOT NULL,
    project_id bigint DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id)
        REFERENCES project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET DEFAULT
        NOT VALID
);

CREATE TABLE employee_personal_data
(
    id               bigserial               NOT NULL,
    description      character varying(4000) NOT NULL,
    weighting_factor smallint                NOT NULL DEFAULT 0,
    employee_id      bigint                  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id)
        REFERENCES employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE employee_professional_data
(
    id               bigserial               NOT NULL,
    description      character varying(4000) NOT NULL,
    weighting_factor smallint                NOT NULL DEFAULT 0,
    employee_id      bigint                  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id)
        REFERENCES employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

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

CREATE TABLE project_employee_role
(
    id               bigserial              NOT NULL,
    employee_role    character varying(400) NOT NULL,
    employees_number bigint                 NOT NULL DEFAULT 0,
    project_id       bigint                 NOT NULL,
    UNIQUE (employee_role),
    PRIMARY KEY (id),
    FOREIGN KEY (project_id)
        REFERENCES project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE project_employee_role_personal_data
(
    id                       bigserial               NOT NULL,
    description              character varying(4000) NOT NULL,
    weighting_factor         smallint                NOT NULL DEFAULT 0,
    project_employee_role_id bigint                  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_employee_role_id)
        REFERENCES project_employee_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE project_employee_role_professional_data
(
    id                       bigserial               NOT NULL,
    description              character varying(4000) NOT NULL,
    weighting_factor         smallint                NOT NULL DEFAULT 0,
    project_employee_role_id bigint                  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_employee_role_id)
        REFERENCES project_employee_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE weighting_factor
(
    id               smallserial NOT NULL,
    weighting_factor smallint    NOT NULL,
    UNIQUE (weighting_factor),
    PRIMARY KEY (id)
);