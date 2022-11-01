package com.zolPro.yoriLab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommResponseBody {
    private int dishPointer;
    private int soupPointer;
    private List<RecommForDay> recomms;
}
