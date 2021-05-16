package com.ftn.PreporukaOdevneKombinacije.dto;


import javax.validation.constraints.NotBlank;

public class UserLoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public UserLoginDTO() {}

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
