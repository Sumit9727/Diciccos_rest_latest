--
-- PostgreSQL database cluster dump
--

-- Started on 2020-09-16 11:46:01

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md52a29a4f7eb0a98abca0992ca3fb555b6';






--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-09-16 11:46:02

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

-- Completed on 2020-09-16 11:46:04

--
-- PostgreSQL database dump complete
--

--
-- Database "diciccos" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-09-16 11:46:04

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

--
-- TOC entry 2906 (class 1262 OID 24576)
-- Name: diciccos; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE diciccos WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_India.1252' LC_CTYPE = 'English_India.1252';


ALTER DATABASE diciccos OWNER TO postgres;

\connect diciccos

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
-- TOC entry 207 (class 1259 OID 24752)
-- Name: dish_extras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dish_extras (
    dish_extra_id integer NOT NULL,
    dish_extra_description character varying(255),
    dish_name_id integer,
    is_checkbox character varying(30),
    is_radio character varying(30),
    is_required character varying(30),
    dish_extra_name character varying(255) NOT NULL,
    dish_extra_parent_id integer,
    dish_price double precision,
    dish_extra_status character varying(255)
);


ALTER TABLE public.dish_extras OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24750)
-- Name: dish_extras_dish_extra_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dish_extras_dish_extra_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dish_extras_dish_extra_id_seq OWNER TO postgres;

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 206
-- Name: dish_extras_dish_extra_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dish_extras_dish_extra_id_seq OWNED BY public.dish_extras.dish_extra_id;


--
-- TOC entry 209 (class 1259 OID 24763)
-- Name: dish_names; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dish_names (
    dish_name_id integer NOT NULL,
    dish_name_description character varying(255),
    dish_name character varying(255) NOT NULL,
    dish_type_id integer,
    dish_extra character varying(30),
    dish_price double precision NOT NULL,
    dish_status character varying(255)
);


ALTER TABLE public.dish_names OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 24761)
-- Name: dish_names_dish_name_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dish_names_dish_name_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dish_names_dish_name_id_seq OWNER TO postgres;

--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 208
-- Name: dish_names_dish_name_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dish_names_dish_name_id_seq OWNED BY public.dish_names.dish_name_id;


--
-- TOC entry 215 (class 1259 OID 24813)
-- Name: dish_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dish_types (
    dish_type_id integer NOT NULL,
    dish_type_description character varying(255),
    dish_type_name character varying(255),
    menu_type_id integer,
    dish_type_status character varying(255)
);


ALTER TABLE public.dish_types OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 24811)
-- Name: dish_types_dish_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dish_types_dish_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dish_types_dish_type_id_seq OWNER TO postgres;

--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 214
-- Name: dish_types_dish_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dish_types_dish_type_id_seq OWNED BY public.dish_types.dish_type_id;


--
-- TOC entry 217 (class 1259 OID 24824)
-- Name: menu_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu_types (
    menu_type_id integer NOT NULL,
    menu_type_description character varying(255),
    menu_name character varying(255),
    restaurent_id integer,
    menu_status character varying(255)
);


ALTER TABLE public.menu_types OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24822)
-- Name: menu_types_menu_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menu_types_menu_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menu_types_menu_type_id_seq OWNER TO postgres;

--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 216
-- Name: menu_types_menu_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_types_menu_type_id_seq OWNED BY public.menu_types.menu_type_id;


--
-- TOC entry 211 (class 1259 OID 24796)
-- Name: menus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menus (
    menu_id integer NOT NULL,
    menu_type_id integer
);


ALTER TABLE public.menus OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 24794)
-- Name: menus_menu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menus_menu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menus_menu_id_seq OWNER TO postgres;

--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 210
-- Name: menus_menu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menus_menu_id_seq OWNED BY public.menus.menu_id;


--
-- TOC entry 203 (class 1259 OID 24631)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id integer NOT NULL,
    checks character varying(255),
    dining_option character varying(30),
    modified_date character varying(255),
    promised_date character varying(255) NOT NULL,
    restaurant_name character varying(30),
    revenue_center character varying(255),
    service_area character varying(30),
    source character varying(255) NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 24629)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 202
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 213 (class 1259 OID 24804)
-- Name: restaurents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.restaurents (
    restaurent_id integer NOT NULL,
    address_line_1 character varying(50) NOT NULL,
    address_line_2 character varying(50),
    city character varying(30) NOT NULL,
    country character varying(30) NOT NULL,
    restaurent_name character varying(30) NOT NULL,
    state character varying(30) NOT NULL,
    restaurent_status character varying(30)
);


ALTER TABLE public.restaurents OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 24802)
-- Name: restaurents_restaurent_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.restaurents_restaurent_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.restaurents_restaurent_id_seq OWNER TO postgres;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 212
-- Name: restaurents_restaurent_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.restaurents_restaurent_id_seq OWNED BY public.restaurents.restaurent_id;


--
-- TOC entry 205 (class 1259 OID 24650)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    client_id character varying(255) NOT NULL,
    user_name character varying(15),
    user_password character varying(255),
    user_role character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24648)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 2737 (class 2604 OID 24755)
-- Name: dish_extras dish_extra_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_extras ALTER COLUMN dish_extra_id SET DEFAULT nextval('public.dish_extras_dish_extra_id_seq'::regclass);


--
-- TOC entry 2738 (class 2604 OID 24766)
-- Name: dish_names dish_name_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_names ALTER COLUMN dish_name_id SET DEFAULT nextval('public.dish_names_dish_name_id_seq'::regclass);


--
-- TOC entry 2741 (class 2604 OID 24816)
-- Name: dish_types dish_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_types ALTER COLUMN dish_type_id SET DEFAULT nextval('public.dish_types_dish_type_id_seq'::regclass);


--
-- TOC entry 2742 (class 2604 OID 24827)
-- Name: menu_types menu_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_types ALTER COLUMN menu_type_id SET DEFAULT nextval('public.menu_types_menu_type_id_seq'::regclass);


