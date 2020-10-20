package com.prakat.middleware.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.SuggestedDish;

@Repository
public interface SuggestedDishRepository extends JpaRepository<SuggestedDish, Integer>{

	public Optional<SuggestedDish> findByDishNameId(Integer id);
	public List<SuggestedDish> findByRestaurantId(Integer restaurantId);
}
