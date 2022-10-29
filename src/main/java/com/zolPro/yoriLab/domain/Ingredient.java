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
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imgUrl;

    @OneToMany(mappedBy = "ingredient")
    @JsonIgnore
    private List<IngredientAmount> ingredientAmountList;


    public Ingredient(String s) {

    }
}
