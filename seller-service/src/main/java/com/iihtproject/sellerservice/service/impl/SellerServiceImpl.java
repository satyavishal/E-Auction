package com.iihtproject.sellerservice.service.impl;

import com.iihtproject.sellerservice.dto.ProductDto;
import com.iihtproject.sellerservice.exception.customException.CustomException;
import com.iihtproject.sellerservice.model.ProductEntity;
import com.iihtproject.sellerservice.repository.SellerRepository;
import com.iihtproject.sellerservice.response.ProductResponse;
import com.iihtproject.sellerservice.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

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
	public ProductResponse showBids(@Valid String id) {
        Optional<ProductEntity> productEntity = sellerRepository.findById(id);
        if (productEntity.isEmpty()) {
            throw new CustomException("Given product id is unavailable");
        }
        
        ProductResponse response = new ProductResponse();
        ProductEntity entity = productEntity.get();
        response.setBidEndDate(entity.getBidEndDate());
        response.setCategory(entity.getCategory());
        response.setDetailedDescription(entity.getDetailedDescription());
        response.setId(entity.getId());
        response.setProductName(entity.getProductName());
        response.setSellerAddress(entity.getSellerAddress());
        response.setSellerCity(entity.getSellerCity());
        response.setSellerEmail(entity.getSellerEmail());
        response.setSellerFirstName(entity.getSellerFirstName());
        response.setSellerLastName(entity.getSellerLastName());
        response.setSellerPhone(entity.getSellerPhone());
        response.setSellerPinCode(entity.getSellerPinCode());
        response.setSellerState(entity.getSellerState());
        response.setShortDescription(entity.getShortDescription());
        response.setStartingPrice(entity.getStartingPrice());
        
        
        //Add List of Bids 
        
        return  response;
	}

	@Override
	public boolean findProductByIdAndBidEndDate(@Valid String id, Date date) {
		Optional<ProductEntity> productEntity = sellerRepository.findByIdAndBidEndDateLessThan(id,date);
		
		 if (productEntity.isEmpty()) {
	            throw new CustomException("Given product id is unavailable");
	        }
	        
		return true;
	}
}
