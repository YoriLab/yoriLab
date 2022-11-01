package com.zolPro.yoriLab.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Member")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailID; // 이메일 ID
    private String PW; // 비밀번호
    private String name; // 이름

    private Integer dishCluster = 0;
    private Integer dishPointer = 0;
    private Integer soupCluster = 0;
    private Integer soupPointer = 0;

    @OneToMany(mappedBy = "user")
    private List<UserLikeIngredient> likes;

    public Member(String emailID, String PW, String name) {
        this.emailID = emailID;
        this.PW = PW;
        this.name = name;
    }
}
