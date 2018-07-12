package com.meiling.syll.vending.gravity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: minghua
 * @date: 2018/7/10 14:49
 * @modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品标准重量
     */
    private int weight;
    private double price;
    /**
     * 当前搁架数量
     */
    private int num;
}
