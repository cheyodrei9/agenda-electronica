
/*Creacion de la base de datos*/
DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;

USE agenda;

/*tablas simples*/
CREATE TABLE tiposactividades(
idtipoactividad int(11) NOT NULL AUTO_INCREMENT,
tipoactividad varchar(150) NOT NULL,
PRIMARY KEY (idtipoactividad)
);

CREATE TABLE tiposfases(
idtipofase INTEGER(11) NOT NULL AUTO_INCREMENT,
tipofase VARCHAR(150) NOT NULL,
PRIMARY KEY (idtipofase)
);

CREATE TABLE tiposusuarios(
idtipousuario INTEGER(11) NOT NULL AUTO_INCREMENT,
tipo VARCHAR(50) NOT NULL,
PRIMARY KEY (idtipousuario)
);


/*tablas con foranea*/

CREATE TABLE usuarios(
idusuario INTEGER(11) NOT NULL AUTO_INCREMENT,
idtipousuario INTEGER(11) NOT NULL,
nombres VARCHAR(100) NOT NULL,
apellidos VARCHAR(100) NOT NULL,
genero VARCHAR(25) NOT NULL,
telefono VARCHAR(25) NOT NULL,
correo VARCHAR(50) NOT NULL,
contra VARCHAR(16) NOT NULL,
niveldemando INTEGER(1) NOT NULL,
PRIMARY KEY (idusuario),
FOREIGN KEY (idtipousuario) REFERENCES tiposusuarios (idtipousuario)
);

CREATE TABLE cronogramas(
idcronograma INTEGER(11) NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(150) NOT NULL,
idusuario INTEGER(11) NOT NULL,
PRIMARY KEY (idcronograma),
FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario)
);

CREATE TABLE calendarios(
idcalendario INTEGER(11) NOT NULL AUTO_INCREMENT,
idcronograma INTEGER(11) NOT NULL,
año INTEGER(11) NOT NULL,
PRIMARY KEY (idcalendario),
FOREIGN KEY (idcronograma) REFERENCES cronogramas (idcronograma)
);

CREATE TABLE meses(
idmes INTEGER(11) NOT NULL AUTO_INCREMENT,
idcalendario INTEGER(11) NOT NULL,
mes VARCHAR(45) NOT NULL,
PRIMARY KEY (idmes),
FOREIGN KEY (idcalendario) REFERENCES calendarios (idcalendario)
);

CREATE TABLE dias(
iddia INTEGER(11) NOT NULL AUTO_INCREMENT,
idmes INTEGER(11) NOT NULL,
nombredia VARCHAR(15) NOT NULL,
PRIMARY KEY (iddia),
FOREIGN KEY (idmes) REFERENCES meses (idmes)
);


CREATE TABLE fases(
idfase INTEGER(11) NOT NULL AUTO_INCREMENT,
idtipofase INTEGER(11) NOT NULL,
estado VARCHAR(50) NOT NULL,
fecha DATE NOT NULL,
PRIMARY KEY (idfase),
FOREIGN KEY (idtipofase) REFERENCES tiposfases (idtipofase)
);

CREATE TABLE actividades(
idactividad INTEGER(11) NOT NULL AUTO_INCREMENT,
idcronograma INTEGER(11) NOT NULL,
idtipoactividad INTEGER(11) NOT NULL,
nombreactividad VARCHAR(150) NOT NULL,
fechaactividad DATE NOT NULL,
idfase INTEGER(11) NOT NULL,
PRIMARY KEY (idactividad),
FOREIGN KEY (idcronograma) REFERENCES cronogramas (idcronograma),
FOREIGN KEY (idtipoactividad) REFERENCES tiposactividades (idtipoactividad),
FOREIGN KEY (idfase) REFERENCES fases (idfase)
);

CREATE TABLE roles(
idrol INTEGER(11) NOT NULL AUTO_INCREMENT,
rol VARCHAR(50) NOT NULL,
idtipousuario INTEGER(11) NOT NULL,
PRIMARY KEY (idrol),
FOREIGN KEY (idtipousuario) REFERENCES tiposusuarios (idtipousuario)
);


CREATE TABLE notificaciones(
idnotificacion INTEGER(11) NOT NULL AUTO_INCREMENT,
idusuario INTEGER(11) NOT NULL,
idactividad INTEGER() NOT NULL,
rango VARCHAR(50) NOT NULL,
PRIMARY KEY (idnotificacion),
FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
FOREIGN KEY (idactividad) REFERENCES actividades(idactividad)
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