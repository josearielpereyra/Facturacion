-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.25-MariaDB - Source distribution
-- SO del servidor:              osx10.6
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

 /*  LA BASE DE DATOS HA SIDO MODIFICADA PARA CORREGIR INCIDENCIAS CON LOS NOMBRES DE ATRIBUTOS
 TIPO DE DATOS, NOMBRE DE ENTIDADES, ETC. 


-- Volcando estructura de base de datos para dbfacturas
CREATE DATABASE IF NOT EXISTS `dbfacturas` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbfacturas`;

-- Volcando estructura para función dbfacturas.login
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `login`(
	`nombre` VARCHAR(50),
	`pass` VARCHAR(50)


) RETURNS int(11)
BEGIN
IF EXISTS(SELECT * FROM tblusuario  WHERE Nombre=nombre AND clave=pass)THEN
 RETURN 1;
ELSE 
 RETURN 0;
END IF;

END//
DELIMITER ;

-- Volcando estructura para tabla dbfacturas.tblcliente
CREATE TABLE IF NOT EXISTS `tblcliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(28) DEFAULT NULL,
  `Cedula` varchar(11) DEFAULT NULL,
  `LimiteCredito` decimal(10,2) NOT NULL DEFAULT '0.00',
  `telefono` varchar(18) DEFAULT NULL,
  `Direccion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `Cedula` (`Cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblcliente: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblcliente` DISABLE KEYS */;
INSERT INTO `tblcliente` (`idcliente`, `Nombre`, `Apellido`, `Cedula`, `LimiteCredito`, `telefono`, `Direccion`) VALUES
	(1, 'Juan ', 'Perez', '0814-1444-1', 5000.00, '809-485-4574', 'C/ Luperon Nro. 85, Nagua, R.D');
/*!40000 ALTER TABLE `tblcliente` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblcobros
CREATE TABLE IF NOT EXISTS `tblcobros` (
  `Idcobro` int(11) NOT NULL AUTO_INCREMENT,
  `Monto` decimal(10,0) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `IdVenta` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_tblcobros_tblclientes` (`IdCliente`),
  KEY `FK_tblcobros_tblventas` (`IdVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblcobros: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblcobros` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcobros` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblcompra
CREATE TABLE IF NOT EXISTS `tblcompra` (
  `Idcompra` int(11) NOT NULL AUTO_INCREMENT,
  `IdProveedor` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `SubTotal` decimal(10,2) NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  `Pago` tinyint(1) NOT NULL,
  `Anulada` tinyint(1) NOT NULL,
  `Descuentos` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_tblcompras_tblproveedores` (`IdProveedor`),
  CONSTRAINT `FK_tblcompras_tblproveedores` FOREIGN KEY (`IdProveedor`) REFERENCES `tblproveedor` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblcompra: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblcompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcompra` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblDetalleFactura
CREATE TABLE IF NOT EXISTS `tblDetalleFactura` (
  `IdVenta` int(11) DEFAULT NULL,
  `IdProducto` int(11) DEFAULT NULL,
  `Cantidad` decimal(10,2) NOT NULL,
  `TasaImpuesto` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Precio` decimal(10,2) NOT NULL DEFAULT '0.00',
  `SubTotal` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuentos` decimal(10,2) NOT NULL DEFAULT '0.00',
  KEY `FK_tblproductoscompras_tblventas` (`IdVenta`),
  KEY `FK_tblproductoscompras_tblproductos` (`IdProducto`),
  CONSTRAINT `FK_tblproductoscompras_tblproductos` FOREIGN KEY (`IdProducto`) REFERENCES `tblproducto` (`idproducto`),
  CONSTRAINT `FK_tblproductoscompras_tblventas` FOREIGN KEY (`IdVenta`) REFERENCES `tblfactura` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblDetalleFactura: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblDetalleFactura` DISABLE KEYS */;
INSERT INTO `tblDetalleFactura` (`IdVenta`, `IdProducto`, `Cantidad`, `TasaImpuesto`, `Precio`, `SubTotal`, `Descuentos`) VALUES
	(4, 1, 5.00, 18.00, 700.00, 3500.00, 0.00),
	(5, 1, 8.00, 18.00, 700.00, 5600.00, 0.00),
	(5, 1, 1.00, 18.00, 700.00, 700.00, 0.00);
