package com.zolPro.yoriLab.dto;

import com.zolPro.yoriLab.domain.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Data
public class RecipeByDay {
    private Integer day;
    private List<RecommendationByWhen> recommendationByWhenList = new ArrayList<>();

    public RecipeByDay(Integer day) {
        this.day = day;
    }

    public void RecipeByDayWhen(RecommendationByWhen recommendationByWhen) {
        recommendationByWhenList.add(recommendationByWhen);
    }
}