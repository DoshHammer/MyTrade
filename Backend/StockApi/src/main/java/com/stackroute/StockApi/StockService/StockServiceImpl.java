package com.stackroute.StockApi.StockService;

import com.stackroute.StockApi.StockController.StockController;
import com.stackroute.StockApi.StockEntity.StockEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StockServiceImpl implements StockService{

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
    private final String apiUrl =
            "https://api.twelvedata.com/stocks?apikey=21cc109b74d441269fb3356840c31a51&country=";

    private final RestTemplate restTemplate;

    @Autowired
    public StockServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * @param countryName 
     * @return
     */

    public ResponseEntity<?> getStockByCountryName(String countryName) {
        String url = apiUrl + countryName;
        RestTemplate restTemplate = new RestTemplate();
        StockEntity result = new StockEntity();
        result = restTemplate.getForObject(url,StockEntity.class);
        System.out.println(result.getData());
        logger.info("Successfully retrived all the Stocks available for the Country: ", countryName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
