CREATE SEQUENCE user_sequence

CREATE TABLE IF NOT EXISTS public."user_info"
(
    id integer NOT NULL DEFAULT nextval('user_sequence'::regclass),
    user_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    aadhar_number integer NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_name_unique_index UNIQUE (user_name)
)

INSERT INTO "user_info"(ID, USER_NAME, PASSWORD,first_name,last_name,email)
  VALUES (1, 'superadmin@demo.com', /*admin*/'$2a$08$WhflkbQx9CwspjvWr2gGu.Mc9zGUshO5u70R.b2rCvjc91DS2ABbK', 
		  'Super', 'Admin', 'superadmin@demo.com');
