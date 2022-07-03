package com.iihtproject.sellerservice.service;

import java.util.Date;

import javax.validation.Valid;

import com.iihtproject.sellerservice.dto.ProductDto;
import com.iihtproject.sellerservice.response.ProductBidsVO;
import com.iihtproject.sellerservice.response.ProductResponse;

public interface SellerService {
    
	ProductResponse addProduct(ProductDto productDto);
    
    boolean deleteProduct(String productId);

	ProductBidsVO showBids(@Valid String id);

	boolean findProductByIdAndBidEndDate(@Valid String id, Date date);
}
