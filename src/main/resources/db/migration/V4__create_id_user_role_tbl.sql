CREATE TABLE IF NOT EXISTS public.user_role
(user_id smallint NOT NULL,
role_id smallint NOT NULL);
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);