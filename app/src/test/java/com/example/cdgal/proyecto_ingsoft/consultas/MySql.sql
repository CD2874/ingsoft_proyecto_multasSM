-- -----------------------------------------------------
-- Table `datos_vehiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datos_vehiculo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `no_multa` INT UNSIGNED NOT NULL,
  `no_placa` VARCHAR(45) NOT NULL,
  `no_tarjeta_cir` VARCHAR(45) NOT NULL,
  `tipo_vehiculo` VARCHAR(45) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `no_placa_UNIQUE` (`no_placa` ASC),
  UNIQUE INDEX `no_multa_UNIQUE` (`no_multa` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `datos_infractor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datos_infractor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(60) NOT NULL,
  `apellidos` VARCHAR(60) NOT NULL,
  `conductor_ausente` VARCHAR(120) NOT NULL,
  `genero` VARCHAR(15) NOT NULL,
  `no_licencia` VARCHAR(16) NOT NULL,
  `tipo_licencia` VARCHAR(5) NOT NULL,
  `no_documento_dpi` VARCHAR(16) NOT NULL,
  `domicilio` VARCHAR(120) NOT NULL,
  `datos_vehiculo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_datos_infractor_datos_vehiculo_idx` (`datos_vehiculo_id` ASC),
  CONSTRAINT `fk_datos_infractor_datos_vehiculo`
    FOREIGN KEY (`datos_vehiculo_id`)
    REFERENCES `datos_vehiculo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `datos_infraccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datos_infraccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lugar_infraccion` TEXT(250) NOT NULL,
  `fecha_infraccion` DATE NOT NULL,
  `hora_infraccion` TIME NOT NULL,
  `datos_vehiculo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_datos_infraccion_datos_vehiculo1_idx` (`datos_vehiculo_id` ASC),
  CONSTRAINT `fk_datos_infraccion_datos_vehiculo1`
    FOREIGN KEY (`datos_vehiculo_id`)
    REFERENCES `datos_vehiculo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `articulo_infringido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `articulo_infringido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `no_articulo` INT UNSIGNED NOT NULL,
  `descripcion_articulo` TEXT(240) NOT NULL,
  `base_legal` TEXT(240) NOT NULL,
  `monto_infraccion` DECIMAL UNSIGNED NOT NULL,
  `datos_vehiculo_id` INT NOT NULL,
  `datos_infraccion_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_articulo_infringido_datos_vehiculo1_idx` (`datos_vehiculo_id` ASC),
  INDEX `fk_articulo_infringido_datos_infraccion1_idx` (`datos_infraccion_id` ASC),
  CONSTRAINT `fk_articulo_infringido_datos_vehiculo1`
    FOREIGN KEY (`datos_vehiculo_id`)
    REFERENCES `datos_vehiculo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_infringido_datos_infraccion1`
    FOREIGN KEY (`datos_infraccion_id`)
    REFERENCES `datos_infraccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `datos_agente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datos_agente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `no_identificacion` VARCHAR(60) NOT NULL,
  `firmo_agente` TINYINT(1) NOT NULL,
  `firmo_infractor` TINYINT(1) NULL,
  `nego_firmar_infractor` TINYINT(1) NULL,
  `datos_vehiculo_id` INT NOT NULL,
  `articulo_infringido_id` INT NOT NULL,
  `datos_infraccion_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_datos_agente_datos_vehiculo1_idx` (`datos_vehiculo_id` ASC),
  INDEX `fk_datos_agente_articulo_infringido1_idx` (`articulo_infringido_id` ASC),
  INDEX `fk_datos_agente_datos_infraccion1_idx` (`datos_infraccion_id` ASC),
  CONSTRAINT `fk_datos_agente_datos_vehiculo1`
    FOREIGN KEY (`datos_vehiculo_id`)
    REFERENCES `datos_vehiculo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_datos_agente_articulo_infringido1`
    FOREIGN KEY (`articulo_infringido_id`)
    REFERENCES `articulo_infringido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_datos_agente_datos_infraccion1`
    FOREIGN KEY (`datos_infraccion_id`)
    REFERENCES `datos_infraccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `observaciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `observacion` TEXT(240) NULL,
  `datos_agente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_observaciones_datos_agente1_idx` (`datos_agente_id` ASC),
  CONSTRAINT `fk_observaciones_datos_agente1`
    FOREIGN KEY (`datos_agente_id`)
    REFERENCES `datos_agente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `privilegios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `privilegios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `privilegio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `privilegio_UNIQUE` (`privilegio` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `credenciales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `credenciales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idStr` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `privilegios_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_credenciales_privilegios1_idx` (`privilegios_id` ASC),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
  UNIQUE INDEX `idStr_UNIQUE` (`idStr` ASC),
  CONSTRAINT `fk_credenciales_privilegios1`
    FOREIGN KEY (`privilegios_id`)
    REFERENCES `privilegios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `misvehiculos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `misvehiculos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `alias` VARCHAR(20) NOT NULL,
  `placa` VARCHAR(20) NOT NULL,
  `credenciales_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `placa_UNIQUE` (`placa` ASC),
  INDEX `fk_misvehiculos_credenciales1_idx` (`credenciales_id` ASC),
  CONSTRAINT `fk_misvehiculos_credenciales1`
    FOREIGN KEY (`credenciales_id`)
    REFERENCES `credenciales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;













/*************************   D   A   T   O   S   ************************************/
insert into datos_vehiculo values (1, 10, 'ABC-111', '11111111', 'Carro 1', 'Toyota 1', 'Negro 1');
insert into datos_vehiculo values (2, 20, 'ABC-222', '22222222', 'Carro 2', 'Toyota 2', 'Negro 2');
insert into datos_vehiculo values (3, 30, 'ABC-333', '22222222', 'Carro 3', 'Toyota 3', 'Negro 3');


insert into datos_infractor values(1, 'Carlos1', 'Galvez1', 'No ausente', 'Masculino', 'Numero 1', 'Tipo A', 'DPI 1', 'domicilio 1',1);
insert into datos_infractor values(2, 'Carlos2', 'Galvez2', 'Si ausente', 'Masculino', 'Numero 2', 'Tipo B', 'DPI 2', 'domicilio 2',2);
insert into datos_infractor values(3, 'Carlos3', 'Galvez3', 'No ausente', 'Masculino', 'Numero 3', 'Tipo C', 'DPI 3', 'domicilio 3',2);
insert into datos_infractor values(4, 'Carlos4', 'Galvez4', 'No ausente', 'Masculino', 'Numero 4', 'Tipo D', 'DPI 4', 'domicilio 4',3);
insert into datos_infractor values(5, 'Carlos5', 'Galvez5', 'Si ausente', 'Masculino', 'Numero 5', 'Tipo E', 'DPI 5', 'domicilio 5',3);
insert into datos_infractor values(6, 'Carlos6', 'Galvez6', 'No ausente', 'Masculino', 'Numero 6', 'Tipo F', 'DPI 6', 'domicilio 6',3);


insert into datos_infraccion values(1,'Malacatan 1', '2018-01-01', '01:01:01', 1);
insert into datos_infraccion values(2,'Malacatan 2', '2018-01-02', '02:02:02', 2);
insert into datos_infraccion values(3,'Malacatan 3', '2018-01-03', '03:03:03', 2);
insert into datos_infraccion values(4,'Malacatan 4', '2018-01-04', '04:04:04', 3);
insert into datos_infraccion values(5,'Malacatan 5', '2018-01-05', '05:05:05', 3);
insert into datos_infraccion values(6,'Malacatan 6', '2018-01-06', '06:06:06', 3);


insert into articulo_infringido values(1,'Numero 1', 'Descripcion 1', 'Base Legal 1', '1000.00', 1, 1);
insert into articulo_infringido values(2,'Numero 2', 'Descripcion 2', 'Base Legal 2', '2000.00', 2, 2);
insert into articulo_infringido values(3,'Numero 3', 'Descripcion 3', 'Base Legal 3', '3000.00', 2, 2);
insert into articulo_infringido values(4,'Numero 4', 'Descripcion 4', 'Base Legal 4', '4000.00', 3, 3);
insert into articulo_infringido values(5,'Numero 5', 'Descripcion 5', 'Base Legal 5', '5000.00', 3, 3);
insert into articulo_infringido values(6,'Numero 6', 'Descripcion 6', 'Base Legal 6', '6000.00', 3, 3);


insert into datos_agente values(1, 'Numero de id 1', 1, 1, 0, 1, 1, 1);
insert into datos_agente values(2, 'Numero de id 2', 1, 0, 1, 2, 2, 2);
insert into datos_agente values(3, 'Numero de id 3', 1, 1, 0, 2, 2, 2);
insert into datos_agente values(4, 'Numero de id 4', 1, 0, 1, 3, 3, 3);
insert into datos_agente values(5, 'Numero de id 5', 1, 1, 0, 3, 3, 3);
insert into datos_agente values(6, 'Numero de id 6', 1, 0, 1, 3, 3, 3);


insert into observaciones values(1, 'Observacion 1', 1);
insert into observaciones values(2, 'Observacion 2', 2);
insert into observaciones values(3, 'Observacion 3', 2);
insert into observaciones values(4, 'Observacion 4', 3);
insert into observaciones values(5, 'Observacion 5', 3);
insert into observaciones values(6, 'Observacion 6', 3);


insert into privilegios values(1, 'Usuario Particular');
insert into privilegios values(2, 'Usuario Agente');


insert into credenciales values(1, '1', 'Carlos Galvez', 'cg@gmail.com', '1234', 1);
insert into credenciales values(2, '2', 'Daniel Barrios', 'db@gmail.com', '1234', 2);


INSERT INTO misvehiculos VALUES(1, 'Alias 1', '1ABC-123', 1);
INSERT INTO misvehiculos VALUES(2, 'Alias 2', '2ABC-123', 2);
INSERT INTO misvehiculos VALUES(3, 'Alias 3', '3ABC-123', 2);
INSERT INTO misvehiculos VALUES(4, 'Alias 4', '4ABC-123', 1);
INSERT INTO misvehiculos VALUES(5, 'Alias 5', '5ABC-123', 1);