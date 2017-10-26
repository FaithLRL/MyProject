package com.lrl.domain;


import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String birthday;
    private String hobby;
    private String married;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }
}
