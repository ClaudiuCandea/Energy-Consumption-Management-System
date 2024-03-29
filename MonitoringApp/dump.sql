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
-- Name: measurement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.measurement (
    measurement_value double precision NOT NULL,
    device_id bigint,
    id bigint NOT NULL,
    "timestamp" timestamp(6) without time zone
);


ALTER TABLE public.measurement OWNER TO postgres;

--
-- Name: measurement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.measurement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.measurement_id_seq OWNER TO postgres;

--
-- Name: measurement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.measurement_id_seq OWNED BY public.measurement.id;


--
-- Name: measurement id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.measurement ALTER COLUMN id SET DEFAULT nextval('public.measurement_id_seq'::regclass);


--
-- Data for Name: device; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device (max_hour_consumtion, id, user_id, address, description) FROM stdin;
22	753	105	camin	TV
22	754	402	a23123	dasdsa
1000	802	402	acasa	tv
20	803	402	acasa	frigider
\.


--
-- Data for Name: measurement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.measurement (measurement_value, device_id, id, "timestamp") FROM stdin;
22.9294086	802	62	2023-11-26 12:50:22.749
22.9294086	754	63	2023-11-26 12:50:24.75
181.65992433	802	64	2023-11-26 13:50:52.822
181.65992433	754	65	2023-11-26 13:50:54.787
458.23387859	802	66	2023-11-26 14:51:22.868
458.23387859	754	67	2023-11-26 14:51:24.837
785.8514774	802	68	2023-11-26 15:51:52.898
785.8514774	754	69	2023-11-26 15:51:54.884
1236.8674129	802	70	2023-11-26 16:52:22.953
1236.8674129	754	71	2023-11-26 16:52:24.936
1633.3284593	802	72	2023-11-26 17:52:53.01
22.9294086	803	73	2023-11-26 13:01:51.308
181.65992433	803	74	2023-11-26 14:02:21.392
458.23387859	803	75	2023-11-26 15:02:51.454
785.8514774	803	76	2023-11-26 16:03:21.532
1236.8674129	803	77	2023-11-26 17:03:51.584
1633.3284593	803	78	2023-11-26 18:04:21.643
2013.1963636	803	79	2023-11-26 19:04:51.708
2568.0627841	803	80	2023-11-26 20:05:21.781
3384.6490241	803	81	2023-11-26 21:05:51.837
4149.3260846	803	82	2023-11-26 22:06:21.895
\.


--
-- Name: measurement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.measurement_id_seq', 82, true);


--
-- Name: device device_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);


--
-- Name: measurement measurement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.measurement
    ADD CONSTRAINT measurement_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

