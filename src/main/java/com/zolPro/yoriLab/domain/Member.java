package com.zolPro.yoriLab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailID; // 이메일 ID
    private String PW; // 비밀번호
    private String name; // 이름
    private int age; // 나이
    private int sex; // 성별
    private int job; // 직업
    private int cookSkill; // 요리실력
    private int cookField; // 좋아하는 요리 분야
    private String favoriteIngredient; // 좋아하는 재료
}
