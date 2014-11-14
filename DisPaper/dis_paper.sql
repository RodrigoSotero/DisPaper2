-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 24-04-2014 a las 17:19:41
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `dis_paper`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacenaje`
--

CREATE TABLE IF NOT EXISTS `almacenaje` (
  `id_almacenaje` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_almacenaje`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `almacenaje`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE IF NOT EXISTS `cargo` (
  `id_cargo` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `cargo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_cargo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`id_cargo`, `cargo`) VALUES
(1, 'Master'),
(2, 'Junior'),
(3, 'Kid');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase_papel`
--

CREATE TABLE IF NOT EXISTS `clase_papel` (
  `id_clase_papel` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Clase` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_clase_papel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `clase_papel`
--

INSERT INTO `clase_papel` (`id_clase_papel`, `Clase`) VALUES
(1, 'CUBIERTO'),
(2, 'NO RECUBIERTO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `id_clientes` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `RFC` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_clientes`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_clientes`, `Nombre`, `Direccion`, `Telefono`, `RFC`) VALUES
(1, 'yopo', 'nueva', '555444', 'yopo32541');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `color`
--

CREATE TABLE IF NOT EXISTS `color` (
  `id_color` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Color` varchar(30) DEFAULT NULL,
  `Tipo_Papel` int(5) NOT NULL,
  PRIMARY KEY (`id_color`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Volcar la base de datos para la tabla `color`
--

INSERT INTO `color` (`id_color`, `Color`, `Tipo_Papel`) VALUES
(1, 'Blanco', 2),
(2, 'Ahuesado', 2),
(3, 'Brillante', 1),
(4, 'Mate', 1),
(5, 'Semi-Mate', 1),
(6, 'azul', 1),
(7, 'verde', 1),
(8, 'verde', 1),
(9, 'adsafsdfsd', 1),
(10, 'amazul', 1),
(11, 'amarillo', 1),
(12, 'Crema', 2),
(13, 'rosa', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleentrada`
--

CREATE TABLE IF NOT EXISTS `detalleentrada` (
  `id_entrada` varchar(15) NOT NULL,
  `Clave_Papel` varchar(50) NOT NULL,
  `total_entrada` int(10) NOT NULL,
  `cantidad_entrada` int(11) NOT NULL,
  `Costo` varchar(30) NOT NULL,
  `total_costo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `detalleentrada`
--

INSERT INTO `detalleentrada` (`id_entrada`, `Clave_Papel`, `total_entrada`, `cantidad_entrada`, `Costo`, `total_costo`) VALUES
('10', '01-3-BLA-1-8768-IEXSA-BIOPAPEL', 10000, 10, '.065', '650.0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallesalidab`
--

CREATE TABLE IF NOT EXISTS `detallesalidab` (
  `id_salida` varchar(15) NOT NULL,
  `Clave_Papel` varchar(50) NOT NULL,
  `total_hojas` int(10) NOT NULL,
  `contenido` int(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `resto` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `detallesalidab`
--

INSERT INTO `detallesalidab` (`id_salida`, `Clave_Papel`, `total_hojas`, `contenido`, `cantidad`, `resto`) VALUES
('1', '01-3-BLA-1-8768-IEXSA-BIOPAPEL', 1000, 1000, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallesalidah`
--

CREATE TABLE IF NOT EXISTS `detallesalidah` (
  `id_salida` varchar(15) NOT NULL,
  `Clave_Papel` varchar(50) NOT NULL,
  `total_hojas` int(10) NOT NULL,
  `contenido` int(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `resto` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `detallesalidah`
--

INSERT INTO `detallesalidah` (`id_salida`, `Clave_Papel`, `total_hojas`, `contenido`, `cantidad`, `resto`) VALUES
('1', '02-4-BRI-1-6587230-IEXSA', 10, 10, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documento_entrada`
--

CREATE TABLE IF NOT EXISTS `documento_entrada` (
  `id_documento_en` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Documento` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_documento_en`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `documento_entrada`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

CREATE TABLE IF NOT EXISTS `entrada` (
  `id_entrada` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `TURNO1` varchar(5) NOT NULL,
  `TURNO2` varchar(5) NOT NULL,
  `TURNO3` varchar(5) NOT NULL,
  `orden_produccion` varchar(45) NOT NULL,
  `orden_compra` varchar(45) NOT NULL,
  `id_propietario` int(5) unsigned NOT NULL,
  `id_proveedores` int(5) unsigned NOT NULL,
  `id_responsable` int(5) unsigned NOT NULL,
  `Fecha` varchar(15) DEFAULT NULL,
  `tipo_entrada` int(5) NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  PRIMARY KEY (`id_entrada`),
  KEY `ENTRADA_FKIndex3` (`id_responsable`),
  KEY `ENTRADA_FKIndex6` (`id_proveedores`),
  KEY `ENTRADA_FKIndex7` (`id_propietario`),
  KEY `ENTRADA_FKIndex8` (`TURNO1`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcar la base de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`id_entrada`, `TURNO1`, `TURNO2`, `TURNO3`, `orden_produccion`, `orden_compra`, `id_propietario`, `id_proveedores`, `id_responsable`, `Fecha`, `tipo_entrada`, `observaciones`) VALUES
(1, 't1', '', '', '02', '025', 1, 1, 1, '05-04-2014', 1, 'dfasdfasdf'),
(2, '', 't2', '', 'asdfadf', 'asdfadf', 2, 1, 1, '05-04-2014', 2, 'fasdfasdgdhdfgsdfg'),
(3, 't1', '', '', 'asdfad', 'asdfa', 1, 1, 1, '06-04-2014', 1, 'adfasdfasdf'),
(4, 't1', '', '', 'asdfad', 'asdfaf', 1, 1, 1, '06-04-2014', 1, 'ASDFASDFASDF'),
(5, 't1', '', '', 'op-123', '254/872', 1, 1, 1, '05-04-2014', 1, 'hola mundo'),
(6, 't1', '', '', 'opdas', 'asdf', 2, 1, 1, '05-04-2014', 2, 'dsfaf'),
(7, 't1', '', '', 'zsdfsdf', 'afad', 3, 1, 1, '06-04-2014', 2, 'sdfasdfd'),
(8, 't1', '', '', 'SDFASD', 'ASDFA', 4, 2, 1, '06-04-2014', 2, 'DFADFADF'),
(9, 't1', '', '', 'sadfad', 'sdfaf', 1, 1, 1, '06-04-2014', 2, 'sadffad'),
(10, 't1', '', '', 'dasdfasdf', 'asdfasdf', 1, 1, 1, '06-04-2014', 1, 'askdjfhasldjfh');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE IF NOT EXISTS `inventario` (
  `id_inventario` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `clavePapel` varchar(45) NOT NULL,
  `id_propietario` int(5) NOT NULL,
  `cantidad` int(10) unsigned DEFAULT NULL,
  `presentacion` int(5) NOT NULL,
  PRIMARY KEY (`id_inventario`,`clavePapel`),
  KEY `INVENTARIO_FKIndex2` (`clavePapel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id_inventario`, `clavePapel`, `id_propietario`, `cantidad`, `presentacion`) VALUES
(1, '01-3-BLA-1-8768-IEXSA-BIOPAPEL', 1, 8000, 8),
(2, '01-3-BLA-1-8768-CONALITEG-BIOPAPEL', 2, 1000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maquinas`
--

CREATE TABLE IF NOT EXISTS `maquinas` (
  `id_maquina` int(5) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `tipo_maquina` int(5) NOT NULL,
  PRIMARY KEY (`id_maquina`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcar la base de datos para la tabla `maquinas`
--

INSERT INTO `maquinas` (`id_maquina`, `nombre`, `tipo_maquina`) VALUES
(1, 'ROTATIVA 1', 0),
(2, 'ROTATIVA 2', 0),
(3, 'ROTATIVA 3', 0),
(4, 'KOMORI', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

CREATE TABLE IF NOT EXISTS `marca` (
  `id_marca` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `marca` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `marca`
--

INSERT INTO `marca` (`id_marca`, `marca`) VALUES
(1, 'SCRIBE'),
(2, 'PATITO'),
(3, 'BIOPAPEL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nombre_papel`
--

CREATE TABLE IF NOT EXISTS `nombre_papel` (
  `id_nombre` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `id_clase_papel` int(5) NOT NULL,
  `id_tipo_papel` int(5) NOT NULL,
  PRIMARY KEY (`id_nombre`),
  KEY `id_clase_papel` (`id_clase_papel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Volcar la base de datos para la tabla `nombre_papel`
--

INSERT INTO `nombre_papel` (`id_nombre`, `nombre`, `id_clase_papel`, `id_tipo_papel`) VALUES
(1, 'Diario', 2, 1),
(2, 'Biblia', 2, 1),
(3, 'Bond', 2, 1),
(4, 'Couche', 1, 1),
(5, 'SuperCal', 1, 2),
(6, 'LWC', 1, 2),
(7, 'AeroCopy', 1, 2),
(8, 'Cartulina Bristol', 1, 0),
(9, 'Caple', 1, 0),
(10, 'Sulfatada', 1, 0),
(11, 'Adhesivos Couche', 1, 0),
(12, 'Polypap', 1, 0),
(13, 'Cartulina Granito', 1, 0),
(14, 'Cartulina Opalina', 1, 0),
(15, 'Kromecote', 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenprod`
--

CREATE TABLE IF NOT EXISTS `ordenprod` (
  `id_op` varchar(10) NOT NULL,
  `merma` varchar(45) NOT NULL,
  `fecha_inicial` varchar(45) NOT NULL,
  `hora_inicial` varchar(45) NOT NULL,
  `fecha_final` varchar(20) NOT NULL,
  `hora_final` varchar(20) NOT NULL,
  `estandar_produccion` varchar(20) NOT NULL,
  `tiempo_real` varchar(20) NOT NULL,
  `total_pliego` varchar(20) NOT NULL,
  `contador_rotatvivas` int(20) NOT NULL,
  PRIMARY KEY (`id_op`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ordenprod`
--

INSERT INTO `ordenprod` (`id_op`, `merma`, `fecha_inicial`, `hora_inicial`, `fecha_final`, `hora_final`, `estandar_produccion`, `tiempo_real`, `total_pliego`, `contador_rotatvivas`) VALUES
('asda/9874', '20.00 %', '05-04-2014', '14:00', '06-04-2014', '20:00', '.2564', '06:00', '58', 58),
('asda/9875', '20.20 %', '06-04-2014', '14:00', '13-04-2014', '12:00', '.25', '98', '25', 87),
('dfsdd', '', 'kjñlkj', 'lñkjlñkj', 'ñlkjlñj', 'lñj', 'ñlkj', 'lñj', 'lkj', 36);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `papel`
--

CREATE TABLE IF NOT EXISTS `papel` (
  `id_papel` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Clave` varchar(45) NOT NULL,
  `MARCA_id_marca` int(5) unsigned NOT NULL,
  `NOMBRE_PAPEL_id_nombre` int(5) unsigned NOT NULL,
  `COLOR_id_color` int(5) unsigned NOT NULL,
  `TIPO_PAPEL_id_tipo_papel` int(5) unsigned NOT NULL,
  `CLASE_PAPEL_id_clase_papel` int(5) unsigned NOT NULL,
  `caras` int(5) NOT NULL,
  `ancho` int(5) unsigned DEFAULT NULL,
  `alto` int(5) unsigned DEFAULT NULL,
  `grams` varchar(5) NOT NULL,
  PRIMARY KEY (`id_papel`),
  KEY `PAPEL_FKIndex1` (`CLASE_PAPEL_id_clase_papel`),
  KEY `PAPEL_FKIndex2` (`TIPO_PAPEL_id_tipo_papel`),
  KEY `PAPEL_FKIndex4` (`COLOR_id_color`),
  KEY `PAPEL_FKIndex5` (`NOMBRE_PAPEL_id_nombre`),
  KEY `PAPEL_FKIndex6` (`MARCA_id_marca`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `papel`
--

INSERT INTO `papel` (`id_papel`, `Clave`, `MARCA_id_marca`, `NOMBRE_PAPEL_id_nombre`, `COLOR_id_color`, `TIPO_PAPEL_id_tipo_papel`, `CLASE_PAPEL_id_clase_papel`, `caras`, `ancho`, `alto`, `grams`) VALUES
(1, '01-3-BLA-1-8768-IEXSA-BIOPAPEL', 3, 3, 1, 1, 2, 1, 87, 0, '68'),
(2, '01-3-BLA-1-8768-CONALITEG-BIOPAPEL', 3, 3, 1, 1, 2, 1, 87, 0, '68');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietarios`
--

CREATE TABLE IF NOT EXISTS `propietarios` (
  `id_propietarios` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_propietarios`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcar la base de datos para la tabla `propietarios`
--

INSERT INTO `propietarios` (`id_propietarios`, `nombre`) VALUES
(1, 'IEXSA'),
(2, 'CONALITEG'),
(3, 'IRVING'),
(4, 'ESFINGE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE IF NOT EXISTS `proveedores` (
  `id_provedores` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `RFC` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_provedores`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id_provedores`, `Nombre`, `Direccion`, `Telefono`, `RFC`) VALUES
(1, 'PAPELERIAS LOZANO', 'CONOCIDA', '55555', '5874698'),
(2, 'PAPELERIAS LEON', 'DESCONOCIDA', '888888', '965412');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsable`
--

CREATE TABLE IF NOT EXISTS `responsable` (
  `id_responsable` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `CARGO_id_cargo` int(5) unsigned NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `pswd` varchar(45) DEFAULT NULL,
  `Activo` int(2) NOT NULL,
  PRIMARY KEY (`id_responsable`),
  UNIQUE KEY `usuario` (`usuario`),
  KEY `RESPONSABLE_FKIndex1` (`CARGO_id_cargo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Volcar la base de datos para la tabla `responsable`
--

INSERT INTO `responsable` (`id_responsable`, `CARGO_id_cargo`, `nombre`, `usuario`, `pswd`, `Activo`) VALUES
(1, 1, 'Jhafet', 'Jhafet', '789', 1),
(2, 2, 'rodrigo', 'yopo', '231', 1),
(3, 3, 'Irving', 'ioza', '345', 0),
(4, 2, 'jaime', 'jimmy', '456', 0),
(15, 1, 'root', 'root', 'root', 1),
(17, 1, 'juan', 'juan', 'juan', 1),
(18, 1, 'ola', 'ola', 'ola', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidab`
--

CREATE TABLE IF NOT EXISTS `salidab` (
  `id_salida` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `folio` varchar(10) NOT NULL,
  `TURNO1` varchar(5) NOT NULL,
  `TURNO2` varchar(5) NOT NULL,
  `TURNO3` varchar(5) NOT NULL,
  `orden_produccion` varchar(45) NOT NULL,
  `estandar_produccion` varchar(45) NOT NULL,
  `id_cliente` int(5) unsigned NOT NULL,
  `id_propietario` int(5) unsigned NOT NULL,
  `id_maquina` int(5) unsigned NOT NULL,
  `Fecha` varchar(15) DEFAULT NULL,
  `titulo` varchar(100) NOT NULL,
  `id_responsable` int(5) NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  PRIMARY KEY (`id_salida`),
  KEY `ENTRADA_FKIndex3` (`id_maquina`),
  KEY `ENTRADA_FKIndex6` (`id_propietario`),
  KEY `ENTRADA_FKIndex7` (`id_cliente`),
  KEY `ENTRADA_FKIndex8` (`TURNO1`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `salidab`
--

INSERT INTO `salidab` (`id_salida`, `folio`, `TURNO1`, `TURNO2`, `TURNO3`, `orden_produccion`, `estandar_produccion`, `id_cliente`, `id_propietario`, `id_maquina`, `Fecha`, `titulo`, `id_responsable`, `observaciones`) VALUES
(1, 'BOBSAL-1', 't1', '', '', 'sdfsafd', 'asdfadfa', 1, 1, 1, '06-04-2014', 'dfadsfasdf', 1, 'fdasdfasdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidah`
--

CREATE TABLE IF NOT EXISTS `salidah` (
  `id_salida` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `folio` varchar(10) NOT NULL,
  `TURNO1` varchar(5) NOT NULL,
  `TURNO2` varchar(5) NOT NULL,
  `TURNO3` varchar(5) NOT NULL,
  `orden_produccion` varchar(45) NOT NULL,
  `estandar_produccion` varchar(45) NOT NULL,
  `id_cliente` int(5) unsigned NOT NULL,
  `id_propietario` int(5) unsigned NOT NULL,
  `id_maquina` int(5) unsigned NOT NULL,
  `Fecha` varchar(15) DEFAULT NULL,
  `titulo` varchar(100) NOT NULL,
  `id_responsable` int(5) NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  PRIMARY KEY (`id_salida`),
  KEY `ENTRADA_FKIndex3` (`id_maquina`),
  KEY `ENTRADA_FKIndex6` (`id_propietario`),
  KEY `ENTRADA_FKIndex7` (`id_cliente`),
  KEY `ENTRADA_FKIndex8` (`TURNO1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `salidah`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_entrada`
--

CREATE TABLE IF NOT EXISTS `tipo_entrada` (
  `id_tipo_en` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Tipo_Entrada` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_en`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `tipo_entrada`
--

INSERT INTO `tipo_entrada` (`id_tipo_en`, `Tipo_Entrada`) VALUES
(1, 'compra'),
(2, 'del cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_papel`
--

CREATE TABLE IF NOT EXISTS `tipo_papel` (
  `id_tipo_papel` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_papel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `tipo_papel`
--

INSERT INTO `tipo_papel` (`id_tipo_papel`, `Tipo`) VALUES
(1, 'Bobina'),
(2, 'Papel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_salida`
--

CREATE TABLE IF NOT EXISTS `tipo_salida` (
  `id_tipo_sal` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Tipo_Salida` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_sal`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `tipo_salida`
--

INSERT INTO `tipo_salida` (`id_tipo_sal`, `Tipo_Salida`) VALUES
(1, 'venta'),
(2, 'maquilar');
