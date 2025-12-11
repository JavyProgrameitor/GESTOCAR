DROP DATABASE IF EXISTS 'gestocardb';
DROP USER IF EXISTS 'gestocar'@'localhost';
 
CREATE DATABASE  IF NOT EXISTS `gestocardb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestocardb`;

-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: gestocardb
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fotos`
--

DROP TABLE IF EXISTS `fotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos` (
  `idfotos` smallint NOT NULL AUTO_INCREMENT,
  `idvehiculo` smallint NOT NULL,
  `foto` varchar(15) NOT NULL,
  PRIMARY KEY (`idfotos`),
  KEY `fk_fotos_vehiculos` (`idvehiculo`),
  CONSTRAINT `fk_fotos_vehiculos` FOREIGN KEY (`idvehiculo`) REFERENCES `vehiculos` (`idvehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `idgasto` smallint NOT NULL AUTO_INCREMENT,
  `idvehiculo` smallint NOT NULL,
  `concepto` varchar(40) NOT NULL,
  `fechagasto` date NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `importe` decimal(6,2) NOT NULL,
  `establecimiento` varchar(100) DEFAULT NULL,
  `kilometros` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`idgasto`),
  KEY `fk_gastos_vehiculos` (`idvehiculo`),
  CONSTRAINT `fk_gastos_vehiculos` FOREIGN KEY (`idvehiculo`) REFERENCES `vehiculos` (`idvehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuarios` smallint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `campobaja` char(1) NOT NULL DEFAULT 'F',
  PRIMARY KEY (`idusuarios`),
  UNIQUE KEY `EMAIL_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','gestor','admin@iesalbarregas.es','21232f297a57a5a743894a0e4a801fc3','9292029B','F');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `idvehiculo` smallint NOT NULL AUTO_INCREMENT,
  `idusuario` smallint NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `motor` set ('gasolina','gasoil','electrico') NOT NULL DEFAULT 'gasolina',
  `matricula` varchar(8) NOT NULL,
  `cilindrada` varchar(5)  NOT NULL,
  `caballos` varchar(4)  DEFAULT NULL,
  `color` varchar(7)  DEFAULT NULL,
  `fechacompra` date NOT NULL,
  `fechaventa` date DEFAULT NULL,
  `preciocompra` decimal(7,2) NOT NULL,
  `precioventa` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`idvehiculo`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`),
  KEY `fk_vehiculos_usuarios` (`idusuario`),
  CONSTRAINT `fk_vehiculos_usuarios` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Crear el usuario gestocar con la contraseña Java*2024
CREATE USER 'gestocar'@'localhost' IDENTIFIED BY 'Java*2024';
-- Otorgar todos los privilegios en la base de datos gestocardb al usuario gestocar
GRANT ALL PRIVILEGES ON gestocardb.* TO 'gestocar'@'localhost';
-- Aplicar los cambios de privilegios
FLUSH PRIVILEGES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-03  2:30:39
