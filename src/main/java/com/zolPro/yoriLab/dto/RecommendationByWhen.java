package com.zolPro.yoriLab.dto;

import com.zolPro.yoriLab.domain.Recommendation;
import com.zolPro.yoriLab.domain.WhenToCook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Data
public class RecommendationByWhen {
    private WhenToCook whenToCook;
    private List<Recommendation> recommendationList = new ArrayList<>();

    public RecommendationByWhen(WhenToCook whenToCook) {
        this.whenToCook = whenToCook;
    }

    public void addRecomm(Recommendation recommendation) {
        recommendationList.add(recommendation);
    }
}
