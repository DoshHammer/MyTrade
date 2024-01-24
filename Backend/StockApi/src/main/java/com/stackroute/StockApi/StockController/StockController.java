package com.stackroute.StockApi.StockController;

import com.stackroute.StockApi.StockService.StockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);
    @Autowired
    StockServiceImpl stockService;
    @GetMapping("/country/{countryName}")
    public ResponseEntity<?> getStocksByCountryName(@PathVariable String countryName) {
        try {
            logger.info("Received request to fetch Stock details of the Country:", countryName);
            return stockService.getStockByCountryName(countryName);
        } catch (Exception e) {
            logger.info("API not responding");
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
