package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.domain.UserLikeIngredient;
import com.zolPro.yoriLab.domain.favoringredList;
import com.zolPro.yoriLab.dto.ClusterResponseBody;
import com.zolPro.yoriLab.repository.JPAFavorIngredRepository;
import com.zolPro.yoriLab.repository.JPAMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FavoringredServiceImpl {
    private JPAFavorIngredRepository jpaFavorIngredRepository;
    private JPAMemberRepository memberRepository;
    private OtherApiService apiService;


    public void insert(Member smember,String[] ingredArray) {
        jpaFavorIngredRepository.saveingredList(smember.getId(),ingredArray);

        Member member = memberRepository.findByEmailID(smember.getEmailID());
        List<UserLikeIngredient> likes = member.getLikes();
        List<String> likeStr = new ArrayList<>();
        for (UserLikeIngredient like : likes) {
            likeStr.add(like.getIngredient().getName());
        }
        System.out.println("len: " + likeStr.size());
        ClusterResponseBody clusterResponseBody = apiService.fetchCluster(likeStr);
        int dishCluster = clusterResponseBody.getDishCluster();
        int soupCluster = clusterResponseBody.getSoupCluster();
        member.setDishCluster(dishCluster);
        member.setSoupCluster(soupCluster);
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
