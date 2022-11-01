package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.repository.JPAMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl {
    private JPAMemberRepository jpaMemberRepository;

    @Autowired
    public MemberServiceImpl(JPAMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    public void join(Member member) {
        jpaMemberRepository.save(member);
    }

    public Member find(String emailID) {
        Member member = jpaMemberRepository.findByEmailID(emailID);
        if(member == null) {

            return null;
        }else {
            return member;
        }


    }

}
