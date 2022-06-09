package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		String itemName = resultSet.getString("itemName");
		double itemValue = resultSet.getDouble("itemValue");
		Long id = resultSet.getLong("customer_id");
		String firstName = resultSet.getString("f_name");
		String surname = resultSet.getString("l_name");
		Customer customer = new Customer(id, firstName, surname);
		Items item = new Items(item_id, itemName, itemValue);
		return new Orders(order_id, customer, item);
	}

	/**
	 * Reads all Orders from the database
	 * 
	 * @return A list of Orders
	 */
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM orders o LEFT OUTER JOIN customers c ON c.customer_id = o.fk_customer_id LEFT OUTER JOIN orders_items oi ON o.order_id = oi.fk_order_id LEFT OUTER JOIN items i on i.item_id = oi.fk_item_id ORDER BY o.order_id");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a order in the database
	 * 
	 * @param order - takes in a customer object. id will be ignored
	 */
	@Override
	public Orders create(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (fk_customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomer().getId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orders ordersItemsFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("orders_items_id");
		Long itemId = resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		double itemCost = resultSet.getDouble("item_value");
		Items item = new Items(itemId, itemName, itemCost);
		Orders order = new Orders(orderId, item);
		return order;
	}

	@Override
	public Orders read(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM orders_items LEFT OUTER JOIN items ON items.item_id = orders_items.fk_item_id WHERE fk_order_id = ?");) {
			statement.setLong(1, order_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return ordersItemsFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 * 
	 * @param order - takes in a order object, the id field will be used to update
	 *              that order in the database
	 * @return
	 */
	@Override
	public Orders update(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE orders SET fk_item_id = ?, fk_item_name = ?, fk_item_value= ? WHERE order_id = ?");) {
			statement.setLong(1, order.getItem().getItemId());
			statement.setString(2, order.getItem().getItemName());
			statement.setDouble(3, order.getItem().getItemValue());
			statement.setDouble(4, order.getOrder_id());
			statement.executeUpdate();
			return read(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orders addItemToOrder(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orders_items (fk_item_id, fk_order_id) VALUES ((SELECT item_id FROM items WHERE item_id = ?), (SELECT order_id FROM orders Where order_id = ?))");) {
			statement.setLong(1, order.getItem().getItemId());
			statement.setLong(2, order.getOrder_id());
			statement.executeUpdate();
			return read(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public int deleteItemFromOrder(Long order_id, Long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE fk_order_id = ? && fk_item_id = ?");) {
			statement.setLong(1, order_id);
			statement.setLong(2, item_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * Deletes an order in the database
	 * 
	 * @param order_id - id of the order
	 */
	public int delete(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE fk_order_id = ?");) {
			statement.setLong(1, order_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}


}
