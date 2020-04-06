 CREATE TABLE `Libros` (`isbn` VARCHAR(255) NOT NULL,`titulo` VARCHAR(255) NOT NULL,`autor` VARCHAR(255) NOT NULL,`precio` INT NOT NULL,`categoria` VARCHAR(255) NOT NULL,PRIMARY KEY (`isbn`))
 CREATE TABLE `Categoria` (`nombre` VARCHAR(255) NOT NULL,`descripcion` VARCHAR(255) NOT NULL,PRIMARY KEY (`nombre`));
 ALTER TABLE `Libros` ADD CONSTRAINT `Libros_fk0` FOREIGN KEY (`categoria`) REFERENCES `Categoria`(`nombre`);

 