package com.lrl.di;

public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("di add Book");
    }
}
