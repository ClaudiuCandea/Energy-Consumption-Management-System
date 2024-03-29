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
-- Name: device; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.device (
    max_hour_consumtion double precision NOT NULL,
    id bigint NOT NULL,
    user_id bigint,
    address character varying(255),
    description character varying(255)
);


ALTER TABLE public.device OWNER TO postgres;

--
-- Name: device_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.device_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.device_seq OWNER TO postgres;

--
-- Data for Name: device; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device (max_hour_consumtion, id, user_id, address, description) FROM stdin;
22	67	22	dssa	adsas
233	66	3	asdsa	adas
70	152	3	muncii	test
3	202	252	la ion	pentru ion
132	203	3	3	test 3
222	65	252	asdsadd	sadad
23313	204	3	adsdsad	adds
22	205	3	asd	asdsad
222	452	105	lab	tv
222	502	105	lab	tv
222	552	105	acasa	tv
222	553	105	acasa	tv
222	554	105	acasa	tv
222	555	105	acasa	tv
222	602	105	acasa	tv
22	652	105	casa la mine2	tv
22	653	105	casa la mine2	tv
22	654	105	casa la mine2	tv adas
22	655	105	casa la mine2	tv adas
22	753	105	camin	TV
22	754	402	a23123	dasdsa
1000	802	402	acasa	tv
20	803	402	acasa	frigider
\.


--
-- Name: device_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.device_seq', 851, true);


--
-- Name: device device_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

