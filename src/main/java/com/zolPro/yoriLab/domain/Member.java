package com.zolPro.yoriLab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailID; // 이메일 ID
    private String PW; // 비밀번호
    private String name; // 이름

    @OneToMany(mappedBy = "user")
    private List<UserLikeIngredient> likes;

    public Member(String emailID, String PW, String name) {
        this.emailID = emailID;
        this.PW = PW;
        this.name = name;
    }
}
