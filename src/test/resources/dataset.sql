INSERT INTO Usuario (cedula, contrasena, email, estado)
VALUES
    ('1234567890', 'password1', 'usuario1@example.com', 0),
    ('2345678901', 'password2', 'usuario2@example.com', 0),
    ('3456789012', 'password3', 'paciente1@example.com', 0);



--Inserccion en la tabla administrador
INSERT INTO Administrador (cedula)
VALUES
    ('1234567890');

-- Inserción de registros en la tabla Paciente
INSERT INTO Paciente (cedula, apellido, ciudad, foto, nombre, telefono, alergias, eps, fecha_nacimiento, tipo_sangre)
VALUES
    ('3456789012', 'Paciente1', 'Ciudad2', 'paciente1.jpg', 'Paciente 1', '9876543210', 'Alergia1', 1, '2000-01-01 00:00:00', 1);

-- Inserción de registros en la tabla medico
INSERT INTO Medico (cedula, apellido, ciudad, foto, nombre, telefono, especialidad, hora_fin, hora_inicio)
VALUES
    ('2345678901', 'Medico1', 'Ciudad1', 'medico1.jpg', 'Dr. Medico1', '1234567890', 1, '2023-10-14 18:00:00', '2023-10-14 09:00:00');

INSERT INTO Cita ( estado_cita, fecha_cita, fecha_creacion, motivo, medico_cedula, paciente_cedula)
VALUES
    (0, '2023-10-31 10:00:00', '2023-10-19 12:00:00', 'Consulta de rutina', '2345678901', '3456789012');


INSERT INTO Consulta (diagnostico, notas, sintomas, tratamiento, cita_id)
VALUES
    ('Diagnostico de prueba', 'Notas de prueba', 'Sintomas de prueba', 'Tratamiento de prueba', 1);

INSERT INTO PQRS (asunto, descripcion, estado, fecha_creacion, tipopqrs, consulta_id)
VALUES
    ('Asunto 1', 'Descripcion 1', 0, '2023-10-14 10:00:00.000000', 1, 1);

INSERT INTO Mensaje (fecha, texto, pqrs_id, usuario_cedula)
VALUES
    ('2023-10-15 15:00:00', 'Inicio del mensaje', 1, '1234567890' );

INSERT INTO Mensaje (fecha,texto, mensaje_id, pqrs_id, usuario_cedula )
VALUES
    ('2023-10-15 16:00:00', 'Segundo Mensaje', 1, 1, '3456789012');




