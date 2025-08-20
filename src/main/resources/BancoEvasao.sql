--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-08-17 14:09:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4833 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 107166)
-- Name: aluno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aluno (
    matricula bigint NOT NULL,
    nome character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    foto character varying(255)
);


ALTER TABLE public.aluno OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 107165)
-- Name: aluno_matricula_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.aluno_matricula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.aluno_matricula_seq OWNER TO postgres;

--
-- TOC entry 4834 (class 0 OID 0)
-- Dependencies: 221
-- Name: aluno_matricula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.aluno_matricula_seq OWNED BY public.aluno.matricula;


--
-- TOC entry 218 (class 1259 OID 107143)
-- Name: coordenador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coordenador (
    matricula bigint NOT NULL,
    nome character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    foto character varying(255)
);


ALTER TABLE public.coordenador OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 107142)
-- Name: coordenador_matricula_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.coordenador_matricula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.coordenador_matricula_seq OWNER TO postgres;

--
-- TOC entry 4835 (class 0 OID 0)
-- Dependencies: 217
-- Name: coordenador_matricula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.coordenador_matricula_seq OWNED BY public.coordenador.matricula;


--
-- TOC entry 220 (class 1259 OID 107154)
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso (
    codigo bigint NOT NULL,
    nome character varying(255) NOT NULL,
    evasao_media double precision,
    coordenador_id bigint NOT NULL
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 107153)
-- Name: curso_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.curso_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.curso_codigo_seq OWNER TO postgres;

--
-- TOC entry 4836 (class 0 OID 0)
-- Dependencies: 219
-- Name: curso_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.curso_codigo_seq OWNED BY public.curso.codigo;


--
-- TOC entry 224 (class 1259 OID 107175)
-- Name: matricula; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matricula (
    codigo bigint NOT NULL,
    aluno_id bigint NOT NULL,
    curso_id bigint NOT NULL,
    historico_evasao character varying(255)
);


ALTER TABLE public.matricula OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 107174)
-- Name: matricula_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.matricula_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.matricula_codigo_seq OWNER TO postgres;

--
-- TOC entry 4837 (class 0 OID 0)
-- Dependencies: 223
-- Name: matricula_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.matricula_codigo_seq OWNED BY public.matricula.codigo;


--
-- TOC entry 4658 (class 2604 OID 107193)
-- Name: aluno matricula; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluno ALTER COLUMN matricula SET DEFAULT nextval('public.aluno_matricula_seq'::regclass);


--
-- TOC entry 4656 (class 2604 OID 107210)
-- Name: coordenador matricula; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coordenador ALTER COLUMN matricula SET DEFAULT nextval('public.coordenador_matricula_seq'::regclass);


--
-- TOC entry 4657 (class 2604 OID 107227)
-- Name: curso codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso ALTER COLUMN codigo SET DEFAULT nextval('public.curso_codigo_seq'::regclass);


--
-- TOC entry 4659 (class 2604 OID 107252)
-- Name: matricula codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matricula ALTER COLUMN codigo SET DEFAULT nextval('public.matricula_codigo_seq'::regclass);


--
-- TOC entry 4825 (class 0 OID 107166)
-- Dependencies: 222
-- Data for Name: aluno; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aluno (matricula, nome, email, foto) FROM stdin;
1	Maria Silva	maria@email.com	maria.png
\.


--
-- TOC entry 4821 (class 0 OID 107143)
-- Dependencies: 218
-- Data for Name: coordenador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coordenador (matricula, nome, email, senha, foto) FROM stdin;
1	Admin	admin@admin.com	admin	adm.jpg
2	Leonardo Andrade	leonardo@gmail.com	1234	leonardo.jpg
\.


--
-- TOC entry 4823 (class 0 OID 107154)
-- Dependencies: 220
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.curso (codigo, nome, evasao_media, coordenador_id) FROM stdin;
1	Engenharia de Software	12.5	1
2	Direito	9.5	2
\.


--
-- TOC entry 4827 (class 0 OID 107175)
-- Dependencies: 224
-- Data for Name: matricula; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matricula (codigo, aluno_id, curso_id, historico_evasao) FROM stdin;
\.


--
-- TOC entry 4838 (class 0 OID 0)
-- Dependencies: 221
-- Name: aluno_matricula_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.aluno_matricula_seq', 1, true);


--
-- TOC entry 4839 (class 0 OID 0)
-- Dependencies: 217
-- Name: coordenador_matricula_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.coordenador_matricula_seq', 2, true);


--
-- TOC entry 4840 (class 0 OID 0)
-- Dependencies: 219
-- Name: curso_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.curso_codigo_seq', 2, true);


--
-- TOC entry 4841 (class 0 OID 0)
-- Dependencies: 223
-- Name: matricula_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matricula_codigo_seq', 1, false);


--
-- TOC entry 4667 (class 2606 OID 107207)
-- Name: aluno aluno_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT aluno_email_key UNIQUE (email);


--
-- TOC entry 4669 (class 2606 OID 107195)
-- Name: aluno aluno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT aluno_pkey PRIMARY KEY (matricula);


--
-- TOC entry 4661 (class 2606 OID 107226)
-- Name: coordenador coordenador_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coordenador
    ADD CONSTRAINT coordenador_email_key UNIQUE (email);


--
-- TOC entry 4663 (class 2606 OID 107212)
-- Name: coordenador coordenador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coordenador
    ADD CONSTRAINT coordenador_pkey PRIMARY KEY (matricula);


--
-- TOC entry 4665 (class 2606 OID 107229)
-- Name: curso curso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (codigo);


--
-- TOC entry 4671 (class 2606 OID 107254)
-- Name: matricula matricula_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_pkey PRIMARY KEY (codigo);


--
-- TOC entry 4672 (class 2606 OID 107243)
-- Name: curso curso_coordenador_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_coordenador_id_fkey FOREIGN KEY (coordenador_id) REFERENCES public.coordenador(matricula);


--
-- TOC entry 4673 (class 2606 OID 107265)
-- Name: matricula matricula_aluno_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_aluno_id_fkey FOREIGN KEY (aluno_id) REFERENCES public.aluno(matricula);


--
-- TOC entry 4674 (class 2606 OID 107274)
-- Name: matricula matricula_curso_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_curso_id_fkey FOREIGN KEY (curso_id) REFERENCES public.curso(codigo);


-- Completed on 2025-08-17 14:09:20

--
-- PostgreSQL database dump complete
--