--
-- TOC entry 2739 (class 2604 OID 24799)
-- Name: menus menu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menus ALTER COLUMN menu_id SET DEFAULT nextval('public.menus_menu_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 24634)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 2740 (class 2604 OID 24807)
-- Name: restaurents restaurent_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurents ALTER COLUMN restaurent_id SET DEFAULT nextval('public.restaurents_restaurent_id_seq'::regclass);


--
-- TOC entry 2736 (class 2604 OID 24653)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 2890 (class 0 OID 24752)
-- Dependencies: 207
-- Data for Name: dish_extras; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dish_extras (dish_extra_id, dish_extra_description, dish_name_id, is_checkbox, is_radio, is_required, dish_extra_name, dish_extra_parent_id, dish_price, dish_extra_status) FROM stdin;
3	\N	1	yes	no	no	No Broccoli	1	0	\N
69	\N	3	yes	no	yes	Sausage	68	2.5	active
70	\N	3	yes	no	yes	Pepperoni	68	2.5	active
71	\N	3	yes	no	yes	Mushroom	68	2.5	active
72	\N	3	yes	no	yes	Meatball	68	2.5	active
73	\N	3	yes	no	yes	Salami	68	2.5	active
74	\N	3	yes	no	yes	Artichoke Heart	68	2.5	active
75	\N	3	yes	no	yes	Onions	68	2.5	active
76	\N	3	yes	no	yes	Bell Peppers	68	2.5	active
77	\N	3	yes	no	yes	Canadian Bacon	68	2.5	active
78	\N	3	yes	no	yes	Pineapple	68	2.5	active
79	\N	3	yes	no	yes	Olives	68	2.5	active
80	\N	3	yes	no	yes	Tomato	68	2.5	active
81	\N	3	yes	no	yes	Jalapeno	68	2.5	active
82	\N	3	yes	no	yes	Bacon	68	2.5	active
83	\N	3	yes	no	yes	Garlic	68	2.5	active
1	\N	1	yes	no	no	Pizza/Calzon Exceptions Milano	0	0	active
2	\N	1	yes	no	no	No Chicken	1	0	active
4	\N	1	yes	no	no	No Cheese	1	0	active
5	\N	1	yes	no	no	No Sauce	1	0	active
6	\N	1	yes	no	no	Additional Toppings	0	0	active
7	\N	1	yes	no	yes	Sausage	6	2.5	active
8	\N	1	yes	no	yes	Pepperoni	6	2.5	active
9	\N	1	yes	no	yes	Mushroom	6	2.5	active
10	\N	1	yes	no	yes	Meatball	6	2.5	active
11	\N	1	yes	no	no	Salami*	6	2.5	active
12	\N	1	yes	no	yes	Artichoke Heart	6	2.5	active
13	\N	1	yes	no	yes	Onions	6	2.5	active
14	\N	1	yes	no	yes	Bell Peppers	6	2.5	active
15	\N	1	yes	no	yes	Canadian Bacon	6	2.5	active
16	\N	1	yes	no	yes	Pineapple	6	2.5	active
17	\N	1	yes	no	yes	Olives	6	2.5	active
18	\N	1	yes	no	yes	Tomato	6	2.5	active
19	\N	1	yes	no	yes	Jalapeno	6	2.5	active
20	\N	1	yes	no	yes	Bacon	6	2.5	active
21	\N	1	yes	no	yes	Garlic	6	2.5	active
22	\N	1	yes	no	no	Premium Toppings	0	0	active
23	\N	1	yes	no	yes	Anchovies	22	7	active
24	\N	1	yes	no	yes	Chicken	22	7	active
25	\N	1	yes	no	yes	Shrimp	22	7	active
26	\N	1	yes	no	yes	Clams	22	7	active
55	\N	1	yes	no	yes	Anchovies	54	7	active
56	\N	1	yes	no	yes	Chicken	54	7	active
57	\N	1	yes	no	yes	Shrimp	54	7	active
58	\N	1	yes	no	yes	Clams	54	7	active
59		3	yes	no	no	Pizza/Calzon Exceptions Vegetarian	0	0	active
60		3	yes	no	no	No Mushroom	59	0	active
61		3	yes	no	no	No Onion	59	0	active
62		3	yes	no	no	No Bell Pepper	59	0	active
63		3	yes	no	no	No Salami	59	0	active
64		3	yes	no	no	No Olive	59	0	active
65		3	yes	no	no	No Tomato	59	0	active
66		3	yes	no	no	No Cheese	59	0	active
67		3	yes	no	no	No Sauce	59	0	active
68		3	yes	no	yes	Additional Toppings	0	0	active
84	Please choose 1	12	no	yes	yes	Fried Ravioli Sauce	0	0	active
85	\N	12	no	yes	yes	Ranch-Bleu	84	0	active
86	\N	12	no	yes	yes	Red Sauce	84	0	active
87	\N	12	no	yes	yes	No Sauce	84	0	active
88	Please choose 1	16	no	yes	yes	Size	0	0	active
89	\N	16	no	yes	yes	Cup	88	3.99	active
90	\N	16	no	yes	yes	Bowl	88	6.99	active
91	Please choose 1	17	no	yes	yes	Dressing Options	0	0	active
92	\N	17	yes	no	yes	The Wedge Exceptions	0	0	active
93	\N	17	yes	no	yes	Enhancements	0	0	active
94	\N	17	no	yes	yes	Blue-Ranch	91	0	active
95	\N	17	no	yes	yes	Italian	91	0	active
96	\N	17	no	yes	yes	1000 Island	91	0	active
97	\N	17	no	yes	yes	Caesar	91	0	active
98	\N	17	no	yes	yes	Oil & Vinegar	91	0	active
99	\N	17	no	yes	yes	No Dressing	91	0	active
100	\N	17	yes	no	no	No Tomatoes	92	0	active
101	\N	17	yes	no	no	No Onions	92	0	active
102	\N	17	yes	no	no	No Dressing	92	0	active
103	\N	17	yes	no	no	No Bacon Bits	92	0	active
104	\N	17	yes	no	no	Dressing on Side	92	0	active
105	\N	17	yes	no	no	Chicken Breast	93	6	active
106	\N	17	yes	no	no	Shrimp	93	7	active
107	\N	17	yes	no	no	Steak	93	8	active
108	\N	19	yes	no	no	Cannelloni Exceptions	0	0	active
109	\N	19	yes	no	no	Enhancements	0	0	active
110	\N	19	yes	no	no	No Mozzarella	108	0	active
111	\N	19	yes	no	no	No Sauce	108	0	active
112	\N	19	yes	no	no	Chicken Breast	109	6	active
113	\N	19	yes	no	no	Shrimp	109	7	active
114	\N	19	yes	no	no	Steak	109	8	active
115	Please choose 1	20	no	yes	yes	Dressing Options	0	0	active
116	\N	20	yes	no	no	Antipasto Salad Exceptions	0	0	active
117	\N	20	yes	no	no	Enhancements	0	0	active
118	\N	20	no	yes	yes	Blue-Ranch	115	0	active
119	\N	20	no	yes	yes	Italian	115	0	active
120	\N	20	no	yes	yes	Italian	115	0	active
121	\N	20	no	yes	yes	1000 Island	115	0	active
122	\N	20	no	yes	yes	Caesar	115	0	active
123	\N	20	no	yes	yes	Oil & Vinegar	115	0	active
124	\N	20	yes	no	no	No Dressing	0	0	active
125	\N	20	yes	no	no	No Olives	116	0	active
126	\N	20	yes	no	no	No Tomatoes	116	0	active
127	\N	20	yes	no	no	No Salami	116	0	active
128	\N	20	yes	no	no	No Cheddar	116	0	active
129	\N	20	yes	no	no	No Mozzarella	116	0	active
130	\N	20	yes	no	no	Chicken Breast	117	6	active
131	\N	20	yes	no	no	Shrimp	117	7	active
132	\N	20	yes	no	no	Steak	117	8	active
133	Please choose 1	21	no	yes	yes	Dressing Options	0	0	active
134	\N	21	yes	no	no	House Salad Exceptions	0	0	active
135	\N	21	yes	no	no	Enhancements	0	0	active
136	\N	21	no	yes	yes	Blue-Ranch	133	0	active
137	\N	21	no	yes	yes	Italian	133	0	active
138	\N	21	no	yes	yes	1000 Island	133	0	active
139	\N	21	no	yes	yes	Caesar	133	0	active
140	\N	21	no	yes	yes	Oil & Vinegar	133	0	active
141	\N	21	no	yes	no	No Dressing	133	0	active
142	\N	21	yes	no	no	No Salami	134	0	active
143	\N	21	yes	no	no	No Pepperoncini	134	0	active
144	\N	21	yes	no	no	No Mozzarella	134	0	active
145	\N	21	yes	no	no	No Croutons	134	0	active
146	\N	21	yes	no	no	Chicken Breast	135	6	active
147	\N	21	yes	no	no	Shrimp	135	7	active
148	\N	21	yes	no	no	Steak	135	8	active
149	Please choose 1	22	no	yes	yes	Dressing Options	0	0	active
150	\N	22	yes	no	no	Caesar Salad Exceptions	0	0	active
151	\N	22	yes	no	no	Enhancements	0	0	active
152	\N	22	no	yes	yes	Blue-Ranch	149	0	active
153	\N	22	no	yes	yes	Italian	149	0	active
154	\N	22	no	yes	yes	1000 Island	149	0	active
155	\N	22	no	yes	yes	Caesar	149	0	active
156	\N	22	no	yes	yes	Oil & Vinegar	149	0	active
157	\N	21	no	yes	no	No Dressing	133	0	active
158	\N	22	yes	no	no	No Mozzarella	150	0	active
159	\N	22	yes	no	no	No Sauce	150	0	active
160	\N	22	yes	no	no	Chicken Breast	151	6	active
161	\N	22	yes	no	no	Shrimp	151	7	active
162	\N	22	yes	no	no	Steak	151	8	active
163	Please choose 1	23	no	yes	yes	Sandwich Side Options	0	0	active
164	\N	23	yes	no	no	Sandwich Exceptions	0	0	active
165	\N	23	no	yes	yes	Fries	163	0	active
166	\N	23	no	yes	yes	Minnestrone	163	0	active
167	\N	23	no	yes	yes	Salad	163	0	active
168	\N	23	yes	no	no	No Marinara	164	0	active
169	\N	23	yes	no	no	No Mozzarella	164	0	active
170	Please choose 1	24	no	yes	yes	Sandwich Side Options	0	0	active
171	\N	24	yes	no	no	Sandwich Exceptions	0	0	active
172	\N	24	no	yes	yes	Fries	170	0	active
173	\N	24	no	yes	yes	Minnestrone	170	0	active
174	\N	24	no	yes	yes	Salad	170	0	active
175	\N	24	yes	no	no	No Marinara	171	0	active
176	\N	24	yes	no	no	No Mozzarella	171	0	active
177	Please choose 1	25	no	yes	yes	Dinner Specials Enhancements	0	0	active
178	\N	25	no	yes	yes	Dinner Cup of Minestrone	177	0	active
179	\N	25	no	yes	yes	Dinner Side House Salad	177	0	active
180	Please choose 1	26	no	yes	yes	Dinner Specials Enhancements	0	0	active
181	\N	26	yes	no	no	Chicken Picatta/Parm Exceptions	0	0	active
182	\N	26	no	yes	yes	Dinner Cup of Minestrone	180	0	active
183	\N	26	no	yes	yes	Dinner Side House Salad	180	0	active
184	\N	26	yes	no	no	No Sauce	181	0	active
185	\N	26	yes	no	no	No Mozzarella	181	0	active
186	\N	26	yes	no	no	No Spaghetti	181	0	active
187	\N	26	yes	no	no	No Vegetables	181	0	active
188	\N	26	yes	no	no	Sub Spaghetti	181	0	active
189	\N	26	yes	no	no	Sub Veggies	181	0	active
190	Please choose 1	27	no	yes	yes	Dinner Specials Enhancements	0	0	active
191	\N	27	yes	no	no	Chicken Picatta/Parm Exceptions	0	0	active
192	\N	27	no	yes	yes	Dinner Cup of Minestrone	190	0	active
193	\N	27	no	yes	yes	Dinner Side House Salad	190	0	active
194	\N	27	yes	no	no	No Sauce	191	0	active
195	\N	27	yes	no	no	No Mozzarella	191	0	active
196	\N	27	yes	no	no	No Spaghetti	191	0	active
197	\N	27	yes	no	no	No Vegetables	191	0	active
198	\N	27	yes	no	no	Sub Spaghetti	191	0	active
199	\N	27	yes	no	no	Sub Veggies	191	0	active
200	Please choose 1	28	no	yes	yes	Dinner Specials Enhancements	0	0	active
201	\N	28	no	yes	yes	Dinner Cup of Minestrone	200	0	active
202	\N	28	no	yes	yes	Dinner Side House Salad	200	0	active
203	Please choose 1	29	no	yes	yes	Meat Temperature	0	0	active
204	Please choose 1	29	no	yes	yes	Dinner Specials Enhancements	0	0	active
205	\N	29	no	yes	yes	Rare	203	0	active
206	\N	29	no	yes	yes	Medium Rare	203	0	active
207	\N	29	no	yes	yes	Medium	203	0	active
208	\N	29	no	yes	yes	Medium Well	203	0	active
209	\N	29	no	yes	yes	Well Done	203	0	active
210	\N	29	no	yes	yes	Dinner Cup of Minestrone	204	0	active
211	\N	29	no	yes	yes	Dinner Side House Salad	204	0	active
212	Please choose up to 1	30	yes	no	no	Seafood Fettuccine Exceptions	0	0	active
213	\N	30	yes	no	no	No Mussels	212	0	active
214	\N	30	yes	no	no	No Scallops	212	0	active
215	\N	30	yes	no	no	No Shrimp	212	0	active
216	\N	30	yes	no	no	No Clams	212	0	active
217	\N	30	yes	no	no	No Alfredo	212	0	active
218	Please choose up to 1	32	yes	no	no	Veggie Eggplant Parmesan Exceptions	0	0	active
219	Please choose up to 1	32	yes	no	no	Stuffed Favorites Enhancements	0	0	active
220	\N	32	yes	no	no	No Sauce	218	0	active
221	\N	32	yes	no	no	No Mozzarella	218	0	active
222	\N	32	yes	no	no	Cup of Minestrone Soup	219	3.75	active
223	\N	32	yes	no	no	Side House Salad	219	3.75	active
224	Please choose up to 1	33	yes	no	no	Spinach Ravioli Exceptions	0	0	active
225	Please choose up to 1	33	yes	no	no	Stuffed Favorites Enhancements	0	0	active
226	\N	33	yes	no	no	No Sauce	224	0	active
227	\N	33	yes	no	no	No Artichoke Hearts	224	0	active
228	\N	33	yes	no	no	Cup of Minestrone Soup	225	3.75	active
229	\N	33	yes	no	no	Side House Salad	225	3.75	active
230	Please choose 1	34	yes	no	yes	Meatballs or Sausage	0	0	active
231	\N	34	yes	no	no	Cannelloni Exceptions	0	0	active
232	\N	34	yes	no	yes	Stuffed Favorites Enhancements	0	0	active
233	\N	34	no	yes	yes	2 Meatballs	230	0	active
234	\N	34	no	yes	yes	1 Sausage	230	0	active
235	\N	34	no	yes	yes	No Protein	230	0	active
236	\N	34	yes	no	yes	No Mozzarella	231	0	active
237	\N	34	yes	no	yes	No Sauce	231	0	active
238	\N	34	yes	no	yes	Cup of Minestrone Soup	232	3.75	active
239	\N	34	yes	no	yes	Side House Salad	232	3.75	active
240	Please choose 1	35	no	yes	yes	Meatballs or Sausage	0	0	active
241	Please choose up to 1	35	yes	no	no	Manicotti Exceptions	0	0	active
242	Please choose up to 1	35	yes	no	no	Stuffed Favorites Enhancements	0	0	active
243	\N	35	no	yes	yes	2 Meatballs	240	0	active
244	\N	35	no	yes	yes	1 Sausage	240	0	active
245	\N	35	no	yes	yes	No Protein	240	0	active
246	\N	35	yes	no	yes	No Sauce	241	0	active
247	\N	35	yes	no	yes	No Marinara	241	0	active
248	\N	35	yes	no	yes	Cup of Minestrone Soup	242	3.75	active
249	\N	35	yes	no	yes	Side House Salad	242	3.75	active
250	Please choose up to 1	36	yes	no	no	Veggie Eggplant Parmesan Exceptions	0	0	active
251	Please choose up to 1	36	yes	no	no	Stuffed Favorites Enhancements	0	0	active
252	\N	36	yes	no	no	No Sauce	250	0	active
253	\N	36	yes	no	no	No Mozzarella	250	0	active
254	\N	36	yes	no	no	Cup of Minestrone Soup	251	3.75	active
255	\N	36	yes	no	no	Side House Salad	251	3.75	active
256	Please choose up to 1	37	yes	no	no	Ravioli Marsala Exceptions	0	0	active
257	Please choose up to 1	37	yes	no	no	Stuffed Favorites Enhancements	0	0	active
258	\N	37	yes	no	no	No Chicken	256	0	active
259	\N	37	yes	no	no	No Mushrooms	256	0	active
260	Please choose up to 1	37	yes	no	no	Cup of Minestrone Soup	257	3.75	active
261	Please choose up to 1	37	yes	no	no	Side House Salad	257	3.75	active
262	\N	38	yes	no	no	Pizza/Calzon Exceptions Milano	0	0	active
263	\N	38	yes	no	no	Additional Toppings	0	0	active
264	\N	38	yes	no	no	Premium Toppings	0	0	active
265	\N	38	yes	no	no	No Chicken	262	0	active
266	\N	38	yes	no	no	No Broccoli	262	0	active
267	\N	38	yes	no	no	No Cheese	262	0	active
268	\N	38	yes	no	no	No Sauce	262	0	active
269	\N	38	yes	no	no	Sausage*	263	2.5	active
270	\N	38	yes	no	no	Pepperoni*	263	2.5	active
271	\N	38	yes	no	no	Mushroom*	263	2.5	active
272	\N	38	yes	no	no	Meatball*	263	2.5	active
273	\N	38	yes	no	no	Salami*	263	2.5	active
274	\N	38	yes	no	no	Artichoke Heart*	263	2.5	active
275	\N	38	yes	no	no	Onions*	263	2.5	active
276	\N	38	yes	no	no	Bell Peppers*	263	2.5	active
277	\N	38	yes	no	no	Canadian Bacon*	263	2.5	active
278	\N	38	yes	no	no	Pineapple*	263	2.5	active
279	\N	38	yes	no	no	Olives*	263	2.5	active
280	\N	38	yes	no	no	Tomato*	263	2.5	active
281	\N	38	yes	no	no	Jalapeno*	263	2.5	active
282	\N	38	yes	no	no	Bacon*	263	2.5	active
283	\N	38	yes	no	no	Garlic*	263	2.5	active
284	\N	38	yes	no	no	Anchovies*	264	7	active
285	\N	38	yes	no	no	Chicken*	264	7	active
286	\N	38	yes	no	no	Shrimp*	264	7	active
287	\N	38	yes	no	no	Clams*	264	7	active
288	\N	6	yes	no	no	Pizza/Calzon Exceptions Milano	0	0	active
289	\N	6	yes	no	no	Additional Toppings	0	0	active
290	\N	6	yes	no	no	Premium Toppings	0	0	active
291	\N	6	yes	no	no	No Chicken	288	0	active
292	\N	6	yes	no	no	No Broccoli	288	0	active
293	\N	6	yes	no	no	No Cheese	288	0	active
294	\N	6	yes	no	no	No Sauce	288	0	active
295	\N	6	yes	no	no	Sausage*	289	2.5	active
296	\N	6	yes	no	no	Pepperoni*	289	2.5	active
297	\N	6	yes	no	no	Mushroom*	289	2.5	active
298	\N	6	yes	no	no	Meatball*	289	2.5	active
299	\N	6	yes	no	no	Salami*	289	2.5	active
300	\N	6	yes	no	no	Artichoke Heart*	289	2.5	active
301	\N	6	yes	no	no	Onions*	289	2.5	active
302	\N	6	yes	no	no	Bell Peppers*	289	2.5	active
303	\N	6	yes	no	no	Canadian Bacon*	289	2.5	active
304	\N	6	yes	no	no	Pineapple*	289	2.5	active
305	\N	6	yes	no	no	Olives*	289	2.5	active
306	\N	6	yes	no	no	Tomato*	289	2.5	active
307	\N	6	yes	no	no	Jalapeno*	289	2.5	active
308	\N	6	yes	no	no	Bacon*	289	2.5	active
309	\N	6	yes	no	no	Garlic*	289	2.5	active
310	\N	6	yes	no	no	Anchovies*	290	7	active
311	\N	6	yes	no	no	Chicken*	290	7	active
312	\N	6	yes	no	no	Shrimp*	290	7	active
313	\N	6	yes	no	no	Clams*	290	7	active
314	\N	39	yes	no	no	Pizza/Calzon Exceptions Dicicco’s Special	0	0	active
315	\N	39	yes	no	no	Additional Toppings	0	0	active
316	\N	39	yes	no	no	Premium Toppings	0	0	active
317	\N	39	yes	no	no	No Sausage	314	0	active
318	\N	39	yes	no	no	No Pepperoni	314	0	active
319	\N	39	yes	no	no	No Meatball	314	0	active
320	\N	39	yes	no	no	No Salami	314	0	active
321	\N	39	yes	no	no	No Mushroom	314	0	active
322	\N	39	yes	no	no	No Onion	314	0	active
323	\N	39	yes	no	no	No Bell Pepper	314	0	active
324	\N	39	yes	no	no	No Cheese	314	0	active
325	\N	39	yes	no	no	No Sauce	314	0	active
326	\N	39	yes	no	no	Sausage*	315	2.5	active
327	\N	39	yes	no	no	Pepperoni*	315	2.5	active
328	\N	39	yes	no	no	Mushroom*	315	2.5	active
329	\N	39	yes	no	no	Meatball*	315	2.5	active
330	\N	39	yes	no	no	Salami*	315	2.5	active
331	\N	39	yes	no	no	Artichoke Heart*	315	2.5	active
332	\N	39	yes	no	no	Onions*	315	2.5	active
333	\N	39	yes	no	no	Bell Peppers*	315	2.5	active
334	\N	39	yes	no	no	Canadian Bacon*	315	2.5	active
335	\N	39	yes	no	no	Pineapple*	315	2.5	active
336	\N	39	yes	no	no	Olives*	315	2.5	active
337	\N	39	yes	no	no	Tomato*	315	2.5	active
338	\N	39	yes	no	no	Jalapeno*	315	2.5	active
339	\N	39	yes	no	no	Bacon*	315	2.5	active
340	\N	39	yes	no	no	Garlic*	315	2.5	active
341	\N	39	yes	no	no	Anchovies*	316	7	active
342	\N	39	yes	no	no	Chicken*	316	7	active
343	\N	39	yes	no	no	Shrimp*	316	7	active
344	\N	39	yes	no	no	Clams*	316	7	active
345	\N	2	yes	no	no	Pizza/Calzon Exceptions Dicicco’s Special	0	0	active
346	\N	2	yes	no	no	Additional Toppings	0	0	active
347	\N	2	yes	no	no	Premium Toppings	0	0	active
348	\N	7	yes	no	no	Pizza/Calzon Exceptions Dicicco’s Special	0	0	active
349	\N	7	yes	no	no	Additional Toppings	0	0	active
350	\N	7	yes	no	no	Premium Toppings	0	0	active
351	\N	2	yes	no	no	No Sausage	345	0	active
352	\N	2	yes	no	no	No Pepperoni	345	0	active
353	\N	2	yes	no	no	No Meatball	345	0	active
354	\N	2	yes	no	no	No Salami	345	0	active
355	\N	2	yes	no	no	No Mushroom	345	0	active
356	\N	2	yes	no	no	No Onion	345	0	active
357	\N	2	yes	no	no	No Bell Pepper	345	0	active
358	\N	2	yes	no	no	No Cheese	345	0	active
359	\N	2	yes	no	no	No Sauce	345	0	active
360	\N	2	yes	no	no	Sausage*	346	2.5	active
361	\N	2	yes	no	no	Pepperoni*	346	2.5	active
362	\N	2	yes	no	no	Mushroom*	346	2.5	active
363	\N	2	yes	no	no	Meatball*	346	2.5	active
364	\N	2	yes	no	no	Salami*	346	2.5	active
365	\N	2	yes	no	no	Artichoke Heart*	346	2.5	active
366	\N	2	yes	no	no	Onions*	346	2.5	active
367	\N	2	yes	no	no	Bell Peppers*	346	2.5	active
368	\N	2	yes	no	no	Canadian Bacon*	346	2.5	active
369	\N	2	yes	no	no	Pineapple*	346	2.5	active
370	\N	2	yes	no	no	Olives*	346	2.5	active
371	\N	2	yes	no	no	Tomato*	346	2.5	active
372	\N	2	yes	no	no	Jalapeno*	346	2.5	active
373	\N	2	yes	no	no	Bacon*	346	2.5	active
374	\N	2	yes	no	no	Garlic*	346	2.5	active
375	\N	2	yes	no	no	Anchovies*	347	7	active
376	\N	2	yes	no	no	Chicken*	347	7	active
377	\N	2	yes	no	no	Shrimp*	347	7	active
378	\N	2	yes	no	no	Clams*	347	7	active
379	\N	7	yes	no	no	No Sausage	348	0	active
380	\N	7	yes	no	no	No Pepperoni	348	0	active
381	\N	7	yes	no	no	No Meatball	348	0	active
382	\N	7	yes	no	no	No Salami	348	0	active
383	\N	7	yes	no	no	No Mushroom	348	0	active
384	\N	7	yes	no	no	No Onion	348	0	active
385	\N	7	yes	no	no	No Bell Pepper	348	0	active
386	\N	7	yes	no	no	No Cheese	348	0	active
387	\N	7	yes	no	no	No Sauce	348	0	active
388	\N	7	yes	no	no	Sausage*	349	2.5	active
389	\N	7	yes	no	no	Pepperoni*	349	2.5	active
390	\N	7	yes	no	no	Mushroom*	349	2.5	active
391	\N	7	yes	no	no	Meatball*	349	2.5	active
392	\N	7	yes	no	no	Salami*	349	2.5	active
393	\N	7	yes	no	no	Artichoke Heart*	349	2.5	active
394	\N	7	yes	no	no	Onions*	349	2.5	active
395	\N	7	yes	no	no	Bell Peppers*	349	2.5	active
396	\N	7	yes	no	no	Canadian Bacon*	349	2.5	active
397	\N	7	yes	no	no	Pineapple*	349	2.5	active
398	\N	7	yes	no	no	Olives*	349	2.5	active
399	\N	7	yes	no	no	Tomato*	349	2.5	active
400	\N	7	yes	no	no	Jalapeno*	349	2.5	active
401	\N	7	yes	no	no	Bacon*	349	2.5	active
402	\N	7	yes	no	no	Garlic*	349	2.5	active
403	\N	7	yes	no	no	Anchovies*	350	7	active
404	\N	7	yes	no	no	Chicken*	350	7	active
405	\N	7	yes	no	no	Shrimp*	350	7	active
406	\N	7	yes	no	no	Clams*	350	7	active
407	\N	40	yes	no	no	Pizza/Calzon Exceptions Dicicco’s Special	0	0	active
408	\N	40	yes	no	no	Additional Toppings	0	0	active
409	\N	40	yes	no	no	Premium Toppings	0	0	active
410	\N	40	yes	no	no	No Mushroom	407	0	active
411	\N	40	yes	no	no	No Onion	407	0	active
412	\N	40	yes	no	no	No Bell Pepper	407	0	active
413	\N	40	yes	no	no	No Olive	407	0	active
414	\N	40	yes	no	no	No Tomato	407	0	active
415	\N	40	yes	no	no	No Cheese	407	0	active
416	\N	40	yes	no	no	No Sauce	407	0	active
417	\N	40	yes	no	no	Sausage*	408	2.5	active
418	\N	40	yes	no	no	Pepperoni*	408	2.5	active
419	\N	40	yes	no	no	Mushroom*	408	2.5	active
420	\N	40	yes	no	no	Meatball*	408	2.5	active
421	\N	40	yes	no	no	Salami*	408	2.5	active
422	\N	40	yes	no	no	Artichoke Heart*	408	2.5	active
423	\N	40	yes	no	no	Onions*	408	2.5	active
424	\N	40	yes	no	no	Bell Peppers*	408	2.5	active
425	\N	40	yes	no	no	Canadian Bacon*	408	2.5	active
426	\N	40	yes	no	no	Pineapple*	408	2.5	active
427	\N	40	yes	no	no	Olives*	408	2.5	active
428	\N	40	yes	no	no	Tomato*	408	2.5	active
429	\N	40	yes	no	no	Jalapeno*	408	2.5	active
430	\N	40	yes	no	no	Bacon*	408	2.5	active
431	\N	40	yes	no	no	Garlic*	408	2.5	active
432	\N	40	yes	no	no	Anchovies*	409	7	active
433	\N	40	yes	no	no	Chicken*	409	7	active
434	\N	40	yes	no	no	Shrimp*	409	7	active
435	\N	40	yes	no	no	Clams*	409	7	active
436	\N	8	yes	no	no	Pizza/Calzon Exceptions Vegetarian	0	0	active
437	\N	8	yes	no	no	Additional Toppings	0	0	active
438	\N	8	yes	no	no	Premium Toppings	0	0	active
439	\N	8	yes	no	no	No Mushroom	436	0	active
440	\N	8	yes	no	no	No Onion	436	0	active
441	\N	8	yes	no	no	No Bell Pepper	436	0	active
442	\N	8	yes	no	no	No Olive	436	0	active
443	\N	8	yes	no	no	No Tomato	436	0	active
444	\N	8	yes	no	no	No Cheese	436	0	active
445	\N	8	yes	no	no	No Sauce	436	0	active
446	\N	8	yes	no	no	Sausage*	437	2.5	active
447	\N	8	yes	no	no	Pepperoni*	437	2.5	active
448	\N	8	yes	no	no	Mushroom*	437	2.5	active
449	\N	8	yes	no	no	Meatball*	437	2.5	active
450	\N	8	yes	no	no	Salami*	437	2.5	active
451	\N	8	yes	no	no	Artichoke Heart*	437	2.5	active
452	\N	8	yes	no	no	Onions*	437	2.5	active
453	\N	8	yes	no	no	Bell Peppers*	437	2.5	active
454	\N	8	yes	no	no	Canadian Bacon*	437	2.5	active
455	\N	8	yes	no	no	Pineapple*	437	2.5	active
456	\N	8	yes	no	no	Olives*	437	2.5	active
457	\N	8	yes	no	no	Tomato*	437	2.5	active
458	\N	8	yes	no	no	Jalapeno*	437	2.5	active
459	\N	8	yes	no	no	Bacon*	437	2.5	active
460	\N	8	yes	no	no	Garlic*	437	2.5	active
461	\N	8	yes	no	no	Anchovies*	438	7	active
462	\N	8	yes	no	no	Chicken*	438	7	active
463	\N	8	yes	no	no	Shrimp*	438	7	active
464	\N	8	yes	no	no	Clams*	438	7	active
465	\N	41	yes	no	no	Pizza/Calzon Exceptions Vegetarian	0	0	active
466	\N	41	yes	no	no	Additional Toppings	0	0	active
467	\N	41	yes	no	no	Premium Toppings	0	0	active
468	\N	4	yes	no	no	Pizza/Calzon Exceptions Meat Lovers	0	0	active
469	\N	4	yes	no	no	Additional Toppings	0	0	active
470	\N	4	yes	no	no	Premium Toppings	0	0	active
471	\N	9	yes	no	no	Pizza/Calzon Exceptions Meat Lovers	0	0	active
472	\N	9	yes	no	no	Additional Toppings	0	0	active
473	\N	9	yes	no	no	Premium Toppings	0	0	active
474	\N	41	yes	no	no	No Sausage	465	0	active
475	\N	41	yes	no	no	No Pepperoni	465	0	active
476	\N	41	yes	no	no	No Meatball	465	0	active
477	\N	41	yes	no	no	No Salami	465	0	active
478	\N	41	yes	no	no	No Canadian Bacon	465	0	active
479	\N	41	yes	no	no	No Cheese	465	0	active
480	\N	41	yes	no	no	No Sauce	465	0	active
481	\N	41	yes	no	no	Sausage*	466	2.5	active
482	\N	41	yes	no	no	Pepperoni*	466	2.5	active
483	\N	41	yes	no	no	Mushroom*	466	2.5	active
484	\N	41	yes	no	no	Meatball*	466	2.5	active
485	\N	41	yes	no	no	Salami*	466	2.5	active
486	\N	41	yes	no	no	Artichoke Heart*	466	2.5	active
487	\N	41	yes	no	no	Onions*	466	2.5	active
488	\N	41	yes	no	no	Bell Peppers*	466	2.5	active
489	\N	41	yes	no	no	Canadian Bacon*	466	2.5	active
490	\N	41	yes	no	no	Pineapple*	466	2.5	active
491	\N	41	yes	no	no	Olives*	466	2.5	active
492	\N	41	yes	no	no	Tomato*	466	2.5	active
493	\N	41	yes	no	no	Jalapeno*	466	2.5	active
494	\N	41	yes	no	no	Bacon*	466	2.5	active
495	\N	41	yes	no	no	Garlic*	466	2.5	active
496	\N	41	yes	no	no	Anchovies*	467	7	active
497	\N	41	yes	no	no	Chicken*	467	7	active
498	\N	41	yes	no	no	Shrimp*	467	7	active
499	\N	41	yes	no	no	Clams*	467	7	active
500	\N	4	yes	no	no	No Sausage	468	0	active
501	\N	4	yes	no	no	No Pepperoni	468	0	active
502	\N	4	yes	no	no	No Meatball	468	0	active
503	\N	4	yes	no	no	No Salami	468	0	active
504	\N	4	yes	no	no	No Canadian Bacon	468	0	active
505	\N	4	yes	no	no	No Cheese	468	0	active
506	\N	4	yes	no	no	No Sauce	468	0	active
507	\N	4	yes	no	no	Sausage*	469	2.5	active
508	\N	4	yes	no	no	Pepperoni*	469	2.5	active
509	\N	4	yes	no	no	Mushroom*	469	2.5	active
510	\N	4	yes	no	no	Meatball*	469	2.5	active
511	\N	4	yes	no	no	Salami*	469	2.5	active
512	\N	4	yes	no	no	Artichoke Heart*	469	2.5	active
513	\N	4	yes	no	no	Onions*	469	2.5	active
514	\N	4	yes	no	no	Bell Peppers*	469	2.5	active
515	\N	4	yes	no	no	Canadian Bacon*	469	2.5	active
516	\N	4	yes	no	no	Pineapple*	469	2.5	active
517	\N	4	yes	no	no	Olives*	469	2.5	active
518	\N	4	yes	no	no	Tomato*	469	2.5	active
519	\N	4	yes	no	no	Jalapeno*	469	2.5	active
520	\N	4	yes	no	no	Bacon*	469	2.5	active
521	\N	4	yes	no	no	Garlic*	469	2.5	active
522	\N	4	yes	no	no	Anchovies*	470	7	active
523	\N	4	yes	no	no	Chicken*	470	7	active
524	\N	4	yes	no	no	Shrimp*	470	7	active
525	\N	4	yes	no	no	Clams*	470	7	active
526	\N	9	yes	no	no	No Sausage	471	0	active
527	\N	9	yes	no	no	No Pepperoni	471	0	active
528	\N	9	yes	no	no	No Meatball	471	0	active
529	\N	9	yes	no	no	No Salami	471	0	active
530	\N	9	yes	no	no	No Canadian Bacon	471	0	active
531	\N	9	yes	no	no	No Cheese	471	0	active
532	\N	9	yes	no	no	No Sauce	471	0	active
533	\N	9	yes	no	no	Sausage*	472	2.5	active
534	\N	9	yes	no	no	Pepperoni*	472	2.5	active
535	\N	9	yes	no	no	Mushroom*	472	2.5	active
536	\N	9	yes	no	no	Meatball*	472	2.5	active
537	\N	9	yes	no	no	Salami*	472	2.5	active
538	\N	9	yes	no	no	Artichoke Heart*	472	2.5	active
539	\N	9	yes	no	no	Onions*	472	2.5	active
540	\N	9	yes	no	no	Bell Peppers*	472	2.5	active
541	\N	9	yes	no	no	Canadian Bacon*	472	2.5	active
542	\N	9	yes	no	no	Pineapple*	472	2.5	active
543	\N	9	yes	no	no	Olives*	472	2.5	active
544	\N	9	yes	no	no	Tomato*	472	2.5	active
545	\N	9	yes	no	no	Jalapeno*	472	2.5	active
546	\N	9	yes	no	no	Bacon*	472	2.5	active
547	\N	9	yes	no	no	Garlic*	472	2.5	active
548	\N	9	yes	no	no	Anchovies*	473	7	active
549	\N	9	yes	no	no	Chicken*	473	7	active
550	\N	9	yes	no	no	Shrimp*	473	7	active
551	\N	9	yes	no	no	Clams*	473	7	active
552	Please choose 1	42	no	yes	yes	Included Pizza Top	0	0	active
553	\N	42	yes	no	no	Additional Toppings	0	0	active
554	\N	42	yes	no	no	Premium Toppings	0	0	active
555	Please choose 1	5	no	yes	yes	Included Pizza Top	0	0	active
556	\N	5	yes	no	no	Additional Toppings	0	0	active
557	\N	5	yes	no	no	Premium Toppings	0	0	active
558	Please choose 1	10	no	yes	yes	Included Pizza Top	0	0	active
559	\N	10	yes	no	no	Additional Toppings	0	0	active
560	\N	10	yes	no	no	Premium Toppings	0	0	active
564	\N	42	yes	no	no	Bell Peppers	552	0	active
565	\N	42	yes	no	no	Canadian Bacon	552	0	active
566	\N	42	yes	no	no	Cheese	552	0	active
567	\N	42	yes	no	no	Chicken	552	0	active
568	\N	42	yes	no	no	Clams	552	0	active
569	\N	42	yes	no	no	Garlic	552	0	active
570	\N	42	yes	no	no	Jalapeno	552	0	active
571	\N	42	yes	no	no	Meatball	552	0	active
572	\N	42	yes	no	no	Mushroom	552	0	active
573	\N	42	yes	no	no	Olives	552	0	active
574	\N	42	yes	no	no	Onions	552	0	active
575	\N	42	yes	no	no	Pepperoni	552	0	active
576	\N	42	yes	no	no	Pineapple	552	0	active
577	\N	42	yes	no	no	Salami	552	0	active
578	\N	42	yes	no	no	Sausage	552	0	active
579	\N	42	yes	no	no	Shrimp	552	0	active
580	\N	42	yes	no	no	Tomato	552	0	active
581	\N	42	yes	no	no	Sausage*	553	2.5	active
582	\N	42	yes	no	no	Pepperoni*	553	2.5	active
583	\N	42	yes	no	no	Mushroom*	553	2.5	active
584	\N	42	yes	no	no	Meatball*	553	2.5	active
585	\N	42	yes	no	no	Salami*	553	2.5	active
586	\N	42	yes	no	no	Artichoke Heart*	553	2.5	active
587	\N	42	yes	no	no	Onions*	553	2.5	active
588	\N	42	yes	no	no	Bell Peppers*	553	2.5	active
589	\N	42	yes	no	no	Canadian Bacon*	553	2.5	active
590	\N	42	yes	no	no	Pineapple*	553	2.5	active
591	\N	42	yes	no	no	Olives*	553	2.5	active
592	\N	42	yes	no	no	Tomato*	553	2.5	active
593	\N	42	yes	no	no	Jalapeno*	553	2.5	active
594	\N	42	yes	no	no	Bacon*	553	2.5	active
595	\N	42	yes	no	no	Garlic*	553	2.5	active
596	\N	42	yes	no	no	Anchovies*	554	7	active
597	\N	42	yes	no	no	Chicken*	554	7	active
598	\N	42	yes	no	no	Shrimp*	554	7	active
599	\N	42	yes	no	no	Clams*	554	7	active
561	\N	42	no	yes	yes	Anchovies	552	0	active
562	\N	42	yes	no	no	Artichoke Heart	552	0	active
563	\N	42	yes	no	no	Bacon	552	0	active
600	\N	5	no	yes	yes	Anchovies	555	0	active
601	\N	5	yes	no	no	Artichoke Heart	555	0	active
602	\N	5	yes	no	no	Bacon	555	0	active
603	\N	5	yes	no	no	Bell Peppers	555	0	active
604	\N	5	yes	no	no	Canadian Bacon	555	0	active
605	\N	5	yes	no	no	Cheese	555	0	active
606	\N	5	yes	no	no	Chicken	555	0	active
607	\N	5	yes	no	no	Clams	555	0	active
608	\N	5	yes	no	no	Garlic	555	0	active
609	\N	5	yes	no	no	Jalapeno	555	0	active
610	\N	5	yes	no	no	Meatball	555	0	active
611	\N	5	yes	no	no	Mushroom	555	0	active
612	\N	5	yes	no	no	Olives	555	0	active
613	\N	5	yes	no	no	Onions	555	0	active
614	\N	5	yes	no	no	Pepperoni	555	0	active
615	\N	5	yes	no	no	Pineapple	555	0	active
616	\N	5	yes	no	no	Salami	555	0	active
617	\N	5	yes	no	no	Sausage	555	0	active
618	\N	5	yes	no	no	Shrimp	555	0	active
619	\N	5	yes	no	no	Tomato	555	0	active
620	\N	5	yes	no	no	Sausage*	556	2.5	active
621	\N	5	yes	no	no	Pepperoni*	556	2.5	active
622	\N	5	yes	no	no	Mushroom*	556	2.5	active
623	\N	5	yes	no	no	Meatball*	556	2.5	active
624	\N	5	yes	no	no	Salami*	556	2.5	active
625	\N	5	yes	no	no	Artichoke Heart*	556	2.5	active
626	\N	5	yes	no	no	Onions*	556	2.5	active
627	\N	5	yes	no	no	Bell Peppers*	556	2.5	active
628	\N	5	yes	no	no	Canadian Bacon*	556	2.5	active
629	\N	5	yes	no	no	Pineapple*	556	2.5	active
630	\N	5	yes	no	no	Olives*	556	2.5	active
631	\N	5	yes	no	no	Tomato*	556	2.5	active
632	\N	5	yes	no	no	Jalapeno*	556	2.5	active
633	\N	5	yes	no	no	Bacon*	556	2.5	active
634	\N	5	yes	no	no	Garlic*	556	2.5	active
635	\N	5	yes	no	no	Anchovies*	557	7	active
636	\N	5	yes	no	no	Chicken*	557	7	active
637	\N	5	yes	no	no	Shrimp*	557	7	active
638	\N	5	yes	no	no	Clams*	557	7	active
639	\N	10	no	yes	yes	Anchovies	558	0	active
640	\N	10	yes	no	no	Artichoke Heart	558	0	active
641	\N	10	yes	no	no	Bacon	558	0	active
642	\N	10	yes	no	no	Bell Peppers	558	0	active
643	\N	10	yes	no	no	Canadian Bacon	558	0	active
644	\N	10	yes	no	no	Cheese	558	0	active
645	\N	10	yes	no	no	Chicken	558	0	active
646	\N	10	yes	no	no	Clams	558	0	active
647	\N	10	yes	no	no	Garlic	558	0	active
648	\N	10	yes	no	no	Jalapeno	558	0	active
649	\N	10	yes	no	no	Meatball	558	0	active
650	\N	10	yes	no	no	Mushroom	558	0	active
651	\N	10	yes	no	no	Olives	558	0	active
652	\N	10	yes	no	no	Onions	558	0	active
653	\N	10	yes	no	no	Pepperoni	558	0	active
654	\N	10	yes	no	no	Pineapple	558	0	active
655	\N	10	yes	no	no	Salami	558	0	active
656	\N	10	yes	no	no	Sausage	558	0	active
657	\N	10	yes	no	no	Shrimp	558	0	active
658	\N	10	yes	no	no	Tomato	558	0	active
659	\N	10	yes	no	no	Sausage*	559	2.5	active
660	\N	10	yes	no	no	Pepperoni*	559	2.5	active
661	\N	10	yes	no	no	Mushroom*	559	2.5	active
662	\N	10	yes	no	no	Meatball*	559	2.5	active
663	\N	10	yes	no	no	Salami*	559	2.5	active
664	\N	10	yes	no	no	Artichoke Heart*	559	2.5	active
665	\N	10	yes	no	no	Onions*	559	2.5	active
666	\N	10	yes	no	no	Bell Peppers*	559	2.5	active
667	\N	10	yes	no	no	Canadian Bacon*	559	2.5	active
668	\N	10	yes	no	no	Pineapple*	559	2.5	active
669	\N	10	yes	no	no	Olives*	559	2.5	active
670	\N	10	yes	no	no	Tomato*	559	2.5	active
671	\N	10	yes	no	no	Jalapeno*	559	2.5	active
672	\N	10	yes	no	no	Bacon*	559	2.5	active
673	\N	10	yes	no	no	Garlic*	559	2.5	active
674	\N	10	yes	no	no	Anchovies*	560	7	active
675	\N	10	yes	no	no	Chicken*	560	7	active
676	\N	10	yes	no	no	Shrimp*	560	7	active
677	\N	10	yes	no	no	Clams*	560	7	active
678	Please choose 1	25	no	yes	yes	dressing choice	179	0	active
679	\N	25	no	yes	yes	Blue Ranch	678	0	active
680	\N	25	no	yes	yes	Italian	678	0	active
681	\N	25	no	yes	yes	Caesar	678	0	active
682	\N	25	no	yes	yes	1000 island	678	0	active
683	\N	25	no	yes	yes	Oil n Vinegar	678	0	active
684	Please choose 1	26	no	yes	yes	dressing choice	183	0	active
685	Please choose 1	27	no	yes	yes	dressing choice	193	0	active
686	Please choose 1	28	no	yes	yes	dressing choice	202	0	active
687	Please choose 1	29	no	yes	yes	dressing choice	211	0	active
688	\N	26	no	yes	yes	Blue Ranch	684	0	active
689	\N	26	no	yes	yes	Italian	684	0	active
690	\N	26	no	yes	yes	Caesar	684	0	active
691	\N	26	no	yes	yes	1000 island	684	0	active
692	\N	26	no	yes	yes	Oil n Vinegar	684	0	active
693	\N	27	no	yes	yes	Blue Ranch	685	0	active
694	\N	27	no	yes	yes	Italian	685	0	active
695	\N	27	no	yes	yes	Caesar	685	0	active
696	\N	27	no	yes	yes	1000 island	685	0	active
697	\N	27	no	yes	yes	Oil n Vinegar	685	0	active
698	\N	28	no	yes	yes	Blue Ranch	686	0	active
699	\N	28	no	yes	yes	Italian	686	0	active
700	\N	28	no	yes	yes	Caesar	686	0	active
701	\N	28	no	yes	yes	1000 island	686	0	active
702	\N	28	no	yes	yes	Oil n Vinegar	686	0	active
703	\N	29	no	yes	yes	Blue Ranch	687	0	active
704	\N	29	no	yes	yes	Italian	687	0	active
705	\N	29	no	yes	yes	Caesar	687	0	active
706	\N	29	no	yes	yes	1000 island	687	0	active
707	\N	29	no	yes	yes	Oil n Vinegar	687	0	active
27	\N	2	yes	no	no	Pizza/Calzon Exceptions Dicicco’s Special	0	0	string
38	\N	2	yes	no	no	Additional Toppings	0	0	active
28	\N	2	yes	no	no	No Sausage	27	0	active
29	\N	2	yes	no	no	No Pepperoni	27	0	active
30	\N	2	yes	no	no	No Meatball	27	0	active
31	\N	2	yes	no	no	No Salami	27	0	active
32	\N	2	yes	no	no	No Mushroom	27	0	active
33	\N	2	yes	no	no	No Mushroom	27	0	active
34	\N	2	yes	no	no	No Onion	27	0	active
35	\N	2	yes	no	no	No Bell Pepper	27	0	active
36	\N	2	yes	no	no	No Cheese	27	0	active
37	\N	2	yes	no	no	No Sauce	27	0	active
39	\N	2	yes	no	yes	Sausage	38	2.5	active
40	\N	2	yes	no	yes	Pepperoni	38	2.5	active
41	\N	2	yes	no	yes	Mushroom	38	2.5	active
42	\N	2	yes	no	yes	Meatball	38	2.5	active
43	\N	2	yes	no	yes	Salami	38	2.5	active
44	\N	2	yes	no	yes	Artichoke Heart	38	2.5	active
45	\N	2	yes	no	yes	Onions	38	2.5	active
46	\N	2	yes	no	yes	Bell Peppers	38	2.5	active
47	\N	2	yes	no	yes	Canadian Bacon	38	2.5	active
48	\N	2	yes	no	yes	Pineapple	38	2.5	active
49	\N	2	yes	no	yes	Olives	38	2.5	active
50	\N	2	yes	no	yes	Tomato	38	2.5	active
51	\N	2	yes	no	yes	Jalapeno	38	2.5	active
52	\N	2	yes	no	yes	Bacon	38	2.5	active
53	\N	2	yes	no	yes	Garlic	38	2.5	active
54	\N	2	yes	no	no	Premium Toppings	0	0	active
\.


--
-- TOC entry 2892 (class 0 OID 24763)
-- Dependencies: 209
-- Data for Name: dish_names; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dish_names (dish_name_id, dish_name_description, dish_name, dish_type_id, dish_extra, dish_price, dish_status) FROM stdin;
5	Build your own Pizza with one topping.	Large Pizza*	1	yes	17.99	active
1	Pepperoni, salami, meatball, and cheese.	Large Original Pizza*	1	yes	20.99	active
2	Sausage, pepperoni, meatball, salami, mushroom, onion, bell pepper, and cheese.	Large Dicicco’s Special*	1	yes	21.99	active
3	Bell pepper, onion, mushroom, olive, tomato, artichoke hearts, and cheese.	Large Vegetarian*	1	yes	21.99	active
4	Pepperoni, sausage, meatball, salami, canadian bacon, and cheese.	Large Meat Lovers*	1	yes	21.99	active
6	Pepperoni, salami, meatball, and cheese.	Giant Original*	2	yes	23.99	active
7	Sausage, pepperoni, meatball, salami, mushroom, onion, bell pepper, and cheese.	Giant Dicicco’s Special*	2	yes	24	active
8	Bell pepper, onion, mushroom, olive, tomato, artichoke hearts, and cheese.	Giant Vegetarian*	2	yes	24.99	active
9	Pepperoni, sausage, meatball, salami, canadian bacon, and cheese.	Giant Meat Lovers*	2	yes	24.99	active
10	Build your own Pizza with one topping.	Giant Pizza*	2	yes	19.99	active
11	Breaded, seasoned and served with house marinara sauce.	Calamari Fritti*	3	no	11.99	active
12	Served with your choice of: house marinara or bleu-ranch.	Fried Ravioli*	3	yes	10.99	active
13	Fresh tomatoes, garlic and seasoning.	Bruschetta*	3	no	9.99	active
14	Served with house marinara sauce.	Garlic Cheesy Bread	3	no	9.99	active
15	Served with house bleu-ranch.	Fried Zucchini*	3	no	10.99	active
16	Full of fresh seasonal vegetables, kidney beans, garbanzo beans and pasta.	Minestrone Soup	4	yes	11.99	active
17	Iceberg lettuce, bacon, tomatoes, pickled onions, house bleu cheese ranch.	The Wedge*	4	yes	7.99	active
18	Fresh tomatoes, garlic and seasoning.	Bruschetta*	4	no	9.99	active
19	Fresh sliced mozzarella, tomatoes, basil, olive oil, balsamic reduction.	Caprese Salad*	4	yes	9.99	active
20	Iceberg lettuce, pepperoncini's, olives, tomatoes, dry salami, shredded cheddar, mozzarella cheese.	Antipasto Salad*	4	yes	13.99	active
21	Iceberg lettuce, salami, pepperoncini, shredded mozzarella, house-made croutons.	House Salad*	4	yes	7.99	active
22	Romaine lettuce, pecorino romano cheese, croutons, house caesar dressing.	Caesar Salad*	4	yes	7.99	active
23	Hand-rolled meatballs topped with house marinara sauce and mozzarella cheese.	Meatball Sandwich*	5	yes	12.99	active
24	Italian sausage link topped with house marinara sauce and mozzarella cheese.	Sausage Sandwich	5	yes	12.99	active
25	Italian seasoning.	Grilled Chicken Breast*	6	yes	18.99	active
26	Lightly breaded, white wine garlic lemon sauce, capers.	Chicken Picatta*	6	yes	21.99	active
27	Breaded, house marinara sauce, melted mozzarella cheese.	Chicken Parmesan*	6	yes	22.99	active
28	Lighty breaded, sautéed with mushrooms in a marsala wine sauce.	Chicken Marsala*	6	yes	22.99	active
29	Grilled to perfection and finished with sautéed mushrooms in a marsala wine sauce.	Top Sirloin Marsala*	6	yes	26.99	active
30	Mussels, scallops, shrimp and clams with house alfredo sauce.	Seafood Fettuccine*	7	yes	23.99	active
32	Mushrooms, beef, house marinara sauce, melted mozzarella.	Eggplant Parmesan*	8	yes	14.99	active
33	Alfredo sauce and sautéed artichoke hearts.	Spinach Ravioli*	8	yes	16.99	active
34	Beef stuffed pasta, house marinara sauce, melted mozzarella. Choice of (2) meatballs or (1) sausage link	Cannelloni*	8	yes	14.99	active
35	Ricotta stuffed pasta, house marinara sauce melted mozzarella. Choice of (2) meatballs or (1) sausage link	Manicotti*	8	yes	14.99	active
36	Fresh seasonal vegetables, house marinara sauce, melted mozzarella.	Veggie Eggplant Parmesan*	8	yes	13.99	active
37	Mushroom ravioli, sautéed chicken, marsala wine sauce.	Ravioli Marsala*	8	yes	17.99	active
31	Served with house alfredo sauce.	Linguini, Shrimp & Scallops*	7	no	22.99	active
38	Pepperoni, salami, meatball, and cheese.	Individual Original*	9	yes	13.99	active
39	Sausage, pepperoni, meatball, salami, mushroom, onion, bell pepper, and cheese.	Individual DiCicco's Special*	9	yes	14.99	active
40	Bell pepper, onion, mushroom, olive, tomato, artichoke hearts, and cheese.	Individual Vegetarian*	9	yes	14.99	active
41	Pepperoni, sausage, meatball, salami, canadian bacon, and cheese.	Individual Meat Lovers*	9	yes	14.99	active
42	Build your own Pizza with one topping.	Individual Pizza*	9	yes	11.99	active
\.


--
-- TOC entry 2898 (class 0 OID 24813)
-- Dependencies: 215
-- Data for Name: dish_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dish_types (dish_type_id, dish_type_description, dish_type_name, menu_type_id, dish_type_status) FROM stdin;
3	\N	Starters	1	active
4	\N	Soups & Salads	1	active
5	\N	Sandwiches	1	active
6	\N	Dinner Specials	1	active
7	\N	Seafood Favorites	1	active
8	\N	Stuffed Favorites	1	active
9	\N	Individual Pizza 10"	1	active
1	\N	Large Pizza 14"	1	active
2	\N	Giant Pizza 16"	1	active
\.


--
-- TOC entry 2900 (class 0 OID 24824)
-- Dependencies: 217
-- Data for Name: menu_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menu_types (menu_type_id, menu_type_description, menu_name, restaurent_id, menu_status) FROM stdin;
1	string	Delivery & Takeout Menu	1	active
\.


--
-- TOC entry 2894 (class 0 OID 24796)
-- Dependencies: 211
-- Data for Name: menus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menus (menu_id, menu_type_id) FROM stdin;
1	1
\.


--
-- TOC entry 2886 (class 0 OID 24631)
-- Dependencies: 203
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (order_id, checks, dining_option, modified_date, promised_date, restaurant_name, revenue_center, service_area, source) FROM stdin;
\.


--
-- TOC entry 2896 (class 0 OID 24804)
-- Dependencies: 213
-- Data for Name: restaurents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.restaurents (restaurent_id, address_line_1, address_line_2, city, country, restaurent_name, state, restaurent_status) FROM stdin;
1	Times Square	string	New York	United States of America	Al Pachino	New York	Open
\.


--
-- TOC entry 2888 (class 0 OID 24650)
-- Dependencies: 205
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, client_id, user_name, user_password, user_role) FROM stdin;
1	QWERTY	test	test	USER
\.


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 206
-- Name: dish_extras_dish_extra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dish_extras_dish_extra_id_seq', 707, true);


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 208
-- Name: dish_names_dish_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dish_names_dish_name_id_seq', 42, true);


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 214
-- Name: dish_types_dish_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dish_types_dish_type_id_seq', 9, true);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 216
-- Name: menu_types_menu_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_types_menu_type_id_seq', 1, true);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 210
-- Name: menus_menu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menus_menu_id_seq', 1, true);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 202
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 1, false);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 212
-- Name: restaurents_restaurent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.restaurents_restaurent_id_seq', 1, true);


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, true);


