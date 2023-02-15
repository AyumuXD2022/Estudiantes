INSERT INTO `db_escuela`.`direcciones`(`calle`,`ciudad`,`codigo_postal`) VALUES('Avenida','Ecatepec','55000');
INSERT INTO `db_escuela`.`direcciones`(`calle`,`ciudad`,`codigo_postal`) VALUES('Avenida2','Ecatepec2','55000');
INSERT INTO `db_escuela`.`direcciones`(`calle`,`ciudad`,`codigo_postal`) VALUES('Avenida3','Ecatepec3','55000');
INSERT INTO `db_escuela`.`direcciones`(`calle`,`ciudad`,`codigo_postal`) VALUES('Avenida4','Ecatepec4','55000');
INSERT INTO `db_escuela`.`estudiantes` (`apellido`, `email`, `nombre`, `telefono`, `direccion_id`) VALUES ('Ortega', 'correo@mailcom', 'Gustavo', '5585456', '1');
INSERT INTO `db_escuela`.`estudiantes` (`apellido`, `email`, `nombre`, `telefono`, `direccion_id`) VALUES ('Fulano', 'correo2@mail.com', 'Santiago', '55854562', '2');

INSERT INTO `db_escuela`.`profesores` (`apellido`, `email`, `nombre`, `telefono`, `direccion_id`) VALUES ('Ortega2', 'correo@mailcom', 'Gustavo2', '5585456', '3');
INSERT INTO `db_escuela`.`profesores` (`apellido`, `email`, `nombre`, `telefono`, `direccion_id`) VALUES ('Fulano2', 'correo2@mail.com', 'Santiago2', '55854562', '4');

INSERT INTO `db_escuela`.`clases` (`descripcion`, `fecha_fin`, `fecha_inicio`, `nombre`) VALUES ('Bueno', '2023-12-31', '2023-01-31', 'Matematica');
INSERT INTO `db_escuela`.`clases` (`descripcion`, `fecha_fin`, `fecha_inicio`, `nombre`) VALUES ('Bueno', '2023-12-31', '2023-01-31', 'Cienca');