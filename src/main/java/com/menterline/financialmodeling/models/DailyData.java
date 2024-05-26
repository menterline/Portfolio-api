package com.menterline.financialmodeling.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DailyData {
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

    public DailyData() {
    }

    public DailyData(double open, double high, double low, double close, double volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    // Getters and setters
    @JsonGetter("open")
    public double getOpen() { return open; }
    @JsonSetter("1. open")
    public void setOpen(double open) { this.open = open; }

    @JsonGetter("high")
    public double getHigh() { return high; }
    @JsonSetter("2. high")
    public void setHigh(double high) { this.high = high; }

    @JsonGetter("low")
    public double getLow() { return low; }
    @JsonSetter("3. low")
    public void setLow(double low) { this.low = low; }

    @JsonGetter("close")
    public double getClose() { return close; }
    @JsonSetter("4. close")
    public void setClose(double close) { this.close = close; }

    @JsonGetter("volume")
    public double getVolume() { return volume; }
    @JsonSetter("5. volume")
    public void setVolume(double volume) { this.volume = volume; }
}
