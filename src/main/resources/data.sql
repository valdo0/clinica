-- Tabla USUARIO
INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol)
VALUES ('Admin', 'admin@clinica.cl', '+56911111111', CURRENT_DATE, 'ADMIN');

INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol)
VALUES ('Juan Pérez', 'juan@clinica.cl', '+56922222222', CURRENT_DATE, 'USER');

INSERT INTO USUARIO (nombre, email, telefono, fecha_registro, rol)
VALUES ('Sebastián Valdivia', 'se.valdivia@duocuc.cl', '+56922222222', CURRENT_DATE, 'USER');

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
