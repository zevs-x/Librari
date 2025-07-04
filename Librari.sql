PGDMP      ;                }            Library    17.3    17.3 ,    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    57493    Library    DATABASE     o   CREATE DATABASE "Library" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'ru-RU';
    DROP DATABASE "Library";
                     postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    57711    Book_released_id_N    SEQUENCE     |   CREATE SEQUENCE public."Book_released_id_N"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."Book_released_id_N";
       public               postgres    false    4            �            1259    57581    Book_released    TABLE     _  CREATE TABLE public."Book_released" (
    "Book_released_id" bigint DEFAULT nextval('public."Book_released_id_N"'::regclass) NOT NULL,
    copy_id bigint NOT NULL,
    reader_id bigint NOT NULL,
    loan_date date DEFAULT CURRENT_DATE NOT NULL,
    due_date text,
    return_date date,
    CONSTRAINT loans_check CHECK ((return_date >= loan_date))
);
 #   DROP TABLE public."Book_released";
       public         heap r       postgres    false    228    4            �            1259    57628    author_id_N    SEQUENCE     u   CREATE SEQUENCE public."author_id_N"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."author_id_N";
       public               postgres    false    4            �            1259    57499    authors    TABLE     �   CREATE TABLE public.authors (
    author_id bigint DEFAULT nextval('public."author_id_N"'::regclass) NOT NULL,
    first_name character varying NOT NULL,
    middle_name character varying NOT NULL,
    last_name character varying
);
    DROP TABLE public.authors;
       public         heap r       postgres    false    224    4            �            1259    57521    book_authors    TABLE     a   CREATE TABLE public.book_authors (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL
);
     DROP TABLE public.book_authors;
       public         heap r       postgres    false    4            �            1259    57705 	   book_id_N    SEQUENCE     s   CREATE SEQUENCE public."book_id_N"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public."book_id_N";
       public               postgres    false    4            �            1259    57494    books    TABLE     )  CREATE TABLE public.books (
    book_id bigint DEFAULT nextval('public."book_id_N"'::regclass) NOT NULL,
    title character varying(255) NOT NULL,
    isbn character varying(13),
    publication_date date NOT NULL,
    author_id bigint NOT NULL,
    genre_name character varying(255) NOT NULL
);
    DROP TABLE public.books;
       public         heap r       postgres    false    225    4            �            1259    57707    copy_book_id_N    SEQUENCE     x   CREATE SEQUENCE public."copy_book_id_N"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public."copy_book_id_N";
       public               postgres    false    4            �            1259    57534    copies    TABLE     �  CREATE TABLE public.copies (
    copy_book_id bigint DEFAULT nextval('public."copy_book_id_N"'::regclass) NOT NULL,
    book_id bigint NOT NULL,
    status character varying(10) DEFAULT 'доступна'::character varying NOT NULL,
    acquisition_date date NOT NULL,
    CONSTRAINT copies_status_check CHECK (((status)::text = ANY ((ARRAY['доступна'::character varying, 'выдана'::character varying, 'в ремонте'::character varying])::text[])))
);
    DROP TABLE public.copies;
       public         heap r       postgres    false    226    4            �            1259    57709    reader_id_N    SEQUENCE     u   CREATE SEQUENCE public."reader_id_N"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."reader_id_N";
       public               postgres    false    4            �            1259    57546    readers    TABLE       CREATE TABLE public.readers (
    reader_id bigint DEFAULT nextval('public."reader_id_N"'::regclass) NOT NULL,
    first_name text NOT NULL,
    middle_name text NOT NULL,
    last_name text,
    phone text NOT NULL,
    registration_date date DEFAULT CURRENT_DATE NOT NULL
);
    DROP TABLE public.readers;
       public         heap r       postgres    false    227    4            �            1259    57713 
   staff_id_N    SEQUENCE     t   CREATE SEQUENCE public."staff_id_N"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public."staff_id_N";
       public               postgres    false    4            �            1259    57588    staff    TABLE     �   CREATE TABLE public.staff (
    staff_id bigint DEFAULT nextval('public."staff_id_N"'::regclass) NOT NULL,
    first_name text NOT NULL,
    middle_name text NOT NULL,
    last_name text,
    date_of_birth date NOT NULL,
    role text NOT NULL
);
    DROP TABLE public.staff;
       public         heap r       postgres    false    229    4            �          0    57581    Book_released 
   TABLE DATA           s   COPY public."Book_released" ("Book_released_id", copy_id, reader_id, loan_date, due_date, return_date) FROM stdin;
    public               postgres    false    222   �4       �          0    57499    authors 
   TABLE DATA           P   COPY public.authors (author_id, first_name, middle_name, last_name) FROM stdin;
    public               postgres    false    218   5       �          0    57521    book_authors 
   TABLE DATA           :   COPY public.book_authors (book_id, author_id) FROM stdin;
    public               postgres    false    219   �5       �          0    57494    books 
   TABLE DATA           ^   COPY public.books (book_id, title, isbn, publication_date, author_id, genre_name) FROM stdin;
    public               postgres    false    217   �5       �          0    57534    copies 
   TABLE DATA           Q   COPY public.copies (copy_book_id, book_id, status, acquisition_date) FROM stdin;
    public               postgres    false    220   7       �          0    57546    readers 
   TABLE DATA           j   COPY public.readers (reader_id, first_name, middle_name, last_name, phone, registration_date) FROM stdin;
    public               postgres    false    221   [7       �          0    57588    staff 
   TABLE DATA           b   COPY public.staff (staff_id, first_name, middle_name, last_name, date_of_birth, role) FROM stdin;
    public               postgres    false    223   ;8                   0    0    Book_released_id_N    SEQUENCE SET     C   SELECT pg_catalog.setval('public."Book_released_id_N"', 2, false);
          public               postgres    false    228                       0    0    author_id_N    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."author_id_N"', 7, true);
          public               postgres    false    224                       0    0 	   book_id_N    SEQUENCE SET     9   SELECT pg_catalog.setval('public."book_id_N"', 6, true);
          public               postgres    false    225                       0    0    copy_book_id_N    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."copy_book_id_N"', 2, false);
          public               postgres    false    226                       0    0    reader_id_N    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."reader_id_N"', 5, true);
          public               postgres    false    227                       0    0 
   staff_id_N    SEQUENCE SET     :   SELECT pg_catalog.setval('public."staff_id_N"', 6, true);
          public               postgres    false    229            L           2606    57679    authors authors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (author_id);
 >   ALTER TABLE ONLY public.authors DROP CONSTRAINT authors_pkey;
       public                 postgres    false    218            J           2606    57505    books books_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public                 postgres    false    217            N           2606    57540    copies copies_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.copies
    ADD CONSTRAINT copies_pkey PRIMARY KEY (copy_book_id);
 <   ALTER TABLE ONLY public.copies DROP CONSTRAINT copies_pkey;
       public                 postgres    false    220            R           2606    57586    Book_released loans_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Book_released"
    ADD CONSTRAINT loans_pkey PRIMARY KEY ("Book_released_id");
 D   ALTER TABLE ONLY public."Book_released" DROP CONSTRAINT loans_pkey;
       public                 postgres    false    222            P           2606    57553    readers readers_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.readers
    ADD CONSTRAINT readers_pkey PRIMARY KEY (reader_id);
 >   ALTER TABLE ONLY public.readers DROP CONSTRAINT readers_pkey;
       public                 postgres    false    221            T           2606    57594    staff staff_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_pkey PRIMARY KEY (staff_id);
 :   ALTER TABLE ONLY public.staff DROP CONSTRAINT staff_pkey;
       public                 postgres    false    223            Y           2606    82436 (   Book_released Book_released_copy_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."Book_released"
    ADD CONSTRAINT "Book_released_copy_id_fkey" FOREIGN KEY (copy_id) REFERENCES public.copies(copy_book_id) NOT VALID;
 V   ALTER TABLE ONLY public."Book_released" DROP CONSTRAINT "Book_released_copy_id_fkey";
       public               postgres    false    222    220    4686            Z           2606    82431 *   Book_released Book_released_reader_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."Book_released"
    ADD CONSTRAINT "Book_released_reader_id_fkey" FOREIGN KEY (reader_id) REFERENCES public.readers(reader_id) NOT VALID;
 X   ALTER TABLE ONLY public."Book_released" DROP CONSTRAINT "Book_released_reader_id_fkey";
       public               postgres    false    221    4688    222            V           2606    57680 (   book_authors book_authors_author_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT book_authors_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.authors(author_id);
 R   ALTER TABLE ONLY public.book_authors DROP CONSTRAINT book_authors_author_id_fkey;
       public               postgres    false    219    218    4684            W           2606    57524 &   book_authors book_authors_book_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT book_authors_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 P   ALTER TABLE ONLY public.book_authors DROP CONSTRAINT book_authors_book_id_fkey;
       public               postgres    false    4682    217    219            U           2606    57731    books books_author_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.authors(author_id) NOT VALID;
 D   ALTER TABLE ONLY public.books DROP CONSTRAINT books_author_id_fkey;
       public               postgres    false    218    4684    217            X           2606    57541    copies copies_book_id_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.copies
    ADD CONSTRAINT copies_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 D   ALTER TABLE ONLY public.copies DROP CONSTRAINT copies_book_id_fkey;
       public               postgres    false    220    4682    217            �   +   x�3�4B#CC]0��".#N4424�#Nc�h� ��9      �   �   x�U���@D��b�@|������8���"H�&Q��qG�@Bڃ�3��]�E��D	�Z�F��&HQ#�XpE�1�\�x����gMt�F�xQ)8Fc7���t%8�Sڇ�B�EJ�7�G"4�`K���AF�Gi����	�D��s=���l)����-�:ι7��)      �      x�3�4�2�4����� ��      �     x�u�MN�0���)r� ���܅�P*��V�(GQCӖ�Wx�o�*u�'���7O^q*w��*���T��ʈmY��o���1a4]�s�>x�9��+����3�0`.��ׄ���s�ޔ�����}��h�$�.Y�46�h�\��ި?1�l��Wmr1H']��Q+Բ���V��'�h`��+�u�lhCL��Sc�r���"c��K��F�Y�Z�~�rYR��?p���%-+]nm��L9�F�����8�f_��:\�8�]��h��ۛ��~,Ҥ      �   -   x�3�4估�¾���.6_�a��������F@����� TR�      �   �   x�uP;�0���%/͇�p��P��J+A=�s#^[Y�??�/Z��>�Ѡ�j�=?��F�T�#nb�t�{�
�tj��)���3M��YՏ>�F����A�n����AkҎ�C.p�fJ�צ���x�ݯ��ސ�&�ԏ����:|a�%�82��M����u5��sVڧ�c�k3)�M�V����O�Q�����k�$      �     x�uP�J1���K$����Ň�ki��(�D��[�xx�>|��7r6rE	wawg&31�G����`+��Ѡ�ay#W�F#��D(j� �ep>�^����3u�$�\�c�g6u��C��5'51���\�c����)Y.D�Od)U����`�w��xa<�a�j�*��V�P��Df���SQz��|e�b�����A,�R����1�mO�g�G#����s��O�v�a'�"��I=�>�������������ş�T?��{vd��A��     