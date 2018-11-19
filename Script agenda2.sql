
/*Creacion de la base de datos*/
drop database agenda;
create database agenda;

use agenda;

/*Tablas simples*/

create table tipos_usuarios(
id_tipo_usuario int auto_increment not null,
tipo varchar(50) not null,
primary key(id_tipo_usuario)
);

create table roles(
id_rol int auto_increment not null,
rol varchar(50) not null,
primary key (id_rol)
);

create table tipos_actividades(
id_tipo_actividad int auto_increment not null,
tipo_actividad varchar(150) not null,
primary key (id_tipo_actividad)
);

create table tipos_fases(
id_tipo_fase int auto_increment not null,
tipo_fase varchar(150) not null,
primary key(id_tipo_fase)
);

create table notificaciones(
id_clasificacion int auto_increment not null,
rango varchar(50) not null,
primary key (id_clasificacion)
);

create table cronogramas(
id_cronograma int auto_increment not null,
nombre varchar(50) not null,
descripcion varchar(150) not null,
primary key (id_cronograma)
);

/*Tablas con llaves foráneas*/
create table usuarios(
id_usuario int auto_increment not null,
id_tipo_usuario int not null,
nombres varchar(100) not null,
apellidos varchar(100) not null,
genero varchar(25) not null,
telefono varchar(25) not null,
correo varchar(50) not null,
contra varchar(16) not null,
nivel_de_mando int(1) not null,
primary key(id_usuario),
foreign key (id_tipo_usuario) references tipos_usuarios(id_tipo_usuario)
);

create table calendarios(
id_calendario int auto_increment not null,
id_cronograma int not null,
año int not null,
primary key (id_calendario),
foreign key (id_cronograma) references cronogramas (id_cronograma)
);

create table meses(
id_mes int auto_increment not null,
id_calendario int not null,
mes int(2) not null,
primary key (id_mes),
foreign key (id_calendario) references calendarios(id_calendario)
);

create table dias(
id_dia int auto_increment not null,
id_mes int not null,
nombre_dia varchar (15) not null,
primary key (id_dia),
foreign key (id_mes) references meses(id_mes)
);

create table actividades(
id_actividad int auto_increment not null,
id_cronograma int not null,
id_tipo_actividad int not null,
nombrea_actividad varchar(150) not null,
primary key (id_actividad),
foreign key (id_cronograma) references cronogramas (id_cronograma),
foreign key (id_tipo_actividad) references tipos_actividades(id_tipo_actividad)
);

create table fases(
id_fase int auto_increment not null,
id_tipo_fase int not null,
estado varchar(50) not null,
fecha date not null,
primary key(id_fase),
foreign key(id_tipo_fase) references tipos_fases(id_tipo_fase)
);