package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Orders {

	private Long order_id;
	private Long customer_id;
	private Long item_id;
	private Customer customer;
	private Items item;

	public Orders() {
		super();
	}

	public Orders(Long order_id) {
		super();
		this.order_id = order_id;
	}

	public Orders(Long order_id, Long customer_id) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
	}

	public Orders(Long order_id, Long item_id, Customer customer) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.customer = customer;
	}

	public Orders(Long order_id, Long item_id, Customer customer, Items item) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.customer = customer;
		this.item = item;
	}

	public Orders(Long order_id, Customer customer, Items item) {
		super();
		this.order_id = order_id;
		this.customer = customer;
		this.item = item;
	}

	public Orders(Long order_id, Items item) {
		super();
		this.order_id = order_id;
		this.item = item;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, customer_id, item, item_id, order_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(customer_id, other.customer_id)
				&& Objects.equals(item, other.item) && Objects.equals(item_id, other.item_id)
				&& Objects.equals(order_id, other.order_id);
	}

}
