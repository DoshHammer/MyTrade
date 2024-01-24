package com.stackroute.StockApi.StockService;

import org.springframework.http.ResponseEntity;

public interface StockService {

    public  ResponseEntity<?> getStockByCountryName(String countryName) ;
}
