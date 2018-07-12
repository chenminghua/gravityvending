package com.meiling.syll.vending.gravity.service;

import org.junit.Test;

/**
 * * 最优组合之背包算法
 * *
 * *价值：6，3，5，4，6
 * *重量：2，2，6，5，4
 * *载重10
 * *求最大价值？
 *
 * @author: minghua
 * @date: 2018/7/12 18:06
 * @modified By:
 */
public class KnapsackTest {

    @Test
    public void test() {

        int max = 10;//载重
        int min = 1;
        int[] jz = new int[]{6, 3, 5, 5, 6};//对应的价值
        int[] zl = new int[]{2, 2, 6, 5, 4};//对应的重量
        int value[] = new int[max + 1];//最大价值(value[max]值最大，value数组从小到大排序)
        int item[] = new int[max + 1]; //放入物体

        for (int i = 0; i < zl.length; i++) {
            for (int s = zl[i]; s <= max; s++) {//s表示货物的重量
                int p = s - zl[i];//单位重量每增加1
                int currVal = jz[i] + value[p];//value[p]原先的重量，jz[i]新增的重量
                if (currVal > value[s]) {
                    value[s] = currVal;//总重量为s时的最大价值
                    item[s] = i;   //增加重量zl[i]的货物到包中（item中的值相同，说明放入的是同一种货物）
                }
            }
            for (int k = 0; k <= max; k++) {//打印放入第k个物体的单位重量增加的最大价值
                System.out.print(value[k] + " ");
            }
            System.out.println();
        }

        //找出放入背包的物体
        for (int i = max; i > min; i = i - zl[item[i]]) {//value[max]价值最大
            //System.out.println(i);//依此类推，除去第N个物体的总重量
            System.out.println("重量：" + zl[item[i]] + "价值：" + jz[item[i]]);
        }
    }
}
