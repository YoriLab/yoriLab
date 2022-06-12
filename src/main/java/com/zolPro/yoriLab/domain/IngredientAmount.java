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
    @ManyToOne
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @Id
    @ManyToOne
    @JoinColumn()
    private Food food;

    private Integer count;

    private String unit;
}

