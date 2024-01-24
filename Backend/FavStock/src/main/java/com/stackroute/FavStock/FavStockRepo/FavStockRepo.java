package com.stackroute.FavStock.FavStockRepo;

import com.stackroute.FavStock.FavStockEntity.FavStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavStockRepo extends JpaRepository<FavStockEntity, Long> {
    List<FavStockEntity> findByUsername(String username);

    List<FavStockEntity> findByUsernameAndSymbol(String username, String symbol);
    void deleteByUsernameAndSymbol(String username, String symbol);
//    static Optional<FavStockEntity> findByUsername(String username) {
//    }
    //void deleteTrackByUserIdAndCountry(Long userId, String country);
}