/*!40000 ALTER TABLE `tblDetalleFactura` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblDetallesCompras
CREATE TABLE IF NOT EXISTS `tblDetallesCompras` (
  `IdCompras` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` decimal(8,2) NOT NULL,
  `TadaImpuestos` decimal(8,2) NOT NULL DEFAULT '0.00',
  `Precio` decimal(10,2) NOT NULL DEFAULT '0.00',
  `SubTotal` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuentos` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Total` decimal(10,2) NOT NULL,
  KEY `FK_tblproductosventas_tblcompras` (`IdCompras`),
  KEY `FK_tblproductosventas_tblproductos` (`IdProducto`),
  CONSTRAINT `FK_tblproductosventas_tblcompras` FOREIGN KEY (`IdCompras`) REFERENCES `tblcompra` (`Id`),
  CONSTRAINT `FK_tblproductosventas_tblproductos` FOREIGN KEY (`IdProducto`) REFERENCES `tblproducto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblDetallesCompras: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblDetallesCompras` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblDetallesCompras` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblFactura
CREATE TABLE IF NOT EXISTS `tblFactura` (
  `Idfactura` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Subtotal` decimal(10,2) NOT NULL,
  `Impuesto` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuento` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Total` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Pago` tinyint(1) NOT NULL DEFAULT '0',
  `MontoPagado` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Anulada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `FK_tblventas_tblclientes` (`idcliente`),
  CONSTRAINT `FK_tblventas_tblclientes` FOREIGN KEY (`IdCliente`) REFERENCES `tblcliente` (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblFactura: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tblFactura` DISABLE KEYS */;
INSERT INTO `tblFactura` (`Id`, `idcliente`, `Fecha`, `Subtotal`, `Impuesto`, `Descuento`, `Total`, `Pago`, `MontoPagado`, `Anulada`) VALUES
	(2, 1, '2019-12-21', 1400.00, 226.80, 140.00, 1486.80, 0, 1000.00, 0),
	(3, 1, '2019-12-21', 2800.00, 453.60, 280.00, 2973.60, 0, 1000.00, 0),
	(4, 1, '2019-12-21', 3500.00, 567.00, 350.00, 3717.00, 0, 1000.00, 0),
	(5, 1, '2019-12-21', 6300.00, 1020.60, 630.00, 6690.60, 0, 1000.00, 0);
/*!40000 ALTER TABLE `tblFactura` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblpagos
CREATE TABLE IF NOT EXISTS `tblpagos` (
  `Idpagos` int(11) NOT NULL AUTO_INCREMENT,
  `Monto` decimal(10,0) NOT NULL,
  `IdProveedor` int(11) NOT NULL,
  `IdCompra` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_tblpagos_tblproveedores` (`IdProveedor`),
  KEY `FK_tblpagos_tblcompras` (`IdCompra`),
  CONSTRAINT `FK_tblpagos_tblcompras` FOREIGN KEY (`IdCompra`) REFERENCES `tblcompra` (`Id`),
  CONSTRAINT `FK_tblpagos_tblproveedores` FOREIGN KEY (`IdProveedor`) REFERENCES `tblproveedor` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblpagos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblpagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblpagos` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblProducto
CREATE TABLE IF NOT EXISTS `tblProducto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `Costo` decimal(10,2) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `existencia` decimal(10,2) NOT NULL DEFAULT '0.00',
  `impuesto` decimal(4,2) NOT NULL DEFAULT '18.00',
  PRIMARY KEY (`idproducto`),
  UNIQUE KEY `Codigo` (`Codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblProducto: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblProducto` DISABLE KEYS */;
INSERT INTO `tblProducto` (`idproducto`, `Codigo`, `descripcion`, `Costo`, `precio`, `existencia`, `impuesto`) VALUES
	(1, '1001', 'COMPUTADORA DELL', 500.00, 700.00, 5.00, 18.00);
/*!40000 ALTER TABLE `tblProducto` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblproveedor
CREATE TABLE IF NOT EXISTS `tblproveedor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Cedula` varchar(11) NOT NULL,
  `RNC` varchar(12) NOT NULL,
  `Direccion` varchar(90) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblproveedor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblproveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblproveedor` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblusuario
CREATE TABLE IF NOT EXISTS `tblusuario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Clave` varchar(9) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Nombre` (`Nombre`,`Clave`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblusuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tblusuario` DISABLE KEYS */;
INSERT INTO `tblusuario` (`Id`, `Nombre`, `Clave`) VALUES
	(1, 'admin', '1234');
/*!40000 ALTER TABLE `tblusuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
