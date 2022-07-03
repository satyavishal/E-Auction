package com.iihtproject.buyerservice.repository;

import com.iihtproject.buyerservice.model.BidEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BuyerRepository extends MongoRepository<BidEntity,String> {
    BidEntity findByProductIdAndBuyerEmail(String productId,String buyerEmail);

    List<BidEntity> findALlByProductId(String productId);

    Optional<BidEntity> findByBuyerEmail(String buyerEmail);
}
