package com.prakat.middleware.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.PopularDish;

@Repository
public interface PopularDishRepository extends JpaRepository<PopularDish, Integer>{

	public Optional<PopularDish> findByDishNameId(Integer id);
	public List<PopularDish> findByRestaurantId(Integer restaurantId);
}
