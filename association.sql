--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 12.0

-- Started on 2019-12-20 10:50:43 MSK

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

--
-- TOC entry 185 (class 1259 OID 1639582)
-- Name: contract; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contract (
    id bigint NOT NULL,
    cost real,
    count real,
    createdate timestamp without time zone,
    dateofperformance date,
    modifydate timestamp with time zone,
    status boolean,
    uid uuid,
    unitcode character varying(255),
    nomenclature_id bigint,
    offer_id bigint,
    request_id bigint
);


ALTER TABLE public.contract OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 1639610)
-- Name: contract_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contract_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contract_seq OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 1639612)
-- Name: contractidgenerator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contractidgenerator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contractidgenerator OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 1639587)
-- Name: nomenclature; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nomenclature (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    modifydate timestamp without time zone,
    name character varying(255),
    uid uuid
);


ALTER TABLE public.nomenclature OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 1639614)
-- Name: nomenclature_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nomenclature_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nomenclature_seq OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 1639592)
-- Name: offer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offer (
    id bigint NOT NULL,
    countofproduct integer NOT NULL,
    createdate timestamp without time zone,
    dateofperformance date,
    modifydate timestamp without time zone,
    priceofproduct double precision NOT NULL,
    uid uuid,
    unitcode character varying(255),
    nomenclature_id bigint,
    organization_id bigint
);


ALTER TABLE public.offer OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 1639597)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id bigint NOT NULL,
    adressoforganization character varying(255),
    inn character varying(255),
    kpp character varying(255),
    nameoforganization character varying(255),
    ogrn character varying(255),
    uid uuid
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 1639616)
-- Name: organization_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organization_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organization_seq OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 1639605)
-- Name: request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.request (
    id bigint NOT NULL,
    countofproduct real,
    createdate timestamp without time zone,
    dateofperformance date,
    modifydate timestamp without time zone,
    priceofproduct real,
    uid uuid,
    unitcode character varying(255),
    nomenclature_id bigint,
    organization_id bigint
);


ALTER TABLE public.request OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 1639618)
-- Name: request_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.request_seq OWNER TO postgres;

--
-- TOC entry 2426 (class 0 OID 1639582)
-- Dependencies: 185
-- Data for Name: contract; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2427 (class 0 OID 1639587)
-- Dependencies: 186
-- Data for Name: nomenclature; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (3, '2019-12-14 09:57:07.65', '2019-12-14 09:57:07.65', '3D принтер', 'aee07b8f-7eda-478f-8be2-9b7ac596c424');
INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (4, '2019-12-14 09:59:52.397', '2019-12-14 09:59:52.397', 'Стол офисный', '058b8777-1bc1-4b9c-8c95-34f0f3bd2623');
INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (5, '2019-12-14 10:01:10.843', '2019-12-14 10:01:10.843', 'Стул офисный', '9f7d5cf3-e139-4ba5-a60d-8eae58c076e1');
INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (1, '2019-09-17 09:45:20.019', '2019-09-17 09:45:20.019', 'Ж/Д вагон', '5a722f88-a1a8-4109-b252-acdcbd497d69');
INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (2, '2019-10-11 09:55:48.251', '2019-10-11 09:55:48.251', 'Станок фрейзерный ЧПУ', '7aeb398b-3a3c-49c9-bed1-279474cac788');
INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (6, '2019-12-14 13:00:08.906', '2019-12-14 13:00:08.906', 'Компьютер', '0e99c44d-dcf3-44f7-9f19-c4534d582dff');
INSERT INTO public.nomenclature (id, createdate, modifydate, name, uid) VALUES (7, '2019-12-14 13:28:03.211', '2019-12-14 13:28:03.211', 'Автомобиль', 'b43f44ba-e005-480d-bf7d-306a69c39f10');


