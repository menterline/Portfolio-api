package com.menterline.financialmodeling.services;

import com.menterline.financialmodeling.models.AlphaVantageTickerData;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class AlphaVantageService {

    private final WebClient webClient;

    public AlphaVantageService(Function<String, WebClient> buildWebClient) {
        this.webClient = buildWebClient.apply("https://www.alphavantage.co");
    }


    public AlphaVantageTickerData getDailyTimeSeries(String ticker, String apiKey) {
        Mono<String> test = webClient.get().uri(uriBuilder -> uriBuilder
                .path("/query")
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", ticker)
                .queryParam("apikey", apiKey).build()).retrieve().bodyToMono(String.class);

        String jsonResponse = test.block();

        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/query")
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", ticker)
                .queryParam("apikey", apiKey)
                .build())
                .retrieve()
                .bodyToMono(AlphaVantageTickerData.class).block();
    }
}