--
-- TOC entry 2748 (class 2606 OID 24760)
-- Name: dish_extras dish_extras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_extras
    ADD CONSTRAINT dish_extras_pkey PRIMARY KEY (dish_extra_id);


--
-- TOC entry 2750 (class 2606 OID 24771)
-- Name: dish_names dish_names_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_names
    ADD CONSTRAINT dish_names_pkey PRIMARY KEY (dish_name_id);


--
-- TOC entry 2756 (class 2606 OID 24821)
-- Name: dish_types dish_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_types
    ADD CONSTRAINT dish_types_pkey PRIMARY KEY (dish_type_id);


--
-- TOC entry 2758 (class 2606 OID 24832)
-- Name: menu_types menu_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_types
    ADD CONSTRAINT menu_types_pkey PRIMARY KEY (menu_type_id);


--
-- TOC entry 2752 (class 2606 OID 24801)
-- Name: menus menus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT menus_pkey PRIMARY KEY (menu_id);


--
-- TOC entry 2744 (class 2606 OID 24639)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2754 (class 2606 OID 24809)
-- Name: restaurents restaurents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurents
    ADD CONSTRAINT restaurents_pkey PRIMARY KEY (restaurent_id);


--
-- TOC entry 2746 (class 2606 OID 24658)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


-- Completed on 2020-09-16 11:46:04

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-09-16 11:46:04

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

--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2813 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


-- Completed on 2020-09-16 11:46:05

--
-- PostgreSQL database dump complete
--

-- Completed on 2020-09-16 11:46:05

--
-- PostgreSQL database cluster dump complete
--

