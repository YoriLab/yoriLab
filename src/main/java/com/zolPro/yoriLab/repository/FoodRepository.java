package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query(value = "SELECT * from food f order by RAND() LIMIT :count", nativeQuery = true)
    List<Food> findSpecificCount(@Param("count") Integer count);
}
