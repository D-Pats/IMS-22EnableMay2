DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;DROP TABLE IF EXISTS `items`;



CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT NOT NULL AUTO_INCREMENT,
    `f_name` VARCHAR(40) NOT NULL,
    `l_name` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NOT NULL,
    `item_value` DECIMAL DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `orders_items` (
	`orders_items_id` INT NOT NULL AUTO_INCREMENT,
    `item_amount` INT DEFAULT NULL,
    `fk_order_id` INT NOT NULL,
    `fk_item_id` INT NOT NULL,
    PRIMARY KEY (`orders_items_id`),
    FOREIGN KEY (`fk_order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE
);