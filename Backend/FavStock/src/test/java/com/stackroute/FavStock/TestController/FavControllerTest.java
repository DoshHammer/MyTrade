//package com.stackroute.FavStock.TestController;
//
//import com.stackroute.FavStock.Controller.FavController;
//import com.stackroute.FavStock.FavStockEntity.FavStockEntity;
//import com.stackroute.FavStock.FavStockService.FavStockServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//public class FavControllerTest {
//
//    @MockBean
//    private FavStockServiceImpl favStockService;
//
//    @InjectMocks
//    private FavController favController;
//
//   // MockMvc mockmvc;
//
//    FavStockEntity favStockEntity = new FavStockEntity();
////
////    private String convertObject(Object obj) throws JsonProcessingException	{
////        ObjectMapper objmap=new ObjectMapper();
////        return	objmap.writeValueAsString(obj);
////    }
////
////    @BeforeEach
////    public void initializedata() {
////        MockitoAnnotations.openMocks(this);
////        mockmvc = MockMvcBuilders.standaloneSetup(favController).build();
////
////        List<String> testList = new ArrayList<>();
////            testList.add("India");
////            testList.add("UK");
////
////        favStockEntity.setId(1L);
////        favStockEntity.setUserId(101L);
////        favStockEntity.setCountries(testList);
////    }
////
////    @Test
////    public void whenaddCountrystoredsuccess() throws Exception {
////        when(favStockService.saveStockCountryToFav(favStockEntity)).thenReturn(favStockEntity);
////
////        mockmvc.perform(MockMvcRequestBuilders.post("/favorite/createFav")
////                .contentType(MediaType.APPLICATION_JSON)
////                        .contentType(convertObject(favStockEntity)).andExpect());
////    }
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testSaveCountry() throws Exception {
//        String countryJson = "{\"CountryName\":'[\"TestCountry\"]',\"userId\":1}";
//        when(favStockService.saveStockCountryToFav(Mockito.any(FavStockEntity.class)))
//                .thenReturn(new FavStockEntity("TestCountry", 1L));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/createFav").content(countryJson)
//                .contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.countryName").value("TestCountry"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1));
//    }
//}
