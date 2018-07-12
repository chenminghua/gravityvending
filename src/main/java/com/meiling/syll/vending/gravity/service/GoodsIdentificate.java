package com.meiling.syll.vending.gravity.service;

import com.meiling.syll.vending.gravity.model.Goods;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: minghua
 * @date: 2018/7/10 14:48
 * @modified By:
 */
@Getter
@Setter
public class GoodsIdentificate {
    private List<GoodsDTO> goodsList;

    /**
     * 计算账单
     * @param loseWeight 重量减少值
     * @return 应付款额
     */
    public double calBill(int loseWeight) {
        double bill = 0;
        Map<Goods, Integer> goodsMap = calCompositeGoods(loseWeight, goodsList);
        for (Map.Entry<Goods, Integer> entry: goodsMap.entrySet()) {
            System.out.println("商品：" + entry.getKey() + ", 数量：" + entry.getValue());
            bill += entry.getKey().getPrice() * entry.getValue();
        }
        return bill;
    }

    /**
     * 计算减少的重量与当前货架的货品的各种组合的最接近组合方式
     * @param loseWeight
     * @param goodsList
     * @return
     */
    private Map<Goods, Integer> calCompositeGoods(int loseWeight, List<GoodsDTO> goodsList) {
        //最简单的方式，我们只能取一个货物
        Goods goods = new Goods();
        int min = 0;
        for (int i = 0; i < goodsList.size(); i++) {
            int currentValue = Math.abs(loseWeight - goodsList.get(i).getGoods().getWeight());
            if (i == 0) {
                min = currentValue;
                goods = goodsList.get(i).getGoods();
            } else {
                if (currentValue < min) {
                    goods = goodsList.get(i).getGoods();
                    min = currentValue;
                }
            }

        }
        Map<Goods, Integer> map = new HashMap<>();
        map.put(goods, 1);
        return map;

//        System.out.println("检测到减少的重量为：" + loseWeight + "g.");
//        Iterator<Goods> iterator = goodsList.iterator();
//        Map<Goods, Integer> result = new HashMap<>();
//        while (iterator.hasNext()) {
//            Goods goods = iterator.next();
//            /*
//            假设减少的重量刚好等于某个商品的标重，则直接返回
//             */
//            if (loseWeight == goods.getWeight()) {
//                result.put(goods, 1);
//                return result;
//            }
//            if (loseWeight % goods.getWeight() == 0) {
//                result.put(goods, loseWeight / goods.getWeight());
//                return result;
//            }
//        }
//
//
//        /*
//        以上情况针对单一货品
//        针对组合商品，将重新计算
//         */
//        Iterator<Goods> iterator1 = goodsList.iterator();
//        while (iterator1.hasNext()) {
//            Goods goods = iterator1.next();
//            //减少的重量小于货品标重，则直接将该货品排除
//            if (loseWeight < goods.getWeight()) {
//                System.out.println("排除 " + goods);
//                iterator1.remove();
//            }
//        }
//        System.out.println("排除后剩余列表为" + goodsList.size());
//
//        return null;
    }

}
