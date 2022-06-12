package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Food;
import com.zolPro.yoriLab.repository.FoodRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FoodService {
    private FoodRepository foodRepository;

    public List<Food> getSomeFoodListSpecificCount(Integer count) {
        return foodRepository.findSpecificCount(count);
    }
}
