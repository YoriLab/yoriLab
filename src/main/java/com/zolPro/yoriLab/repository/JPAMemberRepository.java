package com.zolPro.yoriLab.repository;

import com.zolPro.yoriLab.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface JPAMemberRepository extends JpaRepository<Member, Long>
{
    public Member findByEmailID(String emailId);
}
