package com.menterline.financialmodeling.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AlphaVantageTickerData {

    @JsonProperty("Meta Data")
    private MetaData metaData;

    @JsonProperty("Time Series (Daily)")
    private LinkedHashMap<String, DailyData> dailyData;

    // No-argument constructor
    public AlphaVantageTickerData() {
    }

    // Getters and setters
    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public LinkedHashMap<String, DailyData> getDailyData() {
        return dailyData;
    }

    public void setDailyData(LinkedHashMap<String, DailyData> dailyData) {
        this.dailyData = dailyData;
    }

    public MyTickerData convertToMyTickerData() {
        ArrayList<DailyDataWithDate> data = new ArrayList<>();
        List<String> keys = new ArrayList<>(getDailyData().keySet());
        for (String key : keys) {
            LocalDate localDate = LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            data.add((new DailyDataWithDate(getDailyData().get(key), date)));
        }
        String ticker = getMetaData().getSymbol();
        return new MyTickerData(ticker, data);
    }
}
