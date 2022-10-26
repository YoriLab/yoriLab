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
@Data
@NoArgsConstructor
@Table(name = "Member")
public class Member {
    @Id


    private String emailID; // 이메일 ID
    private String PW; // 비밀번호
    private String name; // 이름

}
