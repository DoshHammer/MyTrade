package com.stackroute.StockApi.StockEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Data {

    private String symbol;
    private String name;
    private String currency;
    private String exchange;
    private String micCode;
    private String country;
    private String type;

}
