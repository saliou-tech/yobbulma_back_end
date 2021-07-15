package com.Yobulma.Yobulma.Dto;

public class ClientsDto {

    private Long id;
    private String password;
    private String email;
    private String profile;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public ClientsDto setEmail(String email) {
        this.email = email;
        return this;
    }


}
