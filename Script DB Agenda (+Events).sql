
/*Creacion de la base de datos*/
DROP DATABASE agenda IF EXISTS;
CREATE DATABASE agenda;

USE agenda;

/*Tablas simples*/

CREATE TABLE tipos_usuarios(
id_tipo_usuario INTEGER AUTO_INCREMENT NOT NULL,
tipo VARCHAR(50) NOT NULL,
PRIMARY KEY(id_tipo_usuario)
);

CREATE TABLE roles(
id_rol INTEGER AUTO_INCREMENT NOT NULL,
rol VARCHAR(50) NOT NULL,
PRIMARY KEY (id_rol)
);

CREATE TABLE tipos_actividades(
id_tipo_actividad INTEGER AUTO_INCREMENT NOT NULL,
tipo_actividad VARCHAR(150) NOT NULL,
PRIMARY KEY (id_tipo_actividad)
);

CREATE TABLE tipos_fases(
id_tipo_fase INTEGER AUTO_INCREMENT NOT NULL,
tipo_fase VARCHAR(150) NOT NULL,
PRIMARY KEY(id_tipo_fase)
);

CREATE TABLE notificaciones(
id_clasificacion INTEGER AUTO_INCREMENT NOT NULL,
rango VARCHAR(50) NOT NULL,
PRIMARY KEY (id_clasificacion)
);

CREATE TABLE cronogramas(
id_cronograma INTEGER AUTO_INCREMENT NOT NULL,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(150) NOT NULL,
PRIMARY KEY (id_cronograma)
);

/*Tablas con llaves foráneas*/
CREATE TABLE usuarios(
id_usuario INTEGER AUTO_INCREMENT NOT NULL,
id_tipo_usuario INTEGER NOT NULL,
nombres VARCHAR(100) NOT NULL,
apellidos VARCHAR(100) NOT NULL,
genero VARCHAR(25) NOT NULL,
telefono VARCHAR(25) NOT NULL,
correo VARCHAR(50) NOT NULL,
contra VARCHAR(16) NOT NULL,
nivel_de_mando INTEGER(1) NOT NULL,
PRIMARY KEY(id_usuario),
FOREIGN KEY (id_tipo_usuario) REFERENCES tipos_usuarios(id_tipo_usuario)
);

CREATE TABLE calendarios(
id_calendario INTEGER AUTO_INCREMENT NOT NULL,
id_cronograma INTEGER NOT NULL,
año INTEGER NOT NULL,
PRIMARY KEY (id_calendario),
FOREIGN KEY (id_cronograma) REFERENCES cronogramas (id_cronograma)
);

CREATE TABLE meses(
id_mes INTEGER AUTO_INCREMENT NOT NULL,
id_calendario INTEGER NOT NULL,
mes INTEGER(2) NOT NULL,
PRIMARY KEY (id_mes),
FOREIGN KEY (id_calendario) REFERENCES calendarios(id_calendario)
);

CREATE TABLE dias(
id_dia INTEGER AUTO_INCREMENT NOT NULL,
id_mes INTEGER NOT NULL,
nombre_dia VARCHAR (15) NOT NULL,
PRIMARY KEY (id_dia),
FOREIGN KEY (id_mes) REFERENCES meses(id_mes)
);

create TABLE actividades(
id_actividad INTEGER AUTO_INCREMENT NOT NULL,
id_cronograma INTEGER NOT NULL,
id_tipo_actividad INTEGER NOT NULL,
nombrea_actividad VARCHAR(150) NOT NULL,
PRIMARY KEY (id_actividad),
FOREIGN KEY (id_cronograma) REFERENCES cronogramas (id_cronograma),
FOREIGN KEY (id_tipo_actividad) REFERENCES tipos_actividades(id_tipo_actividad)
);

CREATE TABLE fases(
id_fase INTEGER AUTO_INCREMENT NOT NULL,
id_tipo_fase INTEGER NOT NULL,
estado VARCHAR(50) NOT NULL,
fecha DATE NOT NULL,
PRIMARY KEY(id_fase),
FOREIGN KEY(id_tipo_fase) REFERENCES tipos_fases(id_tipo_fase)
);

SET GLOBAL event_scheduler = ON;

SET lc_time_names = 'es_ES';
SELECT CONCAT(UPPER(LEFT(DAYNAME(CURDATE()),1)), SUBSTR(DAYNAME(CURDATE()), 2)) AS nombre_dia;

DROP EVENT IF EXISTS nombre_mes;
CREATE EVENT nombre_mes
ON SCHEDULE EVERY 1 MONTH
STARTS '2018-01-01 00:00:00'
DO


DROP EVENT IF EXISTS cambio_nombre_dia;
CREATE EVENT cambio_nombre_dia
ON SCHEDULE EVERY 1 DAY
STARTS '2018-01-01 00:00:00'
DO
BEGIN
	
END

SELECT 
    fecha, DAYOFWEEK(fecha)
FROM
    calendario
WHERE
    fecha BETWEEN '2017-02-01' AND '2017-02-15'
        AND DAYOFWEEK(fecha) IN (2,3,4,5,6) /*--agregar--*/ and DAYOFWEEK(fecha) not in (feriado/*--sub-consulta--*/)