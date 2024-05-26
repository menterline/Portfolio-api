package com.menterline.financialmodeling.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyTickerData {
    private final String ticker;
    private final ArrayList<DailyDataWithDate> data;

    public MyTickerData(String ticker, ArrayList<DailyDataWithDate> data) {
        this.ticker = ticker;
        this.data = data;
    }

    public String getTicker() {
        return ticker;
    }

    public ArrayList<DailyDataWithDate> getData() {
        return data;
    }

}
