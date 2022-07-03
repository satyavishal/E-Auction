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
    public void deleteProduct(String productId) {
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
    }
}
