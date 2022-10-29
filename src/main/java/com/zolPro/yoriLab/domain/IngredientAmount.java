package com.zolPro.yoriLab.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(IngredientAmountId.class)
@NoArgsConstructor
public class IngredientAmount {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Food food;

    private Double count;

    private String unit;
}

