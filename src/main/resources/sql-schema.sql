drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customer_id` INT NOT NULL AUTO_INCREMENT,
    `f_name` VARCHAR(40) NOT NULL,
    `l_name` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`item_id` INT NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NOT NULL,
    `item_value` DECIMAL(4,2) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`order_id` INT NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `ims`.`customers` (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders_items` (
	`orders_items_id` INT NOT NULL AUTO_INCREMENT,
    `fk_order_id` INT NOT NULL,
    `fk_item_id` INT NOT NULL,
    PRIMARY KEY (`orders_items_id`),
    FOREIGN KEY (`fk_order_id`) REFERENCES `ims`.`orders` (`order_id`),
    FOREIGN KEY (`fk_item_id`) REFERENCES `ims`.`items` (`item_id`)
);