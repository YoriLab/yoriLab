package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Ingredient;
import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.domain.favoringredList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JPAFavorIngredRepository {

    private final EntityManager em;
    public JPAFavorIngredRepository(EntityManager em) {
        this.em = em;
    }

    public void saveingredList(Long id, String[] ingredArray){
        for(int i = 0; i < ingredArray.length; i++) {
            System.out.println("success");
            Long ingreId = findIngreId(ingredArray[i]);
            em.createNativeQuery("insert into user_like_ingredient(user_id, ingredient_id) values("+id+","
                    +ingreId +")").executeUpdate();
            System.out.println("save success");
        }

    }
    public void updateingredList(favoringredList ingredList){
        em.createNativeQuery("update favoringred_list set emailID = " + "'" + ingredList.getEmailID() + "'," + "ingred = "+
                ingredList.getIngred()).executeUpdate();
    }

    public List<Object[]> findMember(String emailID) {

        return em.createNativeQuery("select name from ingredient where emailID = " + "'" + emailID + "'").getResultList();
    }

    public Long findIngreId(String ingred) {
        List<Object[]> resultList = em.createNativeQuery("select * from ingredient where name = " + "'" + ingred + "'"
        ).getResultList();
        String id = "";

        id += (resultList.get(0))[0];
        System.out.println(id);
        return Long.parseLong(id);
    }

    public List<Object[]> findIngredListByUserID(Long id) {

        System.out.println("id ::: " + id);
        List<Object[]> ingredId = em.createNativeQuery("select * from user_like_ingredient where use" +
                "r_id = " + ""+id.toString()+"" + "").getResultList();

        for(int i = 0; i < ingredId.size(); i++) {



        }

        return ingredId;
    }

    public List<Object[]> findIngredNameByingreID(BigInteger id) {

        System.out.println("id ::: " + id);
        List<Object[]> ingredId = em.createNativeQuery("select * from ingredient where id" +
                " = " + ""+id+"" + "").getResultList();

        return ingredId;
    }

}
