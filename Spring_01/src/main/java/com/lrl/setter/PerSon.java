package com.lrl.setter;

import com.sun.jndi.cosnaming.IiopUrl;

public class PerSon {
    private String Person;
    private Integer age;


    private Address homeAdder;  //家庭地址
    private Address companyAdder; //公司地址

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getHomeAdder() {
        return homeAdder;
    }

    public void setHomeAdder(Address homeAdder) {
        this.homeAdder = homeAdder;
    }

    public Address getCompanyAdder() {
        return companyAdder;
    }

    public void setCompanyAdder(Address companyAdder) {
        this.companyAdder = companyAdder;
    }


    @Override
    public String toString() {
        return "PerSon{" +
                "Person='" + Person + '\'' +
                ", age=" + age +
                ", homeAdder=" + homeAdder +
                ", companyAdder=" + companyAdder +
                '}';
    }
}
