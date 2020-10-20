package com.prakat.middleware.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	public List<Order> findByUserId(int userId);
	public Optional<Order> findByOrderReferenceId(String orderReferenceId);
}
