package com.menterline.financialmodeling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menterline.financialmodeling.models.DailyDataWithDate;
import com.menterline.financialmodeling.models.AlphaVantageTickerData;
import com.menterline.financialmodeling.models.MyTickerData;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AlphaVantageTickerDataTests {

	@Test
	public void ExpectParseData() {
		try {
			// Read JSON file as a string
			String jsonData = new String(Files.readAllBytes(Paths.get("src/test/resources/IBM_daily.json")));

			// Parse JSON string into TimeSeriesData object
			ObjectMapper objectMapper = new ObjectMapper();
			AlphaVantageTickerData alphaVantageTickerData = objectMapper.readValue(jsonData, AlphaVantageTickerData.class);
			assertEquals(alphaVantageTickerData.getDailyData().get("2024-05-17").getOpen(),168.97);
			assertEquals(alphaVantageTickerData.getDailyData().get("2024-05-17").getHigh(), 169.11);
			assertEquals(alphaVantageTickerData.getDailyData().get("2024-05-17").getLow(), 167.33);
			assertEquals(alphaVantageTickerData.getDailyData().get("2024-05-17").getClose(), 169.03);
			assertEquals(alphaVantageTickerData.getDailyData().get("2024-05-17").getVolume(), 2956387);
		} catch(Exception e) {
			System.out.println(e.toString());
			assert false;
		}
	}
	@Test
	public void ConvertToDailyData_List_ExpectParsed() {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// Read JSON file as a string
			String jsonData = new String(Files.readAllBytes(Paths.get("src/test/resources/IBM_daily.json")));

			AlphaVantageTickerData alphaVantageTickerData = objectMapper.readValue(jsonData, AlphaVantageTickerData.class);
			MyTickerData converted = alphaVantageTickerData.convertToMyTickerData();


			assertEquals("IBM", converted.getTicker());
			assertEquals(4, converted.getData().size());
			assertEquals(169.03, converted.getData().get(0).getDailyData().getClose());
			assertEquals(167.86, converted.getData().get(3).getDailyData().getOpen());
		} catch(Exception e) {
			System.out.println(e.toString());
			assert false;
		}
	}
}
