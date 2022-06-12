package com.zolPro.yoriLab.controller;

import com.zolPro.yoriLab.domain.Food;
import com.zolPro.yoriLab.domain.Ingredient;
import com.zolPro.yoriLab.domain.Recommendation;
import com.zolPro.yoriLab.domain.WhenToCook;
import com.zolPro.yoriLab.dto.RecommendationByDay;
import com.zolPro.yoriLab.dto.RecommendationByWhen;
import com.zolPro.yoriLab.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

        for (int i = 0; i < day; i++) { // 몇일차
            RecommendationByDay recommendationByDay = new RecommendationByDay(i+1);
            for (int j = 0; j < 3; j++) { // 아침 점심 저녁
                RecommendationByWhen recommendationByWhen = new RecommendationByWhen(WhenToCook.values()[j]);

                Integer randomNum = random.nextInt(5) + 1;
                // db에서 랜덤으로 food 데이터 가져오기
                List<Food> randomFoodList = foodService.getSomeFoodListSpecificCount(randomNum);

                // 반복문 돌며 임시 데이터 생성
                for (int k = 0; k < randomNum; k++) {
                    List<Ingredient> randomIngredientList = new ArrayList<>();
                    randomIngredientList.add(new Ingredient("랜덤 재료 " + i + j + k));

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
        List<Ingredient> allIngredientList = new ArrayList<>();
        for (RecommendationByDay recommendationByDay : recommendationFullList) {
            for (RecommendationByWhen recommendationByWhen : recommendationByDay.getRecommendationByWhenList()) {
                for (Recommendation recommendation : recommendationByWhen.getRecommendationList()) {
//                    allIngredientList.addAll(recommendation.getFood().getIngredientList());
                }
            }
        }
        model.addAttribute("allIngredientList", allIngredientList);

        // 일 수 전달
        model.addAttribute("day", day);
        return "contents/recommendation";
    }
}
