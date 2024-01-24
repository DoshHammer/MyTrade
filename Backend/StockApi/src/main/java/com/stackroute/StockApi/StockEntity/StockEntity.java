package com.stackroute.StockApi.StockEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data @NoArgsConstructor @AllArgsConstructor
public class StockEntity {

    private ArrayList<com.stackroute.StockApi.StockEntity.Data> data = new ArrayList<>();

}
