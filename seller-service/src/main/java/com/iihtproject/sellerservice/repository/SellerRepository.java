package com.iihtproject.sellerservice.repository;

import com.iihtproject.sellerservice.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends MongoRepository<ProductEntity, String> {
}
