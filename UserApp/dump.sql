--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    role smallint
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_seq OWNER TO postgres;

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, name, password, role) FROM stdin;
105	gelu	Gelu	$2a$10$4CVFp3B5LjMIU0CYwNeN9ekVOyJEylMAo80JhPW.3kpFmsR/1TPKa	1
152	gelu	Gelu	$2a$10$.YGa35JcAz52qsE5qBieKuCAjt6YLQxWCszme5gNc8yF5EJrXuJg.	1
202	mar	Eugenia	$2a$10$EiU7m56WparCaM2YRt3PpOayeq8YEKw3tpPyQlT1eOgDE0Px03g0i	0
252	ion@gmail	Ion	$2a$10$/oOcaL0B2OYOggRhXfgX3.eQlVK5FjoVSp3fVUSkdP.Ff6WkZRD9K	1
302	marcel@gmail.com	Marcel	$2a$10$dduZxeYx4E5tIi7eyq60TO5BheMWWXhoSOOX427M06MZOJ1jmiKDG	1
103	iustinel	iustinel	$2a$10$pGcFW180PHa9Xc2aZx9pneAWFzubCkTVdqMIOVHMHbDUH0B3k/2wy	0
104	gelu@gmail	gelu2	$2a$10$Hr5VESfiuEHlh6MS3r.QfOGGP.U3yAYNIo0zwlJDfH1qXaLPUXyjG	1
352	ioana@gmail	ioana	$2a$10$1TF202dXqHKmodHi/VwOfuR6FLZk195p3qj6cxj3bBknIC/2bGsSu	1
402	cristian@gmail.com	Cristian	$2a$10$47mzExKSeeic7WPigTN77ut1lAkwgV5dZgYnWi2V2YDdYds7IvgI6	1
\.


--
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_seq', 451, true);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

