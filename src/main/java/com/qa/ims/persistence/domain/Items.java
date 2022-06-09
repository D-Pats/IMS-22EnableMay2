package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Items {

	private Long item_id;
	private String itemName;
	private double itemValue;

	public Items(String itemName, double itemValue) {
		this.setItemName(itemName);
		this.setItemValue(itemValue);
	}

	public Items(Long item_id, String itemName, double itemValue) {
		this.setItemId(item_id);
		this.setItemName(itemName);
		this.setItemValue(itemValue);
	}

	public Long getItemId() {
		return item_id;
	}

	public void setItemId(Long item_id) {
		this.item_id = item_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemValue() {
		return itemValue;
	}

	public void setItemValue(double itemValue) {
		this.itemValue = itemValue;
	}
	
	
	
	@Override
	public String toString() {
		return "item_id:" + item_id + " itemName:" + itemName + " itemValue:" + itemValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemName, itemValue, item_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		return Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(itemValue) == Double.doubleToLongBits(other.itemValue)
				&& Objects.equals(item_id, other.item_id);
	}


}
