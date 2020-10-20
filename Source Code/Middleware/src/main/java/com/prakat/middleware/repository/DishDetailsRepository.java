package com.prakat.middleware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakat.middleware.entity.DishDetails;
import com.prakat.middleware.entity.DishExtra;

@Repository
public interface DishDetailsRepository extends JpaRepository<DishDetails, Integer>{
}
