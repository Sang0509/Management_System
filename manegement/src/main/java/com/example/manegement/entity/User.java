package com.example.manegement.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private String tel;
    private String address;
    private String Birth;
    private String ocupation;
    private String created;
    private String updated;
    private Integer del_flg;

}
