package com.order.service.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.OrderItemServiceProxy;
import com.order.service.OrderService;
import com.order.service.bean.OrderData;
import com.order.service.exceptionhandler.OrderNotFoundException;

@RestController
public class OrderItemController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public OrderItemServiceProxy orderItemServiceProxy;

	@Autowired
	public OrderService orderService;

	@GetMapping("/order/id/{orderId}")
	public OrderData getOrderDetails(@PathVariable Long orderId) {

		OrderData response = orderService.retrieveOrderItems(orderId);

		if (response == null)
			throw new OrderNotFoundException("Order Id: " + orderId);

		logger.info("{}", response);

		return response;
	}

	@PostMapping("/createOrder")
	public String createOrder(@Valid @RequestBody OrderData orderBean) {
		
		List<String> unAvailableOrderItems = orderItemServiceProxy.validateOrderItem(orderBean.getOrderItems());

		if (unAvailableOrderItems.size() == 0) {
			orderBean.setTotal(orderBean.getOrderItems().size());
			orderBean.setOrderDate(null);
			return orderService.save(orderBean);
		}
		return String.join(",", unAvailableOrderItems) + ": products are not available";
	}

}
