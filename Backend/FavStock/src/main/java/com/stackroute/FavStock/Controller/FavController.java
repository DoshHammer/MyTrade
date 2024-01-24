package com.stackroute.FavStock.Controller;

import com.stackroute.FavStock.Exceptions.FavAlreadyExistsById;
import com.stackroute.FavStock.Exceptions.UserNotFoundException;
import com.stackroute.FavStock.FavStockEntity.FavStockEntity;
import com.stackroute.FavStock.FavStockService.FavStockServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavController {

    private static final Logger logger = LoggerFactory.getLogger(FavController.class);

    @Autowired
    FavStockServiceImpl favStockService;

    @PostMapping("/createFav")
    public ResponseEntity<?> createFavCountry(@RequestBody FavStockEntity obj) throws FavAlreadyExistsById {
        logger.info("Received request to save country {}:", obj);
        favStockService.saveStockCountryToFav(obj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewAllFav")
    public List<FavStockEntity> getAllFav() {
        logger.info("Received request to fetch all countries");
        return favStockService.getAllFav();
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<List<FavStockEntity>> getFavByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        List<FavStockEntity> alldata = favStockService.getUserByUsername(username);
        if (alldata.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(alldata, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{username}/{symbol}")
    public List<FavStockEntity> findEntityByUsernameAndSymbol(@PathVariable String username, @PathVariable String symbol) {
        return favStockService.findEntityByUsernameAndSymbol(username, symbol);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntitiesByUsernameAndSymbol(@PathVariable Long id) {
        favStockService.deleteEntityById(id);
    }
}
