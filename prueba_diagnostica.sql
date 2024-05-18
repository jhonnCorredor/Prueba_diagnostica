-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 18-05-2024 a las 21:58:47
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba_diagnostica`
--
CREATE DATABASE IF NOT EXISTS `prueba_diagnostica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `prueba_diagnostica`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint NOT NULL,
  `estado` bit(1) NOT NULL,
  `apellido_cliente` varchar(45) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `identificacion` varchar(10) NOT NULL,
  `nombre_cliente` varchar(45) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `tipo_identificacion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `estado`, `apellido_cliente`, `ciudad`, `direccion`, `identificacion`, `nombre_cliente`, `telefono`, `tipo_identificacion`) VALUES
(1, b'1', 'carlos', 'neiva', 'calle 80a', '1081398776', 'juan', '3208592655', 'CC'),
(3, b'1', 'gonzales', 'neiva', 'calle 80b', '1081398778', 'Pedro', '3158592655', 'CC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descripcion_ventas`
--

CREATE TABLE `descripcion_ventas` (
  `id` bigint NOT NULL,
  `estado` bit(1) NOT NULL,
  `cantidad` bigint NOT NULL,
  `descuento` double NOT NULL,
  `precio` double NOT NULL,
  `sub_total` double NOT NULL,
  `productos_id_producto` bigint NOT NULL,
  `ventas_id_venta` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `descripcion_ventas`
--

INSERT INTO `descripcion_ventas` (`id`, `estado`, `cantidad`, `descuento`, `precio`, `sub_total`, `productos_id_producto`, `ventas_id_venta`) VALUES
(1, b'1', 2, 0, 0, 0, 1, 1),
(2, b'1', 5, 0, 5000, 25000, 1, 2),
(3, b'1', 2, 0, 300, 600, 2, 2),
(6, b'1', 5, 0, 5000, 25000, 1, 4),
(7, b'1', 5, 0, 5000, 25000, 1, 4),
(8, b'1', 2, 0, 300, 600, 2, 4),
(9, b'1', 1, 0, 5000, 5000, 1, 5),
(10, b'1', 1, 0, 5000, 5000, 1, 6),
(11, b'1', 1, 0, 5000, 5000, 1, 6),
(12, b'1', 1, 0, 10000, 10000, 7, 6),
(13, b'1', 1, 0, 5000, 5000, 1, 6),
(14, b'1', 1, 0, 10000, 10000, 7, 6),
(20, b'1', 1, 0, 5000, 5000, 1, 7),
(21, b'1', 1, 0, 10000, 10000, 7, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` bigint NOT NULL,
  `estado` bit(1) NOT NULL,
  `cantidad` bigint NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `nombre_producto` varchar(45) NOT NULL,
  `porcentaje_iva` bigint NOT NULL,
  `precio` double NOT NULL,
  `porcentaje_descuento` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `estado`, `cantidad`, `descripcion`, `nombre_producto`, `porcentaje_iva`, `precio`, `porcentaje_descuento`) VALUES
(1, b'1', 24, 'Gaseosa coca-cola', 'coca-cola', 0, 5000, 0),
(2, b'1', 3, 'coliflor', 'coliflor', 0, 300, 0),
(3, b'1', 5, 'habichuela', 'habichuela', 1, 20, 20),
(5, b'1', 8, 'Papa pastusa', 'Papa', 1, 200, 0),
(6, b'1', 10, 'grano de maiz', 'Maiz', 1, 5000, 0),
(7, b'1', 20, 'pan dulce', 'pan', 10, 10000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` bigint NOT NULL,
  `estado` bit(1) NOT NULL,
  `fecha_venta` datetime(6) NOT NULL,
  `total` varchar(36) NOT NULL,
  `cliente_id_cliente` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `estado`, `fecha_venta`, `total`, `cliente_id_cliente`) VALUES
(1, b'1', '2024-05-17 22:20:41.844000', '0', 1),
(2, b'1', '2024-05-18 14:01:00.000000', '29696.00', 3),
(4, b'1', '2024-05-18 16:04:00.000000', '33792.00', 1),
(5, b'1', '2024-05-18 16:47:00.000000', '6600.00', 1),
(6, b'1', '2024-05-18 16:48:00.000000', '19800.00', 3),
(7, b'0', '2024-05-18 16:51:00.000000', '19800.00', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `descripcion_ventas`
--
ALTER TABLE `descripcion_ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbxbgcx4dxiebwfmye2bx6v2rc` (`productos_id_producto`),
  ADD KEY `FKikmitufic910b2cepdpqcwbqa` (`ventas_id_venta`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgml6nqpk2hi4n186lemvhdkqs` (`cliente_id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `descripcion_ventas`
--
ALTER TABLE `descripcion_ventas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `descripcion_ventas`
--
ALTER TABLE `descripcion_ventas`
  ADD CONSTRAINT `FKbxbgcx4dxiebwfmye2bx6v2rc` FOREIGN KEY (`productos_id_producto`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `FKikmitufic910b2cepdpqcwbqa` FOREIGN KEY (`ventas_id_venta`) REFERENCES `ventas` (`id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `FKgml6nqpk2hi4n186lemvhdkqs` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
