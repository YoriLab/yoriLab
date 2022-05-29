package com.zolPro.yoriLab.controller;

import com.zolPro.yoriLab.domain.Food;
import com.zolPro.yoriLab.domain.Ingredient;
import com.zolPro.yoriLab.domain.Recommendation;
import com.zolPro.yoriLab.domain.WhenToCook;
import com.zolPro.yoriLab.dto.RecommendationByDay;
import com.zolPro.yoriLab.dto.RecommendationByWhen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class indexController {

    /* 인덱스 페이지 */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        return "mainPage";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        return "Signup";
    }

    /* 영수증 페이지 */
    @GetMapping("/receipt")
    public String receipt(Model model) {
        // 변경 예정
        return "/";
    }

    @GetMapping("/recipeView")
    public String recipeRecommend(Model model) {
        return "recipeView";
    }
    @GetMapping("/select")
    public String select(Model model) {
        return "selectIngred";
    }

    /* 추천 페이지 */
    @GetMapping("/recommendation")
    public String recommendation(Model model) {
        List<RecommendationByDay> recommendationFullList = new ArrayList<>();

        // 임시 생성
        Random random = new Random();
        char randomChar = 'A';
        for (int i = 0; i < 7; i++) { // 몇일차
            RecommendationByDay recommendationByDay = new RecommendationByDay(i+1);
            for (int j = 0; j < 3; j++) { // 아침 점심 저녁
                RecommendationByWhen recommendationByWhen = new RecommendationByWhen(WhenToCook.values()[j]);
                for (int k = 0; k < random.nextInt(5) + 1; k++) {
                    List<Ingredient> randomIngredientList = new ArrayList<>();
                    randomIngredientList.add(new Ingredient("랜덤 재료 " + i + j + k));

                    Recommendation recommendation = new Recommendation(i + 1, WhenToCook.values()[j], new Date(),
                            new Food("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnV_w7RbpYilhi7ifIg-DZcvZLscDM5ZDhPA&usqp=CAU", "음식 " + randomChar,
                                    randomIngredientList));
                    randomChar++;
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
                    allIngredientList.addAll(recommendation.getFood().getIngredientList());
                }
            }
        }
        model.addAttribute("allIngredientList", allIngredientList);
        return "contents/recommendation";
    }
}
