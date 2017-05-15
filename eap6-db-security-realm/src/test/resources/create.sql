CREATE TABLE `fabio`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `idusers_UNIQUE` (`username` ASC));

CREATE TABLE `fabio`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `rolename` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `user_idx` (`username` ASC),
  CONSTRAINT `username`
  FOREIGN KEY (`username`)
  REFERENCES `fabio`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `fabio`.`users` (`username`, `password`) VALUES ('fabio', 'fabio');
INSERT INTO `fabio`.`users` (`username`, `password`) VALUES ('chiara', 'chiara');
INSERT INTO `fabio`.`roles` (`username`, `rolename`) VALUES ('fabio', 'admin');
INSERT INTO `fabio`.`roles` (`username`, `rolename`) VALUES ('fabio', 'username');
INSERT INTO `fabio`.`roles` (`username`, `rolename`) VALUES ('chiara', 'manager');

SELECT password FROM fabio.users where username='chiara';
SELECT rolename, 'Roles' FROM fabio.roles where username='chiara';