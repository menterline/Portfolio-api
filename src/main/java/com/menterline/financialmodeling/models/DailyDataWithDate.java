package com.menterline.financialmodeling.models;

import java.util.Date;

public class DailyDataWithDate {
    private DailyData dailyData;
    private Date date;

    public DailyDataWithDate(DailyData dailyData, Date date) {
        this.dailyData = dailyData;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public DailyData getDailyData() {
        return dailyData;
    }

}
