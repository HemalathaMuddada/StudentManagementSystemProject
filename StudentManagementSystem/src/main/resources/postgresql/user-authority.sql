CREATE TABLE IF NOT EXISTS public.user_authority
(
    user_id integer NOT NULL,
    authority_id integer NOT NULL,
    CONSTRAINT user_authority_user_fkey FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT user_authority_authority_fkey FOREIGN KEY (authority_id)
        REFERENCES public.authority (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

INSERT INTO user_authority(user_id, authority_id) VALUES (1, 1);
