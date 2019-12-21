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
IF EXISTS(SELECT * FROM tblusuario  WHERE nombre=nombre AND clave=pass)THEN
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
DELETE FROM `tblcliente`;
/*!40000 ALTER TABLE `tblcliente` DISABLE KEYS */;
INSERT INTO `tblcliente` (`idcliente`, `Nombre`, `Apellido`, `Cedula`, `LimiteCredito`, `telefono`, `Direccion`) VALUES
	(1, 'Juan ', 'Perez', '0814-1444-1', 5000.00, '809-485-4574', 'C/ Luperon Nro. 85, Nagua, R.D');
/*!40000 ALTER TABLE `tblcliente` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblcobros
CREATE TABLE IF NOT EXISTS `tblcobros` (
  `idCobros` int(11) NOT NULL AUTO_INCREMENT,
  `Monto` decimal(10,0) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `idventa` int(11) NOT NULL,
  PRIMARY KEY (`idCobros`),
  KEY `FK_tblcobros_tblcliente` (`idcliente`),
  KEY `FK_tblcobros_tblFactura` (`idventa`),
  CONSTRAINT `FK_tblcobros_tblFactura` FOREIGN KEY (`idventa`) REFERENCES `tblFactura` (`idfactura`) ON DELETE CASCADE,
  CONSTRAINT `FK_tblcobros_tblcliente` FOREIGN KEY (`idcliente`) REFERENCES `tblcliente` (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblcobros: ~0 rows (aproximadamente)
DELETE FROM `tblcobros`;
/*!40000 ALTER TABLE `tblcobros` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcobros` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblcompra
CREATE TABLE IF NOT EXISTS `tblcompra` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `idProveedor` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `SubTotal` decimal(10,2) NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  `Pago` tinyint(1) NOT NULL,
  `Anulada` tinyint(1) NOT NULL,
  `Descuentos` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `FK_tblcompras_tblproveedores` (`idProveedor`),
  CONSTRAINT `FK_tblcompra_tblprovee` FOREIGN KEY (`idProveedor`) REFERENCES `tblproveedor` (`Idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblcompra: ~0 rows (aproximadamente)
DELETE FROM `tblcompra`;
/*!40000 ALTER TABLE `tblcompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcompra` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblDetalleFactura
CREATE TABLE IF NOT EXISTS `tblDetalleFactura` (
  `idVenta` int(11) DEFAULT NULL,
  `idProducto` int(11) DEFAULT NULL,
  `Cantidad` decimal(10,2) NOT NULL,
  `TasaImpuesto` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Precio` decimal(10,2) NOT NULL DEFAULT '0.00',
  `SubTotal` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuentos` decimal(10,2) NOT NULL DEFAULT '0.00',
  KEY `FK_tblproductoscompras_tblventas` (`idVenta`),
  KEY `FK_tblproductoscompras_tblproductos` (`idProducto`),
  CONSTRAINT `FK_tblproductoscompras_tblproductos` FOREIGN KEY (`idProducto`) REFERENCES `tblproducto` (`idproducto`) ON DELETE CASCADE,
  CONSTRAINT `FK_tblproductoscompras_tblventas` FOREIGN KEY (`idVenta`) REFERENCES `tblfactura` (`idfactura`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblDetalleFactura: ~0 rows (aproximadamente)
DELETE FROM `tblDetalleFactura`;
/*!40000 ALTER TABLE `tblDetalleFactura` DISABLE KEYS */;
INSERT INTO `tblDetalleFactura` (`idVenta`, `idProducto`, `Cantidad`, `TasaImpuesto`, `Precio`, `SubTotal`, `Descuentos`) VALUES
	(1, 1, 2.00, 18.00, 700.00, 1400.00, 0.00);
