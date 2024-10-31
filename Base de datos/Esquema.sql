-- Crear base de datos
CREATE DATABASE IF NOT EXISTS GestionActividadesLimpieza;
USE GestionActividadesLimpieza;

-- Tabla Administrador
CREATE TABLE Administrador (
    idAdministrador INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(50) NOT NULL,
    contraseña VARCHAR(100) NOT NULL
);

-- Tabla Actividad
CREATE TABLE Actividad (
    idActividad INT PRIMARY KEY AUTO_INCREMENT,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    evidenciaImagen VARCHAR(255)
);

-- Tabla Colonia
CREATE TABLE Colonia (
    idColonia INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    codigoPostal VARCHAR(10) NOT NULL,
    municipio VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL
);

-- Tabla Cuadrilla
CREATE TABLE Cuadrilla (
    idCuadrilla INT PRIMARY KEY AUTO_INCREMENT,
    nombreCuadrilla VARCHAR(100) NOT NULL
);

-- Tabla JefeCuadrilla
CREATE TABLE JefeCuadrilla (
    idJefe INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    idCuadrilla INT,
    FOREIGN KEY (idCuadrilla) REFERENCES Cuadrilla(idCuadrilla)
);

-- Tabla intermedia Actividad_Colonia (Relación muchos a muchos)
CREATE TABLE Actividad_Colonia (
    idActividad INT,
    idColonia INT,
    PRIMARY KEY (idActividad, idColonia),
    FOREIGN KEY (idActividad) REFERENCES Actividad(idActividad) ON DELETE CASCADE,
    FOREIGN KEY (idColonia) REFERENCES Colonia(idColonia) ON DELETE CASCADE
);

-- Tabla intermedia Cuadrilla_Actividad (Relación muchos a muchos)
CREATE TABLE Cuadrilla_Actividad (
    idCuadrilla INT,
    idActividad INT,
    PRIMARY KEY (idCuadrilla, idActividad),
    FOREIGN KEY (idCuadrilla) REFERENCES Cuadrilla(idCuadrilla) ON DELETE CASCADE,
    FOREIGN KEY (idActividad) REFERENCES Actividad(idActividad) ON DELETE CASCADE
);
