package com.zolPro.yoriLab.domain;

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
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 음식 이름
    private String imgUrl; // 음식 이미지 경로

    private Integer serving; // 몇 인분
    private Integer cookTime; // 요리 시간
    private Integer difficulty; // 난이도
//    private List<Ingredient> ingredientList;

//    @OneToMany(mappedBy = "food")
//    private List<RecipeStep> recipeStepList;

    public Food(String s, String name, List<Ingredient> randomIngredientList) {
    }
}
