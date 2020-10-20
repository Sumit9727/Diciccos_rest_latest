package com.prakat.middleware.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.DishType;

@Repository
public interface DishTypeRepository extends JpaRepository<DishType, Integer>{
	public Page<DishType> findByMenuTypeId(int menuTypeId,Pageable pageable);
}
