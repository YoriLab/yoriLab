package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
//        @Query(value = "SELECT f from Food f join fetch f.ingredientAmountList ia join fetch  ia.ingredient")
//    @Query(value = "SELECT f from Food f order by rand() limit :count", nativeQuery = true)
//    List<Food> findSpecificCount(@Param("count") Integer count);

    // 이거 써
//    @Query(value = "SELECT f from Food f join fetch f.ingredientAmountList ia join fetch  ia.ingredient where f.id in :idList")
    @Query(value = "SELECT f from Food f  where f.id in :idList")
    List<Food> findAllByIdList(@Param("idList") List<Long> idList);
//    @Query(value = "SELECT f from Food f join fetch f.ingredientAmountList ia join fetch  ia.ingredient")
//    List<Food> fetchIngredientOfFood(@Param("foodIdList") List<Long> foodIdList);

    @Query(value = "SELECT f from Food f  where f.name in :nameList")
    List<Food> findAllByNameList(@Param("nameList") List<String> nameList);
    
    // native query에서는 테이별명대로 - 즉, 처음 소문자
    @Query(value = "select count(*) from food", nativeQuery = true)
    Long countTotal();


}
