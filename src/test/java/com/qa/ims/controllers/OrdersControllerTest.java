package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrdersDAO dao;

	@InjectMocks
	private OrdersController controller;

//	@Test
//	public void testCreate() {
//		final long ORDER_ID = 1;
//		final long CUSTOMER_ID = 1; 
//		final Orders created = new Orders(ORDER_ID, CUSTOMER_ID);
//
//		Mockito.when(utils.getLong()).thenReturn(ORDER_ID, CUSTOMER_ID);
//		Mockito.when(dao.create(created)).thenReturn(created);
//
//		assertEquals(created, controller.create());
//
//		Mockito.verify(utils, Mockito.times(2)).getLong();
//		Mockito.verify(dao, Mockito.times(1)).create(created);
//	}

	@Test
	public void testReadAll() {
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders(1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

//	@Test
//	public void testUpdate() {
//		Orders updated = new Orders(1L, 1L);
//
//		Mockito.when(this.utils.getLong()).thenReturn(1L);
//		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrder_id(), updated.getCustomer_id());
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
//
//		assertEquals(updated, this.controller.update());
//
//		Mockito.verify(this.utils, Mockito.times(3)).getLong();
//		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
//	}
//
//	@Test
//	public void testDelete() {
//		final long ID = 1L;
//
//		Mockito.when(utils.getLong()).thenReturn(ID);
//		Mockito.when(dao.delete(ID)).thenReturn(1);
//
//		assertEquals(1L, this.controller.delete());
//
//		Mockito.verify(utils, Mockito.times(1)).getLong();
//		Mockito.verify(dao, Mockito.times(1)).delete(ID);
//	}

}