--
-- TOC entry 2428 (class 0 OID 1639592)
-- Dependencies: 187
-- Data for Name: offer; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2429 (class 0 OID 1639597)
-- Dependencies: 188
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization (id, adressoforganization, inn, kpp, nameoforganization, ogrn, uid) VALUES (1, 'г. Казань, ул. Университетская, д. 35', '111111111111', '1111111', 'Мануфактура 1', '111111111111111', '12b5f899-3c90-4c80-a52c-68bf3f9db1aa');
INSERT INTO public.organization (id, adressoforganization, inn, kpp, nameoforganization, ogrn, uid) VALUES (3, 'г. Казань, ул. Университетская, д. 35', '1600000001', '1601001', 'Производитель 1', NULL, '51e57ba7-fad2-4967-ad71-aa9294a34233');


--
-- TOC entry 2430 (class 0 OID 1639605)
-- Dependencies: 189
-- Data for Name: request; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2441 (class 0 OID 0)
-- Dependencies: 190
-- Name: contract_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contract_seq', 1, false);


--
-- TOC entry 2442 (class 0 OID 0)
-- Dependencies: 191
-- Name: contractidgenerator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contractidgenerator', 1, false);


--
-- TOC entry 2443 (class 0 OID 0)
-- Dependencies: 192
-- Name: nomenclature_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.nomenclature_seq', 7, true);


--
-- TOC entry 2444 (class 0 OID 0)
-- Dependencies: 193
-- Name: organization_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_seq', 3, true);


--
-- TOC entry 2445 (class 0 OID 0)
-- Dependencies: 194
-- Name: request_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.request_seq', 1, false);


--
-- TOC entry 2293 (class 2606 OID 1639586)
-- Name: contract contract_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT contract_pkey PRIMARY KEY (id);


--
-- TOC entry 2295 (class 2606 OID 1639591)
-- Name: nomenclature nomenclature_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nomenclature
    ADD CONSTRAINT nomenclature_pkey PRIMARY KEY (id);


--
-- TOC entry 2297 (class 2606 OID 1639596)
-- Name: offer offer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT offer_pkey PRIMARY KEY (id);


--
-- TOC entry 2299 (class 2606 OID 1639604)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 2301 (class 2606 OID 1639609)
-- Name: request request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_pkey PRIMARY KEY (id);


--
-- TOC entry 2304 (class 2606 OID 1639630)
-- Name: contract fk6ukvedv12y8b4hckx372nk9wd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT fk6ukvedv12y8b4hckx372nk9wd FOREIGN KEY (request_id) REFERENCES public.request(id);


--
-- TOC entry 2308 (class 2606 OID 1639650)
-- Name: request fkaq9av55h9lae4o3y7ly8geavk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT fkaq9av55h9lae4o3y7ly8geavk FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- TOC entry 2306 (class 2606 OID 1639640)
-- Name: offer fkkwsbhmr4f5k5r55w9m5acu10s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT fkkwsbhmr4f5k5r55w9m5acu10s FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- TOC entry 2303 (class 2606 OID 1639625)
-- Name: contract fkl32km589097riyv16my6xs4mk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT fkl32km589097riyv16my6xs4mk FOREIGN KEY (offer_id) REFERENCES public.offer(id);


--
-- TOC entry 2307 (class 2606 OID 1639645)
-- Name: request fkmo9m91nsrcpnl6045doh03t42; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT fkmo9m91nsrcpnl6045doh03t42 FOREIGN KEY (nomenclature_id) REFERENCES public.nomenclature(id);


--
-- TOC entry 2305 (class 2606 OID 1639635)
-- Name: offer fkna52tehxqnlf2vceao0srk5nn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT fkna52tehxqnlf2vceao0srk5nn FOREIGN KEY (nomenclature_id) REFERENCES public.nomenclature(id);


--
-- TOC entry 2302 (class 2606 OID 1639620)
-- Name: contract fknhfw8w3mu7s34rhiqx00bvfxp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT fknhfw8w3mu7s34rhiqx00bvfxp FOREIGN KEY (nomenclature_id) REFERENCES public.nomenclature(id);


-- Completed on 2019-12-20 10:50:43 MSK

--
-- PostgreSQL database dump complete
--

