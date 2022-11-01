package com.zolPro.yoriLab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommForMeal {
    private int whenToCook;
    private List<String> dishNames;
    private List<String> soupNames;
}
