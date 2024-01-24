package com.stackroute.FavStock.TestFavStockService;

import com.stackroute.FavStock.FavStockEntity.FavStockEntity;
import com.stackroute.FavStock.FavStockRepo.FavStockRepo;
import com.stackroute.FavStock.FavStockService.FavStockServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class FavStockServiceImplTest {

    @Mock
    private FavStockRepo favStockRepo;
    @InjectMocks
    private FavStockServiceImpl favStockService;

//    @Test
//    public void testSaveCountry() {
//
//        FavStockEntity favStockEntity = new FavStockEntity("TestCountry", 1L);
//        when(favStockRepo.save(Mockito.any(FavStockEntity.class))).thenReturn(favStockEntity);
//
//        FavStockEntity savedCountry = favStockService.saveStockCountryToFav(favStockEntity);
//
//        assertEquals(favStockEntity.getCountries(), savedCountry.getCountries());
//        assertEquals(favStockEntity.getUserId(), savedCountry.getUserId());
//    }

//    @Test
//    public void testGetAllCountries() {
//        FavStockEntity favStockEntity = new FavStockEntity("TestCountry", 1L);
//        when(favStockRepo.findAll()).thenReturn(Collections.singletonList(favStockEntity));
//
//        List<FavStockEntity> countries = favStockService.getAllFav();
//
//        assertEquals(1, countries.size());
//        assertEquals(favStockEntity.getCountries(), countries.get(0).getCountries());
//        assertEquals(favStockEntity.getUserId(), countries.get(0).getUserId());
//    }
}
