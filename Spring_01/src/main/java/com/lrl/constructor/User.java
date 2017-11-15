package com.lrl.constructor;

public class User {
    private int uid;
    private String username;
    private Integer age;


//    uid和username构造


    public User(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }
    //   username和age构造
    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
