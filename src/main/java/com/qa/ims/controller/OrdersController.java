package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrdersDAO ordersDAO;
	private Utils utils;

	public OrdersController(OrdersDAO ordersDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Orders create() {
		LOGGER.info("Please enter a customer ID");
		Long customer_id = utils.getLong();
		Customer customer = new Customer(customer_id);
		Orders order = ordersDAO.create(new Orders(customer));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long order_id = utils.getLong();
		LOGGER.info("Would you like to add or remove an item from your order? enter 'add' or enter 'remove'");
		String add = utils.getString();
		if (add == "add") {
			LOGGER.info("enter item ID");
			Long new_item_id = utils.getLong();
			LOGGER.info("enter amount of items");
			Long new_item_amount = utils.getLong();
			Items item = new Items(new_item_id);
			Orders order = new Orders(order_id, item, new_item_amount);
			LOGGER.info("Order Updated");
			return order;
		} else if (add == "remove") {
			LOGGER.info("enter item ID to be removed");
			Long remove_item_id = utils.getLong();
			OrdersDAO ordersDAO2 = new OrdersDAO();
			ordersDAO2.deleteItemFromOrder(order_id, remove_item_id);
			LOGGER.info("Order Updated");
		}
		return null;

	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	public int delete() {
		LOGGER.info("Please enter order_id you would like to delete");
		Long order_id = utils.getLong();
		OrdersDAO ordersDAO2 = new OrdersDAO();
		ordersDAO2.delete(order_id);
		System.out.println("Order has been deleted");
		return 0;
	}

}
