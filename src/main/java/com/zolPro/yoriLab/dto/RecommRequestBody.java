package com.zolPro.yoriLab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecommRequestBody {
    private int dishCluster;
    private int dishPointer;
    private int soupCluster;
    private int soupPointer;
    private int day; // 몇일치 추천
}
