package com.lrl.coll;

import java.util.*;

public class CollData {
    private String[] arrayData;
    private List<String> listData;
    private Set<String> setData;
    private Map<String,String> mapData;
    private Properties propsData;

    public String[] getArrayData() {
        return arrayData;
    }

    public void setArrayData(String[] arrayData) {
        this.arrayData = arrayData;
    }

    public List<String> getListData() {
        return listData;
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    public Set<String> getSetData() {
        return setData;
    }

    public void setSetData(Set<String> setData) {
        this.setData = setData;
    }

    public Map<String, String> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, String> mapData) {
        this.mapData = mapData;
    }

    public Properties getPropsData() {
        return propsData;
    }

    public void setPropsData(Properties propsData) {
        this.propsData = propsData;
    }


    @Override
    public String toString() {
        return "CollData{" +
                "arrayData=" + Arrays.toString(arrayData) +
                ", listData=" + listData +
                ", setData=" + setData +
                ", mapData=" + mapData +
                ", propsData=" + propsData +
                '}';
    }
}
