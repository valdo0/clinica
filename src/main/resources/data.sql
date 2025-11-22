-- Tabla USUARIO
INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol,password)
VALUES ('Admin', 'admin@clinica.cl', '+56911111111', CURRENT_DATE, 'ADMIN','admin');

INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol,password)
VALUES ('Juan Pérez', 'juan@clinica.cl', '+56922222222', CURRENT_DATE, 'LABMANAGER','juan');

INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol,password)
VALUES ('Sebastián Valdivia', 'paciente@duocuc.cl', '+56922222222', CURRENT_DATE, 'PACIENTE','sebastian');

INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol,password)
VALUES ('Sebastián Valdivia', 'se.valdivia@duocuc.cl', '+56922222222', CURRENT_DATE, 'PACIENTE','sebastian');

-- Tabla TIPO_ANALISIS
INSERT INTO TIPO_ANALISIS (nombre, descripcion)
VALUES ('Hemograma', 'Análisis general de sangre.');

INSERT INTO TIPO_ANALISIS (nombre, descripcion)
VALUES ('Orina Completa', 'Examen de orina con análisis físico y químico.');

INSERT INTO TIPO_ANALISIS (nombre, descripcion)
VALUES ('Colesterol', 'Mide niveles de colesterol total y fracciones.');

-- Tabla LABORATORIO
INSERT INTO LABORATORIO (nombre, direccion, telefono, habilitado)
VALUES ('Laboratorio Central', 'Av. Principal 123', '+56933333333', 1);

INSERT INTO LABORATORIO (nombre, direccion, telefono, habilitado)
VALUES ('Lab Norte', 'Calle Norte 45', '+56944444444', 1);

INSERT INTO LABORATORIO (nombre, direccion, telefono, habilitado)
VALUES ('Lab Sur', 'Calle Sur 45', '+56944444444', 1);

-- Tabla intermedia LABORATORIO_TIPO_ANALISIS
INSERT INTO LABORATORIO_TIPO_ANALISIS (laboratorio_id, tipo_analisis_id)
VALUES (1, 1);

INSERT INTO LABORATORIO_TIPO_ANALISIS (laboratorio_id, tipo_analisis_id)
VALUES (1, 2);

INSERT INTO LABORATORIO_TIPO_ANALISIS (laboratorio_id, tipo_analisis_id)
VALUES (2, 3);

INSERT INTO LABORATORIO_TIPO_ANALISIS (laboratorio_id, tipo_analisis_id)
VALUES (3, 3);

-- Tabla ANALISIS
INSERT INTO ANALISIS (laboratorio_id, tipo_analisis_id, usuario_id, estado, descripcion, comentarios, fecha_solicitud, fecha_finalizacion)
VALUES (1, 1, 3, 'PENDIENTE', 'Solicitud de hemograma de rutina', null, CURRENT_TIMESTAMP, null);

INSERT INTO ANALISIS (laboratorio_id, tipo_analisis_id, usuario_id, estado, descripcion, comentarios, fecha_solicitud, fecha_finalizacion)
VALUES (1, 2, 3, 'TERMINADO', 'Examen de orina completo', 'Resultados normales', CURRENT_TIMESTAMP - 2, CURRENT_TIMESTAMP - 1);

INSERT INTO ANALISIS (laboratorio_id, tipo_analisis_id, usuario_id, estado, descripcion, comentarios, fecha_solicitud, fecha_finalizacion)
VALUES (2, 3, 4, 'CANCELADO', 'Chequeo de colesterol', 'Cancelado por el paciente', CURRENT_TIMESTAMP - 5, null);
