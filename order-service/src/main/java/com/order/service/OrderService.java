package com.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.service.bean.OrderData;
import com.order.service.bean.OrderResponseBean;
import com.order.service.repository.OrderRepository;

@Service
public class OrderService {

	private final String SUCCESS_MESSAGE = "SUCCESS";
	private final String FAILURE_MESSAGE = "FAIL";
	@Autowired
	private OrderRepository orderRepository;

	/*
	 * public Order findById(Long orderId) {
	 * 
	 * Optional<Order> orderBean = orderRepository.findById(orderId);
	 * 
	 * if (orderBean.isPresent()) { return orderBean.get(); } else { return null; }
	 * }
	 */

	@GetMapping("/order-item/orderId/{orderId}")
	public OrderData retrieveOrderItems(@PathVariable Long orderId) {

		Optional<OrderData> orderBean = orderRepository.findById(orderId);

		return orderBean.get();
	}

	public String save(OrderData orderBean) {

		OrderData savedOrder = orderRepository.save(orderBean);
		
		return savedOrder != null ? SUCCESS_MESSAGE : FAILURE_MESSAGE;
	}

	public OrderResponseBean processItemsList(List<String> avalilableItemsList, List<String> orderedItemsList) {

		OrderResponseBean responseBean = new OrderResponseBean();

		orderedItemsList.forEach(item -> {
			if (avalilableItemsList.contains(item)) {
				responseBean.getAvailableItems().add(item);
			} else {
				responseBean.getUnAvailableItems().add(item);
			}

		});
		return responseBean;
	}

}