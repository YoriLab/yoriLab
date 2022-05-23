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
public class RecommendationByDay {
    private Integer day;
    private List<RecommendationByWhen> recommendationByWhenList = new ArrayList<>();

    public RecommendationByDay(Integer day) {
        this.day = day;
    }

    public void addRecommByWhen(RecommendationByWhen recommendationByWhen) {
        recommendationByWhenList.add(recommendationByWhen);
    }
}
