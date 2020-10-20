package com.prakat.middleware.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.MenuType;

@Repository
public interface MenuTypeRepository extends JpaRepository<MenuType, Integer>{
	public Page<MenuType> findByRestaurantId(int restaurantId,  Pageable pageable);
	public Page<MenuType> findByRestaurantIdAndServiceTypeIgnoreCase(int restaurantId, String serviceType,  Pageable pageable);
}
