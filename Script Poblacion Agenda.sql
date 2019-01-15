
insert into tiposactividades (tiposactividades.tipoactividad) values ('administrativa');
insert into tiposactividades (tiposactividades.tipoactividad) values ('Personal');
insert into tiposactividades (tiposactividades.tipoactividad) values ('Religiosa');

insert into tiposfases (tiposfases.tipofase) values ('inicio');

insert into tiposusuarios(tiposusuarios.tipo) values ('Super Administrador');
insert into tiposusuarios(tiposusuarios.tipo) values ('Gerente');
insert into tiposusuarios(tiposusuarios.tipo) values ('Usuario');


insert into usuarios(usuarios.idtipousuario, usuarios.nombres, usuarios.apellidos, usuarios.genero,
usuarios.telefono, usuarios.correo, usuarios.contra, usuarios.niveldemando) values (1,'David','Flores','M',
'7859-5321','davidflores@gmail.com','123456',1);
insert into usuarios(usuarios.idtipousuario, usuarios.nombres, usuarios.apellidos, usuarios.genero,
usuarios.telefono, usuarios.correo, usuarios.contra, usuarios.niveldemando) values (2,'Eliseo','Garcia','M',
'7859-5321','eliseogarcia@gmail.com','123456',3);
insert into usuarios(usuarios.idtipousuario, usuarios.nombres, usuarios.apellidos, usuarios.genero,
usuarios.telefono, usuarios.correo, usuarios.contra, usuarios.niveldemando) values (3,'Fernando','Medrano','M',
'7859-5321','fernandomedrano@gmail.com','123456',3);

insert into cronogramas(cronogramas.idusuario, cronogramas.nombre, cronogramas.descripcion) values
(1,'Reuniones Administrativas','Reunion dónde se tocan puntos administrativos de la empresa');
insert into cronogramas(cronogramas.idusuario, cronogramas.nombre, cronogramas.descripcion) values
(1,'Actividad Religiosa','Fiestas Patronales');
insert into cronogramas(cronogramas.idusuario, cronogramas.nombre, cronogramas.descripcion) values
(1,'Actividades Personales','Asuntos personales');
insert into cronogramas(cronogramas.idusuario, cronogramas.nombre, cronogramas.descripcion) values
(1,'Actividades Controversiales','Asuntos Controversiales');

insert into calendarios(calendarios.idcronograma, calendarios.año) values (1,2018);

insert into meses(meses.idcalendario,meses.mes) values (1,'Enero');

insert into dias(dias.idmes, dias.nombredia) values (1,'lunes');

insert into fases(fases.idtipofase, fases.estado, fases.fecha) values (1,'activo','2018-01-01');

insert into roles(roles.idtipousuario, roles.rol) values (1,'Administrador');

insert into actividades(actividades.idcronograma,actividades.idtipoactividad, 
actividades.nombreactividad, actividades.fechaactividad, actividades.idfase, actividades.idusuario) values (1,1,'Reunion','2019-02-02',1,3); 
insert into actividades(actividades.idcronograma,actividades.idtipoactividad, 
actividades.nombreactividad, actividades.fechaactividad, actividades.idfase, actividades.idusuario) values (3,2,'Vacacion','2019-02-02',1,3); 
insert into actividades(actividades.idcronograma,actividades.idtipoactividad, 
actividades.nombreactividad, actividades.fechaactividad, actividades.idfase, actividades.idusuario) values (2,3,'Semana Santa','2019-04-02',1,3);