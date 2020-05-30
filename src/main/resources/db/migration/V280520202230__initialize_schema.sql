CREATE TABLE category
(
    id            bigserial              NOT NULL,
    category_name character varying(255) NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE project
(
    id          bigserial NOT NULL,
    created_at  timestamp,
    name        character varying(255) DEFAULT NULL,
    price       real,
    quantity    integer,
    updated_at  timestamp,
    category_id bigint    NOT NULL,
    CONSTRAINT project_pkey PRIMARY KEY (id),
    CONSTRAINT project_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES category (id)
);

CREATE TABLE "user"
(
    id       bigserial NOT NULL,
    email    character varying(255) DEFAULT NULL,
    username character varying(255) DEFAULT NULL,
    password character varying(255) DEFAULT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_email_key UNIQUE (email)
);

CREATE TABLE "role"
(
    id   bigserial NOT NULL,
    name character varying(255) DEFAULT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT role_id_fkey FOREIGN KEY (role_id)
        REFERENCES "role" (id),
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
        REFERENCES "user" (id)
);