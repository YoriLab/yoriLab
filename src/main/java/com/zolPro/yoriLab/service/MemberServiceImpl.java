package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.repository.JPAMemberRepository;
import com.zolPro.yoriLab.repository.MemberRepository;
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
        jpaMemberRepository.saveMember(member);
    }

    public Member find(String emailID) {
        if(jpaMemberRepository.findMember(emailID).size() == 0) {

            return null;
        }else {
            return jpaMemberRepository.findMember(emailID).get(0);
        }


    }

}
