package com.iihtproject.buyerservice.repository;

import com.iihtproject.buyerservice.model.BidEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<BidEntity,String> {
}
