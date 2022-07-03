package com.iihtproject.sellerservice.repository;

import com.iihtproject.sellerservice.model.ProductEntity;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends MongoRepository<ProductEntity, String> {
    Optional<ProductEntity> findByIdAndBidEndDateGreaterThan(@Valid String id, Date date);
}
