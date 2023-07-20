CREATE TABLE IF NOT EXISTS public.category
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    category character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT category_category_key UNIQUE (category)
);

CREATE TABLE IF NOT EXISTS public.brand
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    brand character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT brand_pkey PRIMARY KEY (id),
    CONSTRAINT brand_brand_key UNIQUE (brand)
);

CREATE TABLE IF NOT EXISTS public.image
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    image character varying(255),
    CONSTRAINT image_pkey PRIMARY KEY (id),
    CONSTRAINT image_image_key UNIQUE (image)
);

CREATE TABLE IF NOT EXISTS public.product
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    product_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    brand_id smallint NOT NULL,
    category_id smallint NOT NULL,
	price DECIMAL(8,2),
	image_id smallint,
	description  character varying(500),
    recording_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)

