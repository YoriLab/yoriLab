package com.zolPro.yoriLab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "favoringred_list")
public class favoringredList {
    @Id
    private String emailID; // 이메일 ID
    private String ingred;

}
