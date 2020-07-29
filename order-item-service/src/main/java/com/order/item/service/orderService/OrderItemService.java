package com.order.item.service.orderService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.item.service.bean.OrderItem;
import com.order.item.service.repository.OrderItemRepository;

@Service
public class OrderItemService {

	private final String SUCCESS_MESSAGE = "SUCCESS";
	private final String FAILURE_MESSAGE = "FAIL";
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public OrderItem findById(Long orderItemId) {
		
		Optional<OrderItem> orderBean = orderItemRepository.findById(orderItemId);

		if(orderBean.isPresent())
		{
			return orderBean.get();
		}else {
			return null;
		}
	}

	public String save(OrderItem item) {
		
		OrderItem savedOrderItem= orderItemRepository.save(item);
		return savedOrderItem!=null? SUCCESS_MESSAGE: FAILURE_MESSAGE;
	}
}
