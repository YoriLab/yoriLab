package com.zolPro.yoriLab.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientAmountId implements Serializable {
    private Ingredient ingredient;
    private Food food;
}
