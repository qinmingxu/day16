package com.example.lianxi01.bean;

/**
 * Created by wan on 2017/12/18.
 */

public class PriceAndCount {
    private double price;
    private int num;

    public PriceAndCount(int num, double price) {
        this.num = num;
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public PriceAndCount setNum(int num) {
        this.num = num;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PriceAndCount setPrice(double price) {
        this.price = price;
        return this;
    }
}
