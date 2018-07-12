package com.meiling.syll.vending.gravity.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: minghua
 * @date: 2018/7/12 18:16
 * @modified By:
 */
public class Knapsack {
    private List<Commodity> commodityList;
    private int[] Capacities;
    private Box[][] valueGrid;

    public Box[][] getValueGrid() {
        return valueGrid;
    }

    public Knapsack(List<Commodity> commodityList, int[] capacities) {
        this.commodityList = commodityList;
        Capacities = capacities;
        valueGrid = new Box[commodityList.size()][capacities.length];
        for (int i = 0; i < commodityList.size(); i++)
            for (int j = 0; j < capacities.length; j++) {
                valueGrid[i][j] = new Box();
                valueGrid[i][j].setValue(0);
                valueGrid[i][j].setCommodityList(new ArrayList<Commodity>());
            }
    }

    /**
     * 根据背包容量获取在网格中的下标（纵坐标）
     *
     * @param capcity
     * @return
     */
    public int getCapcityIndex(int capcity) {
        for (int i = 0; i < Capacities.length; i++) {
            if (Capacities[i] == capcity)
                return i;
        }
        return -1;
    }

    /**
     * 根据背包容量对应的下标，获取该容量下的最大价值
     *
     * @param j
     * @return
     */
    public int getMaxValue(int j) {
        int maxValue = -1;
        for (int i = 0; i < valueGrid.length; i++) {
            if (valueGrid[i][j].getValue() > maxValue)
                maxValue = valueGrid[i][j].getValue();
        }
        return maxValue;
    }

    /**
     * 根据背包容量对应的下标，获取该容量下的最大值的下标
     *
     * @param j
     * @return
     */
    public int getMaxValueIndex(int j) {
        int index = -1;
        int maxValue = -1;
        for (int i = 0; i < valueGrid.length; i++) {
            if (valueGrid[i][j].getValue() > maxValue) {
                maxValue = valueGrid[i][j].getValue();
                index = i;
            }
        }
        return index;
    }

    public int getMaxValueByWeight(int weight) {
        if (weight <= 0)
            return 0;
        else {
            int index = getCapcityIndex(weight);
            //对应的容量并不属于容量中的任意一个
            if (index < 0)
                return 0;
            else
                return getMaxValue(index);
        }

    }

    /**
     * 根据商品名称获取在网格中的下标（横坐标）
     *
     * @param commodityName
     */
    public int getCommodityIndex(String commodityName) {

        for (int i = 0; i < this.commodityList.size(); i++) {
            if (commodityList.get(i).getName().equals(commodityName))
                return i;
        }
        return -1;

    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public int[] getCapacities() {
        return Capacities;
    }

    public void setCapacities(int[] capacities) {
        Capacities = capacities;
    }

    public Box getBoxByIndex(int commodityIndex, int capcityIndex) {
        Box box;
        if (commodityIndex < 0 || capcityIndex < 0) {
            box = new Box();
            box.setValue(0);
            box.setCommodityList(new ArrayList<Commodity>());
        } else {
            box = valueGrid[commodityIndex][capcityIndex];
        }
        return box;
    }

    /**
     * 构造value表格
     */
    public void constructValueGrid() {
        for (int i = 0; i < valueGrid.length; i++)
            for (int j = 0; j < valueGrid[i].length; j++) {
                Commodity commdity = this.commodityList.get(i);
                int capacity = this.Capacities[j];
                //判断当前容量能够放下该商品
                if (commdity.getWeight() <= capacity) {
                    //剩余的空间
                    int leftCapacity = capacity - commdity.getWeight();
                    int valueAfterPut = commdity.getValue() + this.getMaxValueByWeight(leftCapacity);
                    //容量对应的下标
                    int capcityIndex = getCapcityIndex(leftCapacity);
                    //商品下标
                    int commodityIndex = this.getMaxValueIndex(capacity - commdity.getWeight());
                    List<Commodity> lst = this.getBoxByIndex(commodityIndex, capcityIndex).getCommodityList();
                    //放上最新物品后的解大于之前该容量的最优解，则将该物品以及之前的最优解都放到新的表格中，如a,b所示
                    //否则最优解仍然是
                    if (valueAfterPut >= this.getMaxValueByWeight(capacity)) {
                        valueGrid[i][j].setValue(valueAfterPut);
                        //a.将原有的货物加到新的表格中
                        for (Commodity tempCommdity : lst) {
                            valueGrid[i][j].addCommodity(tempCommdity);
                        }
                        //b.将当前货物加到表格中
                        valueGrid[i][j].addCommodity(commdity);
                    } else {
                        int tempI = (i - 1) < 0 ? 0 : i - 1;
                        valueGrid[i][j] = valueGrid[tempI][j];
                    }
                } else {
                    //不能放下商品，则最优解仍然是上一个商品所对应的容量的最优解
                    int tempI = (i - 1) < 0 ? 0 : i - 1;
                    valueGrid[i][j] = valueGrid[tempI][j];
                }
            }

    }

    public static String getValueAndCommodity(Box box) {
        StringBuilder sb = new StringBuilder();
        sb.append("<" + box.getValue() + ":");
        for (Commodity commodity : box.getCommodityList()) {
            sb.append(commodity.getName() + "-");
        }
        sb.append(">");
        return sb.toString();

    }

    public static void main(String[] args) {
        List<Commodity> commodityList = new ArrayList<Commodity>();
        commodityList.add(new Commodity("吉他", 1, 1500));
        commodityList.add(new Commodity("笔记本电脑", 3, 6000));
        commodityList.add(new Commodity("音响", 4, 7000));
        int[] capcities = {1, 2, 3, 4, 5, 6};
        Knapsack knapsack = new Knapsack(commodityList, capcities);
        knapsack.constructValueGrid();
        int i = commodityList.size() - 1;
        for (int j = 0; j < capcities.length; j++) {
            String s = String.format("质量为%s:", capcities[j]);
            System.out.println(s + getValueAndCommodity(knapsack.getValueGrid()[i][j]));
        }


    }
}
