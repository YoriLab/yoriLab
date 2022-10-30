package com.zolPro.yoriLab.controller;

import com.zolPro.yoriLab.domain.*;
import com.zolPro.yoriLab.dto.RecommendationByDay;
import com.zolPro.yoriLab.dto.RecommendationByWhen;
import com.zolPro.yoriLab.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class RecommendationController {
    private FoodService foodService;

    /* 추천 페이지 */
    @GetMapping("/recommendation")
    public String recommendation(Model model, @RequestParam(value = "day", required = false, defaultValue = "7") Long day) {
        List<RecommendationByDay> recommendationFullList = new ArrayList<>();

        // 임시 생성
        Random random = new Random();

        // food table에 전체 row 수
        Long totalCount = foodService.getTotalCount();

        for (int i = 0; i < day; i++) { // 몇일차
            RecommendationByDay recommendationByDay = new RecommendationByDay(i+1);
            for (int j = 0; j < 3; j++) { // 아침 점심 저녁
                RecommendationByWhen recommendationByWhen = new RecommendationByWhen(WhenToCook.values()[j]);

                // 랜덤한 food id list
                List<Long> randomFoodIdList = new ArrayList<>();
                Integer randomNum = random.nextInt(5) + 1;
                for (int k = 0; k < randomNum; k++) {
                    randomFoodIdList.add((long) (random.nextInt(Math.toIntExact(totalCount)) + 1));
                }


                // db에서 랜덤으로 food 데이터 가져오기
//                List<Food> randomFoodList = foodService.getSomeFoodListSpecificCount(randomNum);
                List<Food> randomFoodList = foodService.findAllByIdList(randomFoodIdList);


                // 반복문 돌며 임시 데이터 생성
                for (int k = 0; k < randomFoodList.size(); k++) {
//                    List<Ingredient> randomIngredientList = new ArrayList<>();
//                    randomIngredientList.add(new Ingredient("랜덤 재료 " + i + j + k));

                    Recommendation recommendation = new Recommendation(i + 1, WhenToCook.values()[j], new Date(),
                            randomFoodList.get(k));
                    recommendationByWhen.addRecomm(recommendation);
                }
                recommendationByDay.addRecommByWhen(recommendationByWhen);
            }
            recommendationFullList.add(recommendationByDay);
        }



        // 레시피 일차별 출력 위한 변수
        model.addAttribute("recommendationFullList", recommendationFullList);


        // 레시피 재료들 영수증 출력 위한 변수
        // key: 재료 이름
        // value: Map<단위, 개수>
        Map<String, Map<String, Double>> allIngredientCount = new HashMap<>();

        for (RecommendationByDay recommendationByDay : recommendationFullList) {
            for (RecommendationByWhen recommendationByWhen : recommendationByDay.getRecommendationByWhenList()) {
                for (Recommendation recommendation : recommendationByWhen.getRecommendationList()) {
                    List<IngredientAmount> ingredientAmountList = recommendation.getFood().getIngredientAmountList();
                    for (IngredientAmount ingredientAmount : ingredientAmountList) {
                        Ingredient ingredient = ingredientAmount.getIngredient();
                        Map<String, Double> specificIngredientUnitCount = allIngredientCount.getOrDefault(ingredient.getName(), new HashMap<>());

                        specificIngredientUnitCount.put(ingredientAmount.getUnit(),
                                specificIngredientUnitCount.getOrDefault(ingredientAmount.getUnit(), 0.0) + ingredientAmount.getCount());

                        allIngredientCount.put(ingredient.getName(), specificIngredientUnitCount);
                    }

                }
            }
        }

        Map<String, String> allIngredientList = new HashMap<>();
        for (String s : allIngredientCount.keySet()) {
            String unit = (String) allIngredientCount.get(s).keySet().toArray()[0];
            allIngredientList.put(s, allIngredientCount.get(s).get(unit) + unit);
        }

        model.addAttribute("allIngredientList", allIngredientList);
//        model.addAttribute("allIngredientList", new HashMap<>());

        // 일 수 전달
        model.addAttribute("day", day);
        return "contents/recommendation";
    }
}
