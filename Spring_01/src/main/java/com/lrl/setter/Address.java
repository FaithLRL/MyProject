package com.lrl.setter;

public class Address {
    private String adder;
    private String tel;

    public String getAdder() {
        return adder;
    }

    public void setAdder(String adder) {
        this.adder = adder;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Address{" +
                "adder='" + adder + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
