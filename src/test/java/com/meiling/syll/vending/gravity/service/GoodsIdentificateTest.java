package com.meiling.syll.vending.gravity.service;

import com.meiling.syll.vending.gravity.model.Goods;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: minghua
 * @date: 2018/7/10 15:07
 * @modified By:
 */
public class GoodsIdentificateTest {
    private GoodsIdentificate goodsIdentificate = new GoodsIdentificate();

    @Before
    public void setUp() throws Exception {
        List<GoodsDTO> goodsList = new ArrayList<>();
        //Goods jiaDuoBao              = new Goods("加多宝",     340, 3, 5);
        Goods leshiShuPianTongZhuang = new Goods("桶装乐事薯片", 85, 4, 5);
        //Goods leshiShuPianDaiZhuang = new Goods("袋装乐事薯片", 152, 3, 5);
        Goods cocacola              = new Goods("罐装可口可乐", 360, 3, 5);
        //Goods sprite = new Goods("罐装雪碧", 360, 3, 5);
        Goods nongFuShanQuan           = new Goods("农夫山泉", 580, 3, 5);
        Goods yibao                        = new Goods("怡宝", 380, 3, 5);
       // Goods haochidian                 = new Goods("好吃点", 120, 3, 5);
        //Goods pepsiCola            = new Goods("瓶装百事可乐", 370, 3, 5);
        Goods kangshifu        = new Goods("康师傅桶装方便面", 140, 3, 5);
        //goodsList.add(jiaDuoBao);
        //goodsList.add(leshiShuPianDaiZhuang);
        goodsList.add(new GoodsDTO(leshiShuPianTongZhuang, 5));
        goodsList.add(new GoodsDTO(cocacola,5));
        goodsList.add(new GoodsDTO(nongFuShanQuan, 5));
        goodsList.add(new GoodsDTO(yibao, 5));
        //goodsList.add(haochidian);
        //goodsList.add(pepsiCola);
        goodsList.add(new GoodsDTO(kangshifu, 5));

        goodsIdentificate.setGoodsList(goodsList);
    }

    @Test
    public void calBill() {
        double bill = goodsIdentificate.calBill(139);
        System.out.println("应付账单：" + bill + "元");
    }
}