package com.iihtproject.sellerservice.service.impl;

import com.iihtproject.sellerservice.dto.ProductDto;
import com.iihtproject.sellerservice.exception.customException.CustomException;
import com.iihtproject.sellerservice.model.ProductEntity;
import com.iihtproject.sellerservice.repository.SellerRepository;
import com.iihtproject.sellerservice.response.ProductBidsVO;
import com.iihtproject.sellerservice.response.ProductResponse;
import com.iihtproject.sellerservice.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class SellerServiceImpl implements SellerService {

    private String URI = "http://localhost:9001/e-auction/api/v1/buyer";
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ProductResponse addProduct(ProductDto productDto){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto,productEntity);
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(sellerRepository.save(productEntity), productResponse);
        return productResponse;
    }

    @Override
    public boolean deleteProduct(String productId) {
        Optional<ProductEntity> productEntity = sellerRepository.findById(productId);
        if (productEntity.isEmpty()) {
            throw new CustomException("Given product id is unavailable");
        }

        ProductEntity product = productEntity.get();

        Date currentDate = new Date();
        if (currentDate.compareTo(product.getBidEndDate()) > 0) {
            throw new CustomException("Product cannot be deleted after bid end date");
        }

//        int numOfBids = bidRepository.countNumberOfBidsForProduct(productId);
//        if (numOfBids > 0) {
//            throw new InvalidInputException("At least one Bid has been placed on the product");
//        }
        sellerRepository.deleteById(productId);
        
        return true;
    }

	@Override
	public ProductBidsVO showBids(@Valid String id) {
        Optional<ProductEntity> productEntity = sellerRepository.findById(id);
        if (productEntity.isEmpty()) {
            throw new CustomException("Given product id is unavailable");
        }
        ProductResponse productResponse = new ProductResponse();
        ProductEntity entity = productEntity.get();
        BeanUtils.copyProperties(entity,productResponse);
        //Add List of Bids 
        Object[] bidsList =
                restTemplate.getForObject(URI+"/getBids/"+id,Object[].class);
        return new ProductBidsVO(productResponse, Arrays.asList(bidsList));
	}

	@Override
	public boolean findProductByIdAndBidEndDate(@Valid String id, Date date) {
		Optional<ProductEntity> productEntity = sellerRepository.findByIdAndBidEndDateGreaterThan(id,date);
		
		 if (productEntity.isEmpty()) {
	            return false;
	        }
	        
		return true;
	}
}
