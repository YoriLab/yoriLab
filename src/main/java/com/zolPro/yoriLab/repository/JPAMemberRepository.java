package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JPAMemberRepository implements MemberRepository
{
    private final EntityManager em;
    public JPAMemberRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void saveMember(Member member){
        em.persist(member);
    }

    public List<Member> findMember(String emailID) {

        return em.createNativeQuery("select * from member where emailID = " + "'" + emailID + "'",Member.class).getResultList();
    }

}
