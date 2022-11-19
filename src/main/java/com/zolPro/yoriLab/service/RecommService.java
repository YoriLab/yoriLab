package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.controller.RecommendationController;
import com.zolPro.yoriLab.domain.*;
import com.zolPro.yoriLab.dto.*;
import com.zolPro.yoriLab.repository.FoodRepository;
import com.zolPro.yoriLab.repository.JPAMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class RecommService {
    private FoodService foodService;
    private FoodRepository foodRepository;
    private OtherApiService otherApiService;
    private JPAMemberRepository memberRepository;

    public List<RecommendationByDay> getRecommendationWithSession(int day, Member member) {
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


    public List<RecommendationByDay> getRecommendationWithoutSession(Long day) {
        List<RecommendationByDay> recommendationFullList = new ArrayList<>();


        // 완전 랜덤
        // 임시 생성
        Random random = new Random();

        // food table에 전체 row 수
        Long totalCount =foodService.getTotalCount();

        for (int i = 0; i < day; i++) { // 몇일차
            RecommendationByDay recommendationByDay = new RecommendationByDay(i + 1);
            for (int j = 0; j < 3; j++) { // 아침 점심 저녁
                RecommendationByWhen recommendationByWhen = new RecommendationByWhen(WhenToCook.values()[j]);

                // 랜덤한 food id list
                List<Long> randomFoodIdList = new ArrayList<>();
                Integer randomNum = random.nextInt(5) + 1;
                for (int k = 0; k < randomNum; k++) {
                    randomFoodIdList.add((long) (random.nextInt(Math.toIntExact(totalCount)) + 1));
                }


                // db에서 랜덤으로 food 데이터 가져오기
                List<Food> randomFoodList = foodService.findAllByIdList(randomFoodIdList);


                // 반복문 돌며 임시 데이터 생성
                for (int k = 0; k < randomFoodList.size(); k++) {
                    Recommendation recommendation = new Recommendation(
                            randomFoodList.get(k));

                    recommendationByWhen.addRecomm(recommendation);
                }
                recommendationByDay.addRecommByWhen(recommendationByWhen);
            }
            recommendationFullList.add(recommendationByDay);
        }

        return recommendationFullList;
    }

    public List<RecommendationByDay> getRecommendationByDays(Long day, Member sessionMember) {
        List<RecommendationByDay> recommendationFullList = new ArrayList<>();

        if (sessionMember != null) {
            // 로그인한 유저
            Member member = memberRepository.findById(sessionMember.getId()).orElse(null);
            recommendationFullList = getRecommendationWithSession(day.intValue(), member);
        } else {
            // 로그인 안 한 유저
            recommendationFullList = getRecommendationWithoutSession(day);
        }
        return recommendationFullList;
    }


    public Map<String, String> getAllIngredientList(List<RecommendationByDay> recommendationFullList) {
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
        return allIngredientList;
    }
}
