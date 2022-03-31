create schema cursos;

create table cursos.tblmodalidades(

id	int auto_increment not null,
nombre	varchar(255) unique,
descripcion text,

constraint pk_tblmodalidades primary key (id)

);

create table cursos.tblcursos(

id		int auto_increment not null,
nombre	varchar(255),
costo	float,
description	text,
horas	int(255),
dirigido_a varchar(255),
modalidad varchar(255),

constraint pk_tblcursos primary key (id)

);

create table cursos.tbldescuentos(

id int auto_increment not null,
nombres varchar(255),
modalidad varchar(255),
pais varchar(255),
descuento varchar(255),
curso varchar(255),
fecha_final date,

constraint pk_tbldescuentos primary key (id)

);