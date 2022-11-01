package com.zolPro.yoriLab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ClusterRequestBody {
    private List<String> likes = new ArrayList<>();
}
