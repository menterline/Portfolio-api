package com.menterline.financialmodeling;

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
import java.util.function.Function;

@SpringBootApplication
@RestController
public class FinancialmodelingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialmodelingApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	/*
	This will use the AlphaVantage API 'TIME_SERIES_DAILY'
	takes multiple tickers
	 */
	@GetMapping("/timeSeriesDaily")
	public MyTickerData getTickerData(@RequestParam(value = "tickers", defaultValue = "") String[] tickers) {
		//parse url params into array of tickers
		//call alphavantage API for each ticker
		//create a mapper that takes AV response to make it something usable - a TickerResponse
        try {
			Function<String, WebClient> webClientProvider = baseUrl -> WebClient.builder().baseUrl(baseUrl).build();
			AlphaVantageService service = new AlphaVantageService(webClientProvider);

			AlphaVantageTickerData response = service.getDailyTimeSeries("IBM", "IPWCPZ1EZ7YVJRC5");
			if (response == null) {
				throw new RuntimeException("No Response from AlphaVantage");
			}
			return response.convertToMyTickerData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
