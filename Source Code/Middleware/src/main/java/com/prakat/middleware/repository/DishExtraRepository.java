package com.prakat.middleware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.DishExtra;

@Repository
public interface DishExtraRepository extends JpaRepository<DishExtra, Integer>{
	public List<DishExtra> findByDishNameId(int dishNameId);
	public List<DishExtra> findByParentId(int parentId);
}
