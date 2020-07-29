package com.order.service.bean;

import java.util.List;

public class OrderResponseBean {
	
	private Long orderId;
	private List<String> availableItems;
	private List<String> unAvailableItems;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List<String> getAvailableItems() {
		return availableItems;
	}
	public void setAvailableItems(List<String> availableItems) {
		this.availableItems = availableItems;
	}
	public List<String> getUnAvailableItems() {
		return unAvailableItems;
	}
	public void setUnAvailableItems(List<String> unAvailableItems) {
		this.unAvailableItems = unAvailableItems;
	}
	
	
}
