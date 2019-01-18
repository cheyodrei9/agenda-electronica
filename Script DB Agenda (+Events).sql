DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;

USE agenda;

/*tablas simples*/
CREATE TABLE tiposactividades(
idtipoactividad INT(11) NOT NULL AUTO_INCREMENT,
tipoactividad VARCHAR(150) NOT NULL,
PRIMARY KEY (idtipoactividad)
);

create table tiposfases(
idtipofase INT(11) NOT NULL AUTO_INCREMENT,
tipofase VARCHAR(150) NOT NULL,
PRIMARY KEY (idtipofase)
);

CREATE TABLE tiposusuarios(
idtipousuario INT(11) NOT NULL AUTO_INCREMENT,
tipo VARCHAR(50) NOT NULL,
PRIMARY KEY (idtipousuario)
);

/*tablas con foranea*/

CREATE TABLE usuarios(
idusuario INT(11) NOT NULL AUTO_INCREMENT,
idtipousuario INT(11) NOT NULL,
nombres VARCHAR(100) NOT NULL,
apellidos VARCHAR(100) NOT NULL,
genero VARCHAR(25) NOT NULL,
telefono VARCHAR(25) NOT NULL,
correo VARCHAR(50) NOT NULL,
contra VARCHAR(16) NOT NULL,
niveldemando INT(1) NOT NULL,
PRIMARY KEY (idusuario),
FOREIGN KEY (idtipousuario) REFERENCES tiposusuarios (idtipousuario)
);

CREATE TABLE cronogramas(
idcronograma INT(11) NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(150) NOT NULL,
idusuario INT(11) NOT NULL,
PRIMARY KEY (idcronograma),
FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario)
);

CREATE TABLE calendarios(
idcalendario INT(11) NOT NULL AUTO_INCREMENT,
idcronograma INT(11) NOT NULL,
año INT(11) NOT NULL,
PRIMARY KEY (idcalendario),
FOREIGN KEY (idcronograma) REFERENCES cronogramas (idcronograma)
);

CREATE TABLE meses(
idmes INT(11) NOT NULL AUTO_INCREMENT,
idcalendario INT(11) NOT NULL,
mes VARCHAR(45) NOT NULL,
PRIMARY KEY (idmes),
FOREIGN KEY (idcalendario) REFERENCES calendarios (idcalendario)
);

CREATE TABLE dias(
iddia INT(11) NOT NULL AUTO_INCREMENT,
idmes INT(11) NOT NULL,
nombredia VARCHAR(15) NOT NULL,
PRIMARY KEY (iddia),
FOREIGN KEY (idmes) REFERENCES meses (idmes)
);

CREATE TABLE fases(
idfase INT(11) NOT NULL AUTO_INCREMENT,
idtipofase INT(11) NOT NULL,
estado VARCHAR(50) NOT NULL,
fecha DATE NOT NULL,
PRIMARY KEY (idfase),
FOREIGN KEY (idtipofase) REFERENCES tiposfases (idtipofase)
);

CREATE TABLE actividades(
idactividad INT(11) NOT NULL AUTO_INCREMENT,
idcronograma INT(11) NOT NULL,
idtipoactividad INT(11) NOT NULL,
idusuario INT(11) NOT NULL,
nombreactividad VARCHAR(150) NOT NULL,
fechaactividad DATE NOT NULL,
idfase INT(11) NOT NULL,
PRIMARY KEY (idactividad),
FOREIGN KEY (idcronograma) REFERENCES cronogramas (idcronograma),
FOREIGN KEY (idtipoactividad) REFERENCES tiposactividades (idtipoactividad),
FOREIGN KEY (idfase) REFERENCES fases (idfase),
FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario)
);

CREATE TABLE notificaciones(
idnotificacion INT(11) NOT NULL AUTO_INCREMENT,
idusuario INT(11) NOT NULL,
idactividad INT(11) NOT NULL,
fechaasignada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
diasrestantes INT(3) NOT NULL,
color VARCHAR(10) NOT NULL DEFAULT '#FFFFFF',
estado_notificacion VARCHAR (10) NOT NULL DEFAULT 'no visto',
PRIMARY KEY (idnotificacion),
FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
FOREIGN KEY (idactividad) REFERENCES actividades(idactividad)
);

CREATE TABLE roles(
idrol INT(11) NOT NULL AUTO_INCREMENT,
rol VARCHAR(50) NOT NULL,
idtipousuario INT(11) NOT NULL,
PRIMARY KEY (idrol),
FOREIGN KEY (idtipousuario) REFERENCES tiposusuarios (idtipousuario)
);

DELIMITER \\
DROP TRIGGER IF EXISTS notificacion_inicial \\
CREATE TRIGGER notificacion_inicial AFTER INSERT ON actividades FOR EACH ROW
BEGIN
	INSERT INTO notificaciones (idusuario, idactividad, diasrestantes) VALUES (NEW.idusuario, NEW.idactividad, DATEDIFF(NEW.fechaactividad, CURDATE()));
END \\
DELIMITER ;

