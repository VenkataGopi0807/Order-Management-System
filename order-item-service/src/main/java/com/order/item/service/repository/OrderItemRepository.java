package com.order.item.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.item.service.bean.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
