package com.zolPro.yoriLab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberForm {
    private String emailID; // 이메일 ID
    private String PW; // 비밀번호
    private String name; // 이름
}
