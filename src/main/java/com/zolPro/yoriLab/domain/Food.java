package com.zolPro.yoriLab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 음식 이름
    private String imgUrl; // 음식 이미지 경로
    private String way; // 조리 종류 (찌기, 굽기)
    private String category; // 요리 종류 (반찬, 국)
    @Lob
    private String doc; // 조리법 글
    // 영양소 양
    private Double INFO_NA;
    private Double INFO_WGT;
    private Double INFO_PRO;
    private Double INFO_FAT;
    private Double INFO_CAR;
    private Double INFO_ENG;

//    private Integer serving; // 몇 인분
//    private Integer cookTime; // 요리 시간
//    private Integer difficulty; // 난이도


    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<IngredientAmount> ingredientAmountList;


    public Food(String s, String name, List<Ingredient> randomIngredientList) {
    }
}
