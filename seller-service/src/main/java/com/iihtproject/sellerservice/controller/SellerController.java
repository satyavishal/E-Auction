package com.iihtproject.sellerservice.controller;

import com.iihtproject.sellerservice.dto.ProductDto;
import com.iihtproject.sellerservice.response.ProductBidsVO;
import com.iihtproject.sellerservice.response.ProductResponse;
import com.iihtproject.sellerservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/e-auction/api/v1/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World",HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse>addProduct(@Valid @RequestBody ProductDto productDto){
        return new ResponseEntity<>(sellerService.addProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/show-bids/{productId}")
    public ResponseEntity<ProductBidsVO> showBidsForProduct(@PathVariable("productId") String id){
    	return new ResponseEntity<>(sellerService.showBids(id), HttpStatus.OK);
    }
    
    @GetMapping("/find-product/{productId}/{bidEndDate}")
    public ResponseEntity<Boolean> findProductByIdAndBidEndDate(@PathVariable("productId")String id, @PathVariable("bidEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
    	return new ResponseEntity<>(sellerService.findProductByIdAndBidEndDate(id,date), HttpStatus.OK);

    }
    @PostMapping("/addBids/{productId}")
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") String productId) {
        return new ResponseEntity<>(sellerService.deleteProduct(productId), HttpStatus.OK);
    }
}
