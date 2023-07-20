CREATE TABLE IF NOT EXISTS public.role
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT role_role_key UNIQUE (role)
);

CREATE TABLE IF NOT EXISTS public.state
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    state character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT state_pkey PRIMARY KEY (id),
    CONSTRAINT state_state_key UNIQUE (state)
);
CREATE TABLE IF NOT EXISTS public.status
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (id),
    CONSTRAINT status_status_key UNIQUE (status)
);
CREATE TABLE IF NOT EXISTS public."user"
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    login character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(20) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status_id smallint NOT NULL,
    state_id smallint NOT NULL,
    recording_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_email_key UNIQUE (email),
    CONSTRAINT user_login_key UNIQUE (login),
    CONSTRAINT user_password_key UNIQUE (password),
    CONSTRAINT user_phone_key UNIQUE (phone),
    CONSTRAINT state_fk FOREIGN KEY (state_id)
        REFERENCES public.state (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT status_fk FOREIGN KEY (status_id)
        REFERENCES public.status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
INSERT INTO role (role) VALUES ('USER'), ('ADMIN');
INSERT INTO state (state) VALUES ('NOT_CONFIRMED'), ('CONFIRMED'), ('DELETE'), ('BANNED');
INSERT INTO status (status) VALUES ('NEW'), ('IN_PROGRESS'), ('DELETE'), ('COMPLETED');


INSERT INTO "user" (login, surname, name, phone, email, password, state_id, status_id)
VALUES ('login_1', 'surname_1', 'name_1', '705-705', 'email@mail.ru', 'password_1', 2, 1);
