package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.domain.favoringredList;
import com.zolPro.yoriLab.repository.JPAFavorIngredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FavoringredServiceImpl {
    private JPAFavorIngredRepository jpaFavorIngredRepository;

    @Autowired
    public FavoringredServiceImpl(JPAFavorIngredRepository jpaFavorIngredRepository) {
        this.jpaFavorIngredRepository = jpaFavorIngredRepository;
    }

    public void insert(favoringredList ingredList) {
        System.out.println("id = " + ingredList.getEmailID());
        System.out.println("size = " + jpaFavorIngredRepository.findMember(ingredList.getEmailID()).size());
        if(jpaFavorIngredRepository.findMember(ingredList.getEmailID()).size() == 0) {
            jpaFavorIngredRepository.saveingredList(ingredList);
        }else {
            jpaFavorIngredRepository.updateingredList(ingredList);
        }


    }

}
