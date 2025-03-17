-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 17, 2025 at 05:03 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prueba_tecnica`
--

-- --------------------------------------------------------

--
-- Table structure for table `ciudad`
--

CREATE TABLE `ciudad` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `departamento_codigo` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ciudad`
--

INSERT INTO `ciudad` (`codigo`, `nombre`, `departamento_codigo`) VALUES
('C01', 'Medellín', 'D01'),
('C02', 'Bogotá', 'D02');

-- --------------------------------------------------------

--
-- Table structure for table `departamento`
--

CREATE TABLE `departamento` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `departamento`
--

INSERT INTO `departamento` (`codigo`, `nombre`) VALUES
('D01', 'Antioquia'),
('D02', 'Cundinamarca');

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `precio` double NOT NULL,
  `fecha_compra` date NOT NULL,
  `fecha_venta` date DEFAULT NULL,
  `estado` enum('ACTIVO','ASIGNADO','DISPONIBLE') NOT NULL,
  `ciudad_codigo` varchar(10) DEFAULT NULL
) ;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`id`, `nombre`, `descripcion`, `tipo`, `precio`, `fecha_compra`, `fecha_venta`, `estado`, `ciudad_codigo`) VALUES
(2, 'Cemento Gris', 'Saco de cemento de 50kg', 'Construcción', 50000, '2024-02-15', NULL, 'ACTIVO', 'C02'),
(16, 'Ladrillo', 'asddasd', 'Construccion', 23323, '2025-03-04', NULL, 'ACTIVO', 'C02'),
(17, 'Ladrillo', 'ladrillo rojo', 'Aseo', 213123123, '2025-03-04', NULL, 'ASIGNADO', 'C01');

-- --------------------------------------------------------

--
-- Table structure for table `rol`
--

CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rol`
--

INSERT INTO `rol` (`id`, `nombre`) VALUES
(1, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `password`, `rol_id`) VALUES
(5, 'usuario1@ejemplo1.com', '$2a$10$UaRlk9MMydG5gXtMwNl9KeT631r2nsaMK0mlAu4J28gu11hR6mGdi', 1),
(6, 'usuario12@ejemplo1.com', '$2a$10$GMyyNds/6.WbWzfBlQjPwecoJacIPCNiQlM3Pgnit0KXlc/4.mjrO', 1),
(7, 'jorge@1231231', '$2a$10$cm1KTdwWy4sVD2p2.Gxqj.7Lkf32RXtWfCpWnK/BTRgZP9GPmbQYy', 1),
(8, 'jorge2@2.com', '$2a$10$wcFSuPw5ib.kkM4lVFc4a.9tBuR3aZf/Ozf6h6FHIpGa.bUSYCGAu', 1),
(9, 'eqwe@12312.com', '$2a$10$ecPtkkW8UPkX8Ob3Ako05.Omyc/CctQCFCBt5rZg0KhPSL.Cu5zYm', 1),
(10, 'dasdsa1@wee', '$2a$10$hYrh6dx9UlIMQnlLB/qONerN1oadvH3DBLCPPqpMPtExM4JHmuVEm', 1),
(11, '1233333@3123', '$2a$10$0HuocB3lZT2e1d1JDVSqX.ssYxE0Bjqwdkijfg3O4Xitvd4KBaYsm', 1),
(13, '213123@3332', '$2a$10$3t.jw9iA9/7LYb/g.8LQw.8OPs6eYc/wKzbVtjkHlcxlAv2Nd1lpi', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `departamento_codigo` (`departamento_codigo`);

--
-- Indexes for table `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ciudad_codigo` (`ciudad_codigo`);

--
-- Indexes for table `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK43kr6s7bts1wqfv43f7jd87kp` (`nombre`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`),
  ADD KEY `FKshkwj12wg6vkm6iuwhvcfpct8` (`rol_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rol`
--
ALTER TABLE `rol`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`departamento_codigo`) REFERENCES `departamento` (`codigo`) ON DELETE CASCADE;

--
-- Constraints for table `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `material_ibfk_1` FOREIGN KEY (`ciudad_codigo`) REFERENCES `ciudad` (`codigo`) ON DELETE SET NULL;

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKshkwj12wg6vkm6iuwhvcfpct8` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
