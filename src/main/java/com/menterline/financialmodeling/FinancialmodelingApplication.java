package com.menterline.financialmodeling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menterline.financialmodeling.models.AlphaVantageTickerData;
import com.menterline.financialmodeling.models.MyTickerData;
import com.menterline.financialmodeling.services.AlphaVantageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Function;

@SpringBootApplication
@RestController
public class FinancialmodelingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialmodelingApplication.class, args);
	}


	/*
	This will use the AlphaVantage API 'TIME_SERIES_DAILY'
	takes multiple tickers
	 */
	@GetMapping("/timeSeriesDaily")
	public ArrayList<MyTickerData> getTickerData(@RequestParam(value = "tickers", defaultValue = "") String[] tickers) {
		//parse url params into array of tickers
		//call alphavantage API for each ticker
		//create a mapper that takes AV response to make it something usable - a TickerResponse
        try {
//        	String apikey = System.getenv("ALPHAVANTAGE_KEY");
//			Function<String, WebClient> webClientProvider = baseUrl -> WebClient.builder().baseUrl(baseUrl).build();
//			AlphaVantageService service = new AlphaVantageService(webClientProvider);
//			ArrayList<MyTickerData> tickerData = new ArrayList<>();
//			for (String ticker : tickers) {
//				AlphaVantageTickerData response = service.getDailyTimeSeries(ticker, apikey);
//				if (response == null) {
//					throw new RuntimeException("No Response from AlphaVantage");
//				}
//				tickerData.add(response.convertToMyTickerData());
//			}
//			return tickerData;
//			Test code to skip calling AlphaVantage API
			ObjectMapper objectMapper = new ObjectMapper();
			// Read JSON file as a string
			String jsonData = new String(Files.readAllBytes(Paths.get("src/test/resources/IBM_daily.json")));

			AlphaVantageTickerData alphaVantageTickerData = objectMapper.readValue(jsonData, AlphaVantageTickerData.class);
			MyTickerData converted = alphaVantageTickerData.convertToMyTickerData();
			ArrayList<MyTickerData> test = new ArrayList<MyTickerData>();
			test.add(converted);
			return test;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
