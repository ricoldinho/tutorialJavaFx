-- 1. Crear la base de datos si aún no existe.
CREATE DATABASE IF NOT EXISTS pokerfx;

-- 2. Usar la base de datos pokerfx para las siguientes operaciones.
USE pokerfx;

-- 3. Crear la tabla 'jugadores'.
-- Se elimina la tabla si ya existe para empezar desde cero.
DROP TABLE IF EXISTS jugadores;

CREATE TABLE jugadores (
    -- 'id' es la clave primaria, se incrementa automáticamente en cada inserción.
                           id INT PRIMARY KEY AUTO_INCREMENT,

    -- 'nombre', 'apellido' y 'apodo' son cadenas de texto.
    -- Se definen como NOT NULL para asegurar que siempre tengan un valor.
                           nombre VARCHAR(100) NOT NULL,
                           apellido VARCHAR(100) NOT NULL,
                           apodo VARCHAR(100) NOT NULL,

    -- 'fecha_nacimiento' se mapea con el tipo DATE de SQL, ideal para LocalDate de Java.
                           fecha_nacimiento DATE NOT NULL,

    -- 'tipoJuego' almacena el estilo del jugador.
                           tipoJuego VARCHAR(50) NOT NULL
);

-- Inserciones para la tabla 'jugadores' en la base de datos 'pokerfx'

-- Jugadores Internacionales de WPT y EPT
INSERT INTO jugadores (nombre, apellido, apodo, fecha_nacimiento, tipoJuego) VALUES
                                                                                 ('Daniel', 'Negreanu', 'Kid Poker', '1974-07-26', 'Agresivo'),
                                                                                 ('Phil', 'Ivey', 'No Home Jerome', '1977-02-01', 'Roca'),
                                                                                 ('Phil', 'Hellmuth', 'The Poker Brat', '1964-07-16', 'Farolero'),
                                                                                 ('Erik', 'Seidel', 'Sly', '1959-11-06', 'Conservador'),
                                                                                 ('Justin', 'Bonomo', 'ZeeJustin', '1985-09-30', 'Agresivo'),
                                                                                 ('Stephen', 'Chidwick', 'Stevie444', '1989-05-10', 'Agresivo'),
                                                                                 ('Fedor', 'Holz', 'CrownUpGuy', '1993-07-25', 'Agresivo'),
                                                                                 ('Patrik', 'Antonius', 'The Finn', '1980-12-13', 'Roca'),
                                                                                 ('Gus', 'Hansen', 'The Great Dane', '1974-02-13', 'Farolero'),
                                                                                 ('Viktor', 'Blom', 'Isildur1', '1990-09-26', 'Agresivo');

-- Jugadores Españoles y del Equipo Winamax/PokerStars
INSERT INTO jugadores (nombre, apellido, apodo, fecha_nacimiento, tipoJuego) VALUES
                                                                                 ('Adrián', 'Mateos', 'Amadi_17', '1994-07-01', 'Agresivo'),
                                                                                 ('Sergio', 'Aído', 'Petgaming', '1988-08-05', 'Agresivo'),
                                                                                 ('Leo', 'Margets', 'L. Margets', '1983-08-10', 'Conservador'),
                                                                                 ('Carlos', 'Mortensen', 'El Matador', '1972-04-13', 'Farolero'),
                                                                                 ('Ramón', 'Colillas', 'Mr. Boxes', '1988-11-09', 'Conservador'),
                                                                                 ('Davidi', 'Kitai', 'Kitbul', '1979-09-28', 'Agresivo'),
                                                                                 ('João', 'Vieira', 'Naza114', '1989-07-19', 'Roca'),
                                                                                 ('Mustapha', 'Kanit', 'Mustacchione', '1991-01-24', 'Farolero'),
                                                                                 ('Ana', 'Márquez', 'Ana Marquez', '1986-12-19', 'Conservador'),
                                                                                 ('Juan', 'Pardo', 'Malaka$tyle', '1993-05-18', 'Agresivo');

create table pokerfx.usuarios
(
    usuario_id       int auto_increment primary key,
    nombre           varchar(55)  not null,
    apellido         varchar(55)  not null,
    nickname         varchar(155) null,
    fecha_nacimiento date         not null,
    email            varchar(255) not null,
    password         varchar(255) not null,
    constraint nickname
        unique (nickname)
);

INSERT INTO pokerfx.usuarios (nombre, apellido, nickname, fecha_nacimiento, email)
VALUES
    ('Diego', 'Villares', 'ElPulpo', '1996-06-17', 'diego.villares@pokerfx.com', 'DiegoV'),
    ('Yeremay', 'Hernández', 'Peke', '2002-12-10', 'yeremay.hernandez@pokerfx.com', 'YeremayH'),
    ('David', 'Mella', 'Mella11', '2005-05-23', 'david.mella@pokerfx.com', 'DavidM');
