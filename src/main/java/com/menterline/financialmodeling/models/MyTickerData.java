package com.menterline.financialmodeling.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyTickerData {
    private final String ticker;
    private final ArrayList<MyTickerDataNode> data;

    public MyTickerData(String ticker, ArrayList<MyTickerDataNode> data) {
        this.ticker = ticker;
        this.data = data;
    }

    public String getTicker() {
        return ticker;
    }

    public ArrayList<MyTickerDataNode> getData() {
        return data;
    }

}
