package com.order.item.service.controller;

import java.util.ArrayList;
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

import com.order.item.service.bean.OrderItem;
import com.order.item.service.exceptionhandler.OrderItemNotFoundException;
import com.order.item.service.orderService.OrderItemService;

@RestController
public class OrderItemServiceController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderItemService orderService;

	@GetMapping("/retrive-order-items/itemId/{itemId}")
	public OrderItem retrieveOrderItems(@PathVariable Long itemId) {

		OrderItem response = orderService.findById(itemId);

		if (response == null)
			throw new OrderItemNotFoundException("Order Id: " + itemId);

		logger.info("{}", response);

		return response;
	}

	@PostMapping("/createOrderItem")
	public String createOrderItem(@Valid @RequestBody OrderItem orderItem) {

		String response = orderService.save(orderItem);
		return response;
	}

	@PostMapping("/validate-order-item")
	public List<String> validateOrderItem(@Valid @RequestBody List<OrderItem> orderItems) {

		List<String> unAvailableItems=new ArrayList<>();
		orderItems.forEach(item -> {

			OrderItem orderItem = orderService.findById(item.getItemId());
			if(orderItem==null) {
				unAvailableItems.add(item.getProductCode());
			}else if(item.getQuantity() > orderItem.getQuantity()){
				unAvailableItems.add(item.getProductCode());
			}

		});

		return unAvailableItems;
	}

}
