package com.zolPro.yoriLab.domain;

import lombok.Getter;

import java.io.Serializable;

public class UserLikeIngredientId implements Serializable {
    private Ingredient ingredient;
    private Member user;
}
