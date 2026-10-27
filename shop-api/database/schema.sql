--
-- PostgreSQL database dump
--


-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id uuid NOT NULL,
    country character varying(50),
    city character varying(50),
    street character varying(50)
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id uuid NOT NULL,
    client_name character varying(50),
    client_surname character varying(50),
    birthday date,
    gender character varying(25),
    registration_date date,
    address_id uuid
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.images (
    id uuid NOT NULL,
    images bytea
);


ALTER TABLE public.images OWNER TO postgres;

--
-- Name: prodact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prodact (
    id uuid NOT NULL,
    name character varying(70),
    category character varying(50),
    price numeric(10,2),
    available_stock integer,
    last_update_date date,
    supplier_id uuid,
    image_id uuid
);


ALTER TABLE public.prodact OWNER TO postgres;

--
-- Name: supplier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supplier (
    id uuid NOT NULL,
    name character varying(70),
    address_id uuid,
    phone_number character varying(50)
);


ALTER TABLE public.supplier OWNER TO postgres;

--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: images images_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (id);


--
-- Name: prodact prodact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prodact
    ADD CONSTRAINT prodact_pkey PRIMARY KEY (id);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (id);


--
-- Name: client client_address_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_address_id_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- Name: prodact prodact_image_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prodact
    ADD CONSTRAINT prodact_image_id_fkey FOREIGN KEY (image_id) REFERENCES public.images(id);


--
-- Name: prodact prodact_supplier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prodact
    ADD CONSTRAINT prodact_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);


--
-- Name: supplier supplier_address_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_address_id_fkey FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- PostgreSQL database dump complete
--


