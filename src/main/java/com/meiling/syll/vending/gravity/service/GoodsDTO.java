package com.meiling.syll.vending.gravity.service;

import com.meiling.syll.vending.gravity.model.Goods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: minghua
 * @date: 2018/7/12 18:36
 * @modified By:
 */
@Getter
@Setter
@AllArgsConstructor
public class GoodsDTO {
    private Goods goods;
    private int num;
}
