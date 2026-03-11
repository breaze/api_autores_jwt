CREATE DATABASE biblioteca;
USE biblioteca;

-- ─────────────────────────────────────────
-- Tabla de autores
-- ─────────────────────────────────────────
CREATE TABLE autor (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL,
    nacionalidad  VARCHAR(50),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ─────────────────────────────────────────
-- Tabla de libros (N:1 con autor)
-- ─────────────────────────────────────────
CREATE TABLE libro (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo            VARCHAR(200) NOT NULL,
    anio_publicacion  INT,
    autor_id          BIGINT NOT NULL,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (autor_id) REFERENCES autor(id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- ─────────────────────────────────────────
-- Tabla de detalles del libro (1:1)
-- ─────────────────────────────────────────
CREATE TABLE detalle_libro (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn        VARCHAR(20) UNIQUE NOT NULL,
    num_paginas INT,
    idioma      VARCHAR(50),
    libro_id    BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (libro_id) REFERENCES libro(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ─────────────────────────────────────────
-- Tabla de categorías
-- ─────────────────────────────────────────
CREATE TABLE categoria (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE   -- evita duplicados de nombre
);

-- ─────────────────────────────────────────
-- Tabla intermedia libro_categoria (N:N)
-- ─────────────────────────────────────────
CREATE TABLE libro_categoria (
    libro_id      BIGINT NOT NULL,
    categoria_id  BIGINT NOT NULL,
    prioridad     INT,
    added_at      DATETIME DEFAULT CURRENT_TIMESTAMP,  -- ✔ valor por defecto
    comentario    VARCHAR(255),
    PRIMARY KEY (libro_id, categoria_id),              -- ✔ PK compuesta, sin id redundante
    FOREIGN KEY (libro_id)     REFERENCES libro(id)     ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ─────────────────────────────────────────
-- Tabla de usuarios
-- ─────────────────────────────────────────
CREATE TABLE usuario (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(100) NOT NULL UNIQUE,
    -- Guardar SIEMPRE un hash (bcrypt/argon2), nunca texto plano
    password   VARCHAR(255) NOT NULL,
    activo     BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ─────────────────────────────────────────
-- Tabla de roles
-- ─────────────────────────────────────────
CREATE TABLE rol (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- ─────────────────────────────────────────
-- Tabla intermedia usuario_rol (N:N)
-- ─────────────────────────────────────────
CREATE TABLE usuario_rol (
    usuario_id BIGINT NOT NULL,
    rol_id     BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_rol     FOREIGN KEY (rol_id)     REFERENCES rol(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);