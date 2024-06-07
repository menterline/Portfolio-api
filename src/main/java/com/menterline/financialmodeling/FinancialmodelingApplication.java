package com.menterline.financialmodeling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.menterline.financialmodeling.models.AlphaVantageTickerData;
import com.menterline.financialmodeling.models.MyTickerData;
import com.menterline.financialmodeling.models.MyTickerDataNode;
import com.menterline.financialmodeling.services.AlphaVantageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
			ArrayList<MyTickerData> allData = new ArrayList<MyTickerData>();
			for (String ticker : tickers) {
				LocalDate currentDate= LocalDate.now();
				ArrayList<MyTickerDataNode> nodes = new ArrayList<MyTickerDataNode>();
				for (int i = 0; i < 100; i++) {
					LocalDate localDate = currentDate.minusDays(100 - i);
			        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					MyTickerDataNode node = new MyTickerDataNode(Math.random() * 100, Math.random() * 100, Math.random() * 100, Math.random() * 100, Math.random() * 100, date);
					nodes.add(node);
				}
				MyTickerData data = new MyTickerData(ticker, nodes);
				allData.add(data);
			}
			return allData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
