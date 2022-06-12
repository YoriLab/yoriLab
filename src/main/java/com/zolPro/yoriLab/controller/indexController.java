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
import org.springframework.web.bind.annotation.RequestParam;

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
    /* 메인 화면 */
    @GetMapping("/main")
    public String mainPage(Model model) {
        return "mainPage";
    }
    /* 회원가입 페이지 */
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



    /* 레시피 캘린더 페이지 */
    @GetMapping("/recipeCalendar")
    public String recipeCalendar(Model model) {
        return "recipecalendar";
    }
}
