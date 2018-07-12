package com.meiling.syll.vending.gravity.algorithm;

import java.util.List;

/**
 * @author: minghua
 * @date: 2018/7/12 18:15
 * @modified By:
 */
public class Box {
    //存放的物品
    private List<Commodity> commodityList;
    private int value;

    public Box() {
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Box(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    /**
     * 添加商品
     *
     * @param commodity
     */
    public void addCommodity(Commodity commodity) {
        this.commodityList.add(commodity);
    }

    /**
     * 包裹对应的价值
     *
     * @return
     */
    private int getPackageValue() {
        int value = 0;
        for (Commodity commodity : commodityList) {
            value = value + commodity.getValue();
        }
        return value;
    }

    public int getValue() {
        this.value = getPackageValue();
        return value;
    }

    /**
     * 包裹的重量
     *
     * @return
     */
    public long getPackageWeight() {
        long weight = 0;
        for (Commodity commodity : commodityList) {
            weight = weight + commodity.getValue();
        }
        return weight;
    }
}
