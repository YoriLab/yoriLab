package com.zolPro.yoriLab.controller;

import com.zolPro.yoriLab.domain.*;
import com.zolPro.yoriLab.dto.RecommendationByDay;
import com.zolPro.yoriLab.service.RecommService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@AllArgsConstructor
public class RecommendationController {
    private RecommService recommService;

    /* 추천 페이지 */
    @GetMapping("/recommendation")
    public String recommendation(Model model, @RequestParam(value = "day", required = false, defaultValue = "1") Long day, HttpSession session) {
        List<RecommendationByDay> recommendationFullList = new ArrayList<>();

//        session
        Member sessionMember = (Member) session.getAttribute("member");

        recommendationFullList = recommService.getRecommendationByDays(day, sessionMember);

        // 레시피 일차별 출력 위한 변수
        model.addAttribute("recommendationFullList", recommendationFullList);


        Map<String, String> allIngredientList = recommService.getAllIngredientList(recommendationFullList);

        model.addAttribute("allIngredientList", allIngredientList);

        // 일 수 전달
        model.addAttribute("day", day);
        return "contents/recommendation";
    }

}
