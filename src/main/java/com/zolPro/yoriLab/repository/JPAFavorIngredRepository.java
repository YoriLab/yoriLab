package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.domain.favoringredList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JPAFavorIngredRepository {

    private final EntityManager em;
    public JPAFavorIngredRepository(EntityManager em) {
        this.em = em;
    }

    public void saveingredList(favoringredList ingredList){

        em.createNativeQuery("insert into favoringred_list(emailID, ingred) values('"+ingredList.getEmailID()+"','"
                +ingredList.getIngred() +"')").executeUpdate();
    }
    public void updateingredList(favoringredList ingredList){
        em.createNativeQuery("update favoringred_list set emailID = " + "'" + ingredList.getEmailID() + "'," + "ingred = "+
                ingredList.getIngred()).executeUpdate();
    }

    public List<Member> findMember(String emailID) {

        return em.createNativeQuery("select * from favoringred_list where emailID = " + "'" + emailID + "'").getResultList();
    }


}
