package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemberRepository {

    public void saveMember(Member member);


}
