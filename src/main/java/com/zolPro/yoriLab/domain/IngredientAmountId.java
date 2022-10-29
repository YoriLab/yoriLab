package com.zolPro.yoriLab.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class IngredientAmountId implements Serializable {
    private Ingredient ingredient;
    private Food food;
}
