package com.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.service.bean.OrderData;

public interface OrderRepository extends JpaRepository<OrderData, Long>{

}
