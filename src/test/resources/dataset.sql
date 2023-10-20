INSERT INTO Usuario (cedula, contrasena, email, estado)
VALUES
    ('1234567890', 'password1', 'roberto@example.com', 0),
    ('2384736439', 'password2', 'pablo@example.com', 0),
    ('3456789012', 'password3', 'nora@example.com', 0),
    ('8945662776', 'password4', 'pepito@example.com', 0),
    ('6423645635', 'password5', 'ana@example.com', 0),

    ('1287483633', 'password6', 'emilia@example.com', 0),
    ('3647856424', 'password7', 'jhoan@example.com', 0),
    ('7823684763', 'password8', 'juana@example.com', 0),
    ('7623576456', 'password9', 'salome@example.com', 0),
    ('9384838433', 'password10', 'santiago@example.com', 0),

    ('7468763743', 'password11', 'aurora@example.com', 0),
    ('2345678901', 'password12', 'juan@example.com', 0),
    ('6748634453', 'password13', 'jose@example.com', 0),
    ('4758634653', 'password14', 'johana@example.com', 0),
    ('7623525453', 'password15', 'andres@example.com', 0);



--Inserccion en la tabla administrador
INSERT INTO Administrador (cedula)
VALUES
    ('1234567890'),
    ('2384736439'),
    ('3456789012'),
    ('8945662776'),
    ('6423645635');

-- Inserción de registros en la tabla Paciente
INSERT INTO Paciente (cedula, apellido, ciudad, foto, nombre, telefono, alergias, eps, fecha_nacimiento, tipo_sangre)
VALUES
    ('1287483633', 'Alvarez', 'Calarca', 'paciente1.jpg', 'Emilia', '9876543210', 'Alergia1', 1, '2000-01-01 00:00:00', 1),
    ('3647856424', 'Soler', 'Armenia', 'paciente1.jpg', 'Jhoan', '3255376464', 'Alergia2', 0, '2000-01-01 00:00:00', 1),
    ('7823684763', 'Palacio', 'Armenia', 'paciente1.jpg', 'Juana', '3126554756', 'Alergia3', 5, '2000-01-01 00:00:00', 1),
    ('7623576456', 'Rodriguez', 'Circasia', 'paciente1.jpg', 'Salome', '3008764534', 'Alergia4', 3, '2000-01-01 00:00:00', 1),
    ('9384838433', 'Ramon', 'Buenavista', 'paciente1.jpg', 'Santiago', '3054679872', 'Alergia5', 3, '2000-01-01 00:00:00', 1);

-- Inserción de registros en la tabla medico
INSERT INTO Medico (cedula, apellido, ciudad, foto, nombre, telefono, especialidad, hora_fin, hora_inicio)
VALUES
    ('7468763743', 'Garcia', 'Barranquilla', 'medico1.jpg', 'Dr. Aurora', '3045762345', 0, '2023-10-10 18:00:00', '2023-10-10 09:00:00'),
    ('2345678901', 'Parra', 'Pereira', 'medico2.jpg', 'Dr. Juan', '3099986776', 1, '2023-10-14 18:00:00', '2023-10-14 09:00:00'),
    ('6748634453', 'Moreno', 'Medellin', 'medico3.jpg', 'Dr. Jose', '3012341178', 3, '2023-10-15 18:00:00', '2023-10-15 09:00:00'),
    ('4758634653', 'Fernandez', 'Pasto', 'medico4.jpg', 'Dr. Johana', '3004569898', 2, '2023-10-17 18:00:00', '2023-10-17 09:00:00'),
    ('7623525453', 'Florez', 'Quibdo', 'medico5.jpg', 'Dr. Andres', '3145884631', 5, '2023-10-21 18:00:00', '2023-10-21 09:00:00');

INSERT INTO Cita ( estado_cita, fecha_cita, fecha_creacion, motivo, medico_cedula, paciente_cedula)
VALUES
    (0, '2023-10-22 10:00:00', '2023-10-19 12:00:00', 'Consulta de rutina', '7468763743', '1287483633'),
    (0, '2023-10-31 10:00:00', '2023-10-19 13:00:00', 'Consulta de rutina', '2345678901', '3647856424'),
    (0, '2023-10-24 10:00:00', '2023-10-19 14:00:00', 'Consulta de rutina', '6748634453', '7823684763'),
    (0, '2023-10-27 10:00:00', '2023-10-19 15:00:00', 'Consulta de rutina', '4758634653', '7623576456'),
    (0, '2023-10-30 10:00:00', '2023-10-19 16:00:00', 'Consulta de rutina', '7623525453', '9384838433');


INSERT INTO Consulta (diagnostico, notas, sintomas, tratamiento, cita_id)
VALUES
    ('Diagnostico de prueba', 'Sin notas', 'Ninguno', 'No aplica', 1),
    ('Diagnostico de prueba', 'Sin notas', 'Fiebre', 'Tratamiento de prueba', 2),
    ('Diagnostico de prueba', 'Notas de prueba', 'Dolor de cabeza', 'Pastillas de Cafeina', 3),
    ('Diagnostico de prueba', 'Nada que decir, todo perfecto', 'Ninguno', 'Tomar mucha aguita', 4),
    ('Diagnostico de prueba', 'Notas de prueba', 'Dolor en los pies', 'Tratamiento de prueba', 5);

INSERT INTO PQRS (asunto, descripcion, estado, fecha_creacion, tipopqrs, consulta_id)
VALUES
    ('Asunto 1', 'Que mal servicio', 0, '2023-11-01 10:00:00.000000', 1, 1),
    ('Asunto 2', 'No esperaba ser tan bien atendido, felicitaciones', 0, '2023-11-01 11:00:00.000000', 1, 2),
    ('Asunto 3', 'Se cayeron dos viejitos', 0, '2023-11-01 12:00:00.000000', 1, 3),
    ('Asunto 4', 'No hay suficientes sillas', 0, '2023-11-01 13:00:00.000000', 1, 4),
    ('Asunto 5', 'Descripcion', 0, '2023-11-01 14:00:00.000000', 1, 5);

INSERT INTO Mensaje (fecha, texto, pqrs_id, usuario_cedula)
VALUES
    ('2023-10-15 15:00:00', 'Inicio del mensaje', 1, '1287483633' ),
    ('2023-10-15 15:30:00', 'Inicio del mensaje', 3, '3647856424' ),
    ('2023-10-15 16:00:00', 'No hay marcas de suelo cuando esta mojado', 3, '3647856424' ),
    ('2023-10-15 16:30:00', 'Que complejo que no haya seguridad para las personas mayores de edad', 3, '3647856424' ),
    ('2023-10-15 15:00:00', 'Fin del mensaje', 3, '3647856424' ),
    ('2023-10-15 15:00:00', 'Muy buen trabajo en el aseo', 1, '1287483633' ),
    ('2023-10-15 15:00:00', 'Fin del mensaje', 1, '1287483633' );

INSERT INTO Mensaje (fecha,texto, mensaje_id, pqrs_id, usuario_cedula )
VALUES
    ('2023-10-15 16:00:00', 'No hay marcas de suelo cuando esta mojado', 2, 3, '3647856424'),
    ('2023-10-15 16:00:00', 'Que complejo que no haya seguridad para las personas mayores de edad', 3, 3, '3647856424'),
    ('2023-10-15 16:00:00', 'Fin del mensaje', 4, 3, '3647856424'),
    ('2023-10-15 16:00:00', 'Muy buen trabjo en el aseo', 1, 1, '3647856424'),
    ('2023-10-15 16:00:00', 'Fin del mensaje', 7, 1, '3647856424');




