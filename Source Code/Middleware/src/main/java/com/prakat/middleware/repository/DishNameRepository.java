package com.prakat.middleware.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.DishName;

@Repository
public interface DishNameRepository extends JpaRepository<DishName, Integer>{
	public Page<DishName> findByDishTypeId(int dishTypeId, Pageable pageable);
	public List<DishName> findByDishNameIdIn(List<Integer> dishNameIdList);
}
