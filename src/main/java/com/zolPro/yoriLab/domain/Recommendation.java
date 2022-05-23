package com.zolPro.yoriLab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Getter
@AllArgsConstructor
@Data
public class Recommendation {
    private Integer day; // 몇일차 추천
    private WhenToCook whenToCook; // 아침/점심/저녁
    private Date recommendedDate; // 추천 받은 날짜
    private Food food; // 음식 종류
}
