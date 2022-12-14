--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2022-11-17 23:35:53

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
-- TOC entry 210 (class 1259 OID 48855)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id bigint NOT NULL,
    email character varying(255),
    forename character varying(255),
    surname character varying(255)
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 48854)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.student ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 212 (class 1259 OID 48863)
-- Name: student_subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_subject (
    id bigint NOT NULL,
    final_grade integer,
    student_id bigint,
    subject_id bigint
);


ALTER TABLE public.student_subject OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 48862)
-- Name: student_subject_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.student_subject ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.student_subject_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 48869)
-- Name: subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subject (
    id bigint NOT NULL,
    abbreviation character varying(255),
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.subject OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 48868)
-- Name: subject_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.subject ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.subject_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3323 (class 0 OID 48855)
-- Dependencies: 210
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (id, email, forename, surname) FROM stdin;
356	mock1@mock.com	Mock1	Mock1
357	mock2@mock.com	Mock2	Mock2
358	mock3@mock.com	Mock3	Mock3
359	mock4@mock.com	Mock4	Mock4
355	update@update.com	updatedForename	updatedSurname
\.


--
-- TOC entry 3325 (class 0 OID 48863)
-- Dependencies: 212
-- Data for Name: student_subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student_subject (id, final_grade, student_id, subject_id) FROM stdin;
\.


--
-- TOC entry 3327 (class 0 OID 48869)
-- Dependencies: 214
-- Data for Name: subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subject (id, abbreviation, description, name) FROM stdin;
1	MIP	Dumitrescu	Medii si Instrumente de Programare
\.


--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 209
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_id_seq', 359, true);


--
-- TOC entry 3334 (class 0 OID 0)
-- Dependencies: 211
-- Name: student_subject_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_subject_id_seq', 4, true);


--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 213
-- Name: subject_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subject_id_seq', 1, true);


--
-- TOC entry 3174 (class 2606 OID 48961)
-- Name: student_subject final_grade; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.student_subject
    ADD CONSTRAINT final_grade CHECK (((final_grade >= 1) AND (final_grade <= 10))) NOT VALID;


--
-- TOC entry 3176 (class 2606 OID 48861)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- TOC entry 3178 (class 2606 OID 48867)
-- Name: student_subject student_subject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_subject
    ADD CONSTRAINT student_subject_pkey PRIMARY KEY (id);


--
-- TOC entry 3180 (class 2606 OID 48875)
-- Name: subject subject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT subject_pkey PRIMARY KEY (id);


--
-- TOC entry 3182 (class 2606 OID 48881)
-- Name: student_subject fk5cvx0kd792xhvd99s3bsbygfq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_subject
    ADD CONSTRAINT fk5cvx0kd792xhvd99s3bsbygfq FOREIGN KEY (subject_id) REFERENCES public.subject(id);


--
-- TOC entry 3181 (class 2606 OID 48876)
-- Name: student_subject fknhw926s5os3ei5wqfaq94j0mh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_subject
    ADD CONSTRAINT fknhw926s5os3ei5wqfaq94j0mh FOREIGN KEY (student_id) REFERENCES public.student(id);


-- Completed on 2022-11-17 23:35:54

--
-- PostgreSQL database dump complete
--

