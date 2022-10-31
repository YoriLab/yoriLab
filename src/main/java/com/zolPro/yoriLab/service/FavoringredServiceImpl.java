package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.domain.favoringredList;
import com.zolPro.yoriLab.repository.JPAFavorIngredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FavoringredServiceImpl {
    private JPAFavorIngredRepository jpaFavorIngredRepository;

    @Autowired
    public FavoringredServiceImpl(JPAFavorIngredRepository jpaFavorIngredRepository) {
        this.jpaFavorIngredRepository = jpaFavorIngredRepository;
    }

    public void insert(Member member,String[] ingredArray) {
        System.out.println("id = " + member.getId());
        jpaFavorIngredRepository.saveingredList(member.getId(),ingredArray);

    }

    public List<Object[]> selectByID(Long id) {
        List<Object[]> list = jpaFavorIngredRepository.findIngredListByUserID(id);

        for(Object[] i : list) {
            System.out.println("num: "+i[1]);
        }



        return list;
    }

    public String getingredName(BigInteger id) {
    List<Object[]> list = jpaFavorIngredRepository.findIngredNameByingreID(id);





        return (String)(list.get(0)[2]);
    }


}
