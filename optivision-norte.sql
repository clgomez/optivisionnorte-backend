-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2022 a las 19:55:05
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `optivision-norte`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cli_fecha_nacimiento` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cli_sexo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_persona` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cli_fecha_nacimiento`, `cli_sexo`, `id_persona`) VALUES
('10/03/2001', 'femenino', 3),
('12/04/1992', 'masculino', 4),
('03/07/1987', 'masculino', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `emp_estado` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `emp_rol` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_persona` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`emp_estado`, `emp_rol`, `id_persona`) VALUES
('activo', 'administrador', 1),
('activo', 'administrador', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` bigint(20) NOT NULL,
  `apellidos` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `direccion` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `identificacion` bigint(20) DEFAULT NULL,
  `nombres` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tipo_identificacion` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `apellidos`, `direccion`, `email`, `identificacion`, `nombres`, `telefono`, `tipo_identificacion`) VALUES
(1, 'mera molano', 'bosques del pinar', 'mmeram@gmail.com', 1061852423, 'mary luz', '3166952569', 'cedula de ciudadanía'),
(2, 'gonzalez', 'bosques del pinar', 'agonzales@gmail.com', 1061298754, 'andres', '318527413', 'cedula de ciudadanía'),
(3, 'jimenez paz', 'bello horizonte', 'kjimenezp@gmail.com', 1061325897, 'karen viviana', '321578269', 'cedula de ciudadanía'),
(4, 'fernandez alegria', 'asturias', 'cafernandez@gmail.com', 1060238941, 'carlos alberto', '319457821', 'cedula de ciudadania'),
(5, 'satizabal valverde', 'guaduales de la hacienda', 'jcsatizabal@gmail.com', 1061784513, 'juan carlos', '321203876', 'cedula de ciudadanía');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `s_personas`
--

CREATE TABLE `s_personas` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `s_personas`
--

INSERT INTO `s_personas` (`next_val`) VALUES
(7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_persona`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_persona`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FKlbs69o9qkvv7lgn06idak3crb` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK3yo5m2sf91t2spkatlwxagm5x` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
