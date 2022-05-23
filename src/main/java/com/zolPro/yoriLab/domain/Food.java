package com.zolPro.yoriLab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class Food {
    private String imgUrl; // 음식 이미지 경로
    private String name; // 음식 이름
}
