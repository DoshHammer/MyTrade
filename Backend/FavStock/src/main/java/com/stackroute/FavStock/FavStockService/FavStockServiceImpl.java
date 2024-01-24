package com.stackroute.FavStock.FavStockService;

import com.stackroute.FavStock.Exceptions.FavAlreadyExistsById;
import com.stackroute.FavStock.Exceptions.UserNotFoundException;
import com.stackroute.FavStock.FavStockEntity.FavStockEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.FavStock.FavStockRepo.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FavStockServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(FavStockServiceImpl.class);
    @Autowired
    FavStockRepo favStockRepo;

    public FavStockEntity saveStockCountryToFav(FavStockEntity favStockEntity) throws FavAlreadyExistsById{
        List<FavStockEntity> existStock = this.favStockRepo.findByUsernameAndSymbol(favStockEntity.getUsername(), favStockEntity.getSymbol());
        if(existStock.isEmpty()) {
            logger.info("Saving country: {}", favStockEntity);
            return favStockRepo.save(favStockEntity);
        }
        else {
            throw new FavAlreadyExistsById("Stock already exists");
        }
    }

    public List<FavStockEntity> getAllFav() {
        logger.info("Fetching all saved countries");
        return favStockRepo.findAll();
    }

    public List<FavStockEntity> getUserByUsername(String username) throws UserNotFoundException {
        return favStockRepo.findByUsername(username);
    }

    public List<FavStockEntity> findEntityByUsernameAndSymbol(String username, String symbol) {
        return favStockRepo.findByUsernameAndSymbol(username, symbol);
    }

    public void deleteEntityByUsernameAndSymbol(String username, String symbol) {
        favStockRepo.deleteByUsernameAndSymbol(username, symbol);
    }
    public void deleteEntityById(Long id) {
        favStockRepo.deleteById(id);
    }
}
