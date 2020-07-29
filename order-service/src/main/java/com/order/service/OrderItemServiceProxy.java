package com.order.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.service.bean.OrderItem;

@FeignClient(name = "order-item-service", url = "localhost:8001")
public interface OrderItemServiceProxy {
	
	@PostMapping("/validate-order-item")
	public List<String> validateOrderItem(List<OrderItem> orderItems);
}