DELIMITER \\
DROP PROCEDURE IF EXISTS alerta_notificacion \\
CREATE PROCEDURE alerta_notificacion()
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE id_notificacion INT;
    DECLARE id_actividad INT;
    DECLARE days_left INT;
    DECLARE update_notification CURSOR FOR SELECT idnotificacion, idactividad FROM notificaciones WHERE visto != 'finalizada';
    DECLARE CONTINUE handler FOR NOT FOUND SET done = TRUE;
    OPEN update_notification;
    update_notification_loop: LOOP
		FETCH update_notification INTO id_notificacion, id_actividad;
        IF done THEN
			leave update_notification_loop;
		END IF;
        SET days_left = DATEDIFF((SELECT fechaactividad FROM actividades WHERE idactividad = id_actividad), CURDATE());
        IF days_left <= 0 THEN
			UPDATE notificaciones SET diasrestantes = 0, color = '#808080', estado_notificacion = 'finalizada' WHERE idnotificacion = id_notificacion;
        ElSEIF days_left <= 3 THEN
			UPDATE notificaciones SET diasrestantes = days_left, color = '#FF0000', estado_notificacion = 'no visto' WHERE idnotificacion = id_notificacion;
		ELSEIF days_left <= 6 THEN
			UPDATE notificaciones SET diasrestantes = days_left, color = '#FFFF00', estado_notificacion = 'no visto' WHERE idnotificacion = id_notificacion;
		ELSEIF days_left <= 10 THEN
			UPDATE notificaciones SET diasrestantes = days_left, color = '#00FF00', estado_notificacion = 'no visto' WHERE idnotificacion = id_notificacion;
		END IF;
	END LOOP;
END \\
DELIMITER ;

CREATE TABLE registro_actividad(
id_registro_actividad INT NOT NULL AUTO_INCREMENT,
idusuario INT NOT NULL,
idactividad INT NOT NULL,
fechaasignada TIMESTAMP NOT NULL,
PRIMARY KEY (id_registro_actividad),
FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
FOREIGN KEY (idactividad) REFERENCES actividades (idactividad)
);

DELIMITER \\
DROP TRIGGER IF EXISTS guarda_registros \\
CREATE TRIGGER guarda_registros AFTER UPDATE ON notificaciones FOR EACH ROW
BEGIN
	
END \\
DELIMITER ;

SET lc_time_names = 'es_ES';

SET GLOBAL EVENT_SCHEDULER = on;

DROP EVENT IF EXISTS event_monitor;
CREATE EVENT event_monitor ON SCHEDULE EVERY 1 DAY
STARTS '2018-01-01 00:00:01'
DO CALL alerta_notificacion();

/*-------O-------*/
SELECT CONCAT(UPPER(LEFT(DAYNAME(CURDATE()),1)), SUBSTR(DAYNAME(CURDATE()), 2)) AS nombre_dia;

DROP EVENT IF EXISTS nombre_mes;
CREATE EVENT nombre_mes
ON SCHEDULE EVERY 1 MONTH
STARTS '2018-01-01 00:00:00'
BEGIN
	CALL alerta_notificacion();
    
END ;


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
		
		
/*--- OLD PROCEDURE --- Crear notificacion
DELIMITER \\
DROP PROCEDURE IF EXISTS alertas_actividades \\
CREATE PROCEDURE alertas_actividades()
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE id_activity INTEGER;
    DECLARE id_schedule INTEGER;
	DECLARE make_notification CURSOR FOR SELECT idactividades, idcronograma FROM actividades WHERE DATEDIFF(fechaactividad, curdate()) = 10;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN make_notification;
    make_notification_loop: LOOP
		FETCH make_notification INTO id_activity, id_schedule;
		IF done THEN
			leave make_notification_loop;
        END IF;
        IF (SELECT CASE WHEN EXISTS (SELECT * FROM notificaciones WHERE idactividades=id_activity AND idusuario = (SELECT idusuario FROM cronograma WHERE idcronograma = id_schedule)) THEN FALSE ELSE TRUE END)
			THEN
				INSERT INTO notificaciones VALUES ((SELECT idusuario FROM cronograma WHERE idcronograma = id_schedule), id_activity, CONCAT('10-#00FF00-', (SELECT nombreactividad FROM actividades WHERE idactividad=id_activity)));
        END IF;
	END LOOP;
    CLOSE make_notification;
END \\
---*/

/*--- OLD PROCEDURE --- Actualizar notificaciones
DELIMITER \\
DROP PROCEDURE IF EXISTS actualizar_actividades \\
CREATE PROCEDURE actualizar_actividades()
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE id_nottificacion INTEGER DEFAULT 0;
    DECLARE id_actividad INTEGER DEFAULT 0;
    DECLARE dias_restantes INTEGER DEFAULT 0;
    DECLARE color VARCHAR(50) DEFAULT NULL;
    DECLARE update_notify CURSOR FOR SELECT idnotificacion, idactividad FROM notificaciones WHERE notificaciones.idactividad = actividades.idactividad;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN update_notify;
    update_notify_loop: LOOP
		FETCH update_notify INTO id_nottificacion, id_actividad;
		IF done THEN
			leave update_notify_loop;
        END IF;
        SET dias_restantes = (SELECT DATEDIFF((SELECT fechaactividad FROM actividades WHERE notificaciones.idactividad = actividades.idactividad), curdate()));
        IF dias_restantes < 3 THEN
			UPDATE notificaciones SET rango = CONCAT('0', dias_restantes, '-#FF0000-', (SELECT actividades.nombreactividad FROM actividades WHERE idactividad=id_actividad)) WHERE idnottificacion = id_nottificacion;
		elseif dias_restantes < 5 THEN
			UPDATE notificaciones SET rango = CONCAT('0', dias_restantes, '-#FFFF00-', (SELECT actividades.nombreactividad FROM actividades WHERE idactividad=id_actividad)) WHERE idnottificacion = id_nottificacion;
		elseif dias_restantes < 10 then
			UPDATE notificaciones SET rango = CONCAT('0', dias_restantes, '-#00FF00-', (SELECT actividades.nombreactividad FROM actividades WHERE idactividad=id_actividad)) WHERE idnottificacion = id_nottificacion;
		END IF;
    END LOOP;
END \\
---*/