/*!40000 ALTER TABLE `tblDetalleFactura` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblDetallesCompras
CREATE TABLE IF NOT EXISTS `tblDetallesCompras` (
  `idCompras` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `Cantidad` decimal(8,2) NOT NULL,
  `TadaImpuestos` decimal(8,2) NOT NULL DEFAULT '0.00',
  `Precio` decimal(10,2) NOT NULL DEFAULT '0.00',
  `SubTotal` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuentos` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Total` decimal(10,2) NOT NULL,
  KEY `FK_tblproductosventas_tblcompras` (`idCompras`),
  KEY `FK_tblproductosventas_tblproductos` (`idProducto`),
  CONSTRAINT `FK_tblproductosventas_tblcompras` FOREIGN KEY (`idCompras`) REFERENCES `tblcompra` (`idCompra`),
  CONSTRAINT `FK_tblproductosventas_tblproductos` FOREIGN KEY (`idProducto`) REFERENCES `tblproducto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblDetallesCompras: ~0 rows (aproximadamente)
DELETE FROM `tblDetallesCompras`;
/*!40000 ALTER TABLE `tblDetallesCompras` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblDetallesCompras` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblFactura
CREATE TABLE IF NOT EXISTS `tblFactura` (
  `idfactura` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Subtotal` decimal(10,2) NOT NULL,
  `Impuesto` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuento` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Total` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Pago` tinyint(1) NOT NULL DEFAULT '0',
  `MontoPagado` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Anulada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idfactura`),
  KEY `FK_tblventas_tblclientes` (`idcliente`),
  CONSTRAINT `FK_tblventas_tblclientes` FOREIGN KEY (`idcliente`) REFERENCES `tblcliente` (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblFactura: ~0 rows (aproximadamente)
DELETE FROM `tblFactura`;
/*!40000 ALTER TABLE `tblFactura` DISABLE KEYS */;
INSERT INTO `tblFactura` (`idfactura`, `idcliente`, `Fecha`, `Subtotal`, `Impuesto`, `Descuento`, `Total`, `Pago`, `MontoPagado`, `Anulada`) VALUES
	(1, 1, '2019-12-21', 1400.00, 226.80, 140.00, 1486.80, 0, 0.00, 0);
/*!40000 ALTER TABLE `tblFactura` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblpagos
CREATE TABLE IF NOT EXISTS `tblpagos` (
  `idPagos` int(11) NOT NULL AUTO_INCREMENT,
  `Monto` decimal(10,0) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  `idCompra` int(11) NOT NULL,
  PRIMARY KEY (`idPagos`),
  KEY `FK_tblpagos_tblproveedores` (`idProveedor`),
  KEY `FK_tblpagos_tblcompras` (`idCompra`),
  CONSTRAINT `FK_tblpagos_tblcompras` FOREIGN KEY (`idCompra`) REFERENCES `tblcompra` (`idCompra`),
  CONSTRAINT `FK_tblpagos_tblproveedores` FOREIGN KEY (`idProveedor`) REFERENCES `tblproveedor` (`Idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblpagos: ~0 rows (aproximadamente)
DELETE FROM `tblpagos`;
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
DELETE FROM `tblProducto`;
/*!40000 ALTER TABLE `tblProducto` DISABLE KEYS */;
INSERT INTO `tblProducto` (`idproducto`, `Codigo`, `descripcion`, `Costo`, `precio`, `existencia`, `impuesto`) VALUES
	(1, '1001', 'COMPUTADORA DELL', 500.00, 700.00, 3.00, 18.00);
/*!40000 ALTER TABLE `tblProducto` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblproveedor
CREATE TABLE IF NOT EXISTS `tblproveedor` (
  `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Cedula` varchar(11) NOT NULL,
  `RNC` varchar(12) NOT NULL,
  `Direccion` varchar(90) NOT NULL,
  PRIMARY KEY (`idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Volcando datos para la tabla dbfacturas.tblproveedor: ~0 rows (aproximadamente)
DELETE FROM `tblproveedor`;
/*!40000 ALTER TABLE `tblproveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblproveedor` ENABLE KEYS */;

-- Volcando estructura para tabla dbfacturas.tblusuario
CREATE TABLE IF NOT EXISTS `tblusuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `clave` varchar(9) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `Nombre` (`nombre`,`clave`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbfacturas.tblusuario: ~0 rows (aproximadamente)
DELETE FROM `tblusuario`;
/*!40000 ALTER TABLE `tblusuario` DISABLE KEYS */;
INSERT INTO `tblusuario` (`idusuario`, `nombre`, `clave`) VALUES
	(1, 'admin', '1234');
/*!40000 ALTER TABLE `tblusuario` ENABLE KEYS */;

-- Volcando estructura para disparador dbfacturas.tblDetalleFactura_after_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tblDetalleFactura_after_insert` AFTER INSERT ON `tblDetalleFactura` FOR EACH ROW BEGIN

IF NEW.Cantidad>0 THEN
  UPDATE tblproducto SET existencia=existencia-new.Cantidad WHERE idproducto=NEW.idproducto;
END IF;


END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
