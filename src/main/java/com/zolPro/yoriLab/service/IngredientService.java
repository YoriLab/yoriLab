package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {
    private IngredientRepository ingredientRepository;


}
