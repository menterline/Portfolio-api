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
        ArrayList<MyTickerDataNode> data = new ArrayList<>();
        List<String> keys = new ArrayList<>(getDailyData().keySet());
        Collections.sort(keys);
        for (String key : keys) {
            LocalDate localDate = LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            DailyData dailyData = getDailyData().get(key);
            MyTickerDataNode node = new MyTickerDataNode(
            		dailyData.getOpen(), 
            		dailyData.getHigh(),
            		dailyData.getLow(),
            		dailyData.getClose(),
            		dailyData.getVolume(),
            		date);
            data.add(node);
        }
        String ticker = getMetaData().getSymbol();
        return new MyTickerData(ticker, data);
    }
}
