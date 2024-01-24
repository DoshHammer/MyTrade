package com.stackroute.StockApi.TestController;

import com.stackroute.StockApi.StockController.StockController;
import com.stackroute.StockApi.StockEntity.StockEntity;
import com.stackroute.StockApi.StockService.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StockControllerTest {
    @Mock
    StockServiceImpl stockService;

    @InjectMocks
    StockController stockController;

    StockEntity testEntity;

    @BeforeEach
    public void setUp() {
        ArrayList new1 = new ArrayList();
        testEntity = new StockEntity();
        testEntity.setData(new1);
    }

    @Test
    public void testGetStocksByCountryNameSuccess() {
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(new Object());
//        when(stockService.getStockByCountryName(anyString())).thenReturn();
        doReturn(expectedResponse).when(stockService).getStockByCountryName("USA");
        ResponseEntity<?> responseEntity = stockController.getStocksByCountryName("USA");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(stockService, times(1)).getStockByCountryName("USA");
    }

    @Test
    public void testGetStockByCountrName_Exception() {
        when(stockService.getStockByCountryName(anyString())).thenThrow(new RuntimeException("Api Not Working"));

        ResponseEntity<?> responseEntity = stockController.getStocksByCountryName("InvalidCountry");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error", responseEntity.getBody());

        verify(stockService, times(1)).getStockByCountryName("InvalidCountry");
    }
}
