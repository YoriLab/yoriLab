package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.*;
import com.zolPro.yoriLab.dto.*;
import com.zolPro.yoriLab.repository.FoodRepository;
import com.zolPro.yoriLab.repository.JPAMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RecommService {
    private FoodService foodService;
    private FoodRepository foodRepository;
    private JPAMemberRepository memberRepository;
    private RestTemplate restTemplate;
    private OtherApiService otherApiService;

    public List<RecommendationByDay> getRecommendation(int day, Member member) {
        RecommResponseBody recommResponseBody = otherApiService.fetchRecomm(day, member);
        member.setDishPointer(recommResponseBody.getDishPointer());
        member.setSoupPointer(recommResponseBody.getSoupPointer());


        /* response -> RecommendationByDay */
        List<RecommendationByDay> recommendationFullList = new ArrayList<>();


        List<RecommForDay> recommTotal = recommResponseBody.getRecomms();
        for (RecommForDay recommForDay : recommTotal) {
            int cook_day = recommForDay.getDay();
            RecommendationByDay newRecommDay = new RecommendationByDay(cook_day+1);

            List<RecommForMeal> recomms = recommForDay.getRecomms();
            for (RecommForMeal recomm : recomms) {
                int whenToCook = recomm.getWhenToCook();
                RecommendationByWhen newRecommWhen = new RecommendationByWhen(WhenToCook.values()[whenToCook]);

                List<String> soupNames = recomm.getSoupNames();
                List<String> dishNames = recomm.getDishNames();

                List<Food> soupList = foodRepository.findAllByNameList(soupNames);
                List<Food> dishList = foodRepository.findAllByNameList(dishNames);
                for (Food food : dishList) {
                    Recommendation newRecomm = new Recommendation(food);
                    newRecommWhen.addRecomm(newRecomm);
                }
                for (Food food : soupList) {
                    Recommendation newRecomm = new Recommendation(food);
                    newRecommWhen.addRecomm(newRecomm);
                }
                newRecommDay.addRecommByWhen(newRecommWhen);
            }

            recommendationFullList.add(newRecommDay);
        }

        return recommendationFullList;
    }
}
