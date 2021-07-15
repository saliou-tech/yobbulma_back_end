package com.Yobulma.Yobulma.Dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LivreurDto extends  UserDto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String telephone;
    private String adresse;
    private String nom;
    private String password;
    private String profile;
}
