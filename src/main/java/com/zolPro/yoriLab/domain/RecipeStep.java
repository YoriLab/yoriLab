package com.zolPro.yoriLab.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class RecipeStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sequence; // 요리의 몇번째 순서인지

    @Column(columnDefinition = "TEXT")
    private String description; // 레시피 조리 설명

    private String imgUrl; // 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Food food; // 어느 음식의 레시피 스텝인지

}
