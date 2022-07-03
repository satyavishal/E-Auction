package com.iihtproject.sellerservice.service;

import com.iihtproject.sellerservice.dto.ProductDto;
import com.iihtproject.sellerservice.response.ProductResponse;

public interface SellerService {
    ProductResponse addProduct(ProductDto productDto);
    void deleteProduct(String productId);
}
