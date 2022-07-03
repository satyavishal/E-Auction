package com.iihtproject.sellerservice.controller;

import com.iihtproject.sellerservice.dto.ProductDto;
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

    @GetMapping("show-bids/{productId}")
    public ResponseEntity<ProductResponse> showBidsForProduct(@Valid @PathVariable("productId") String id){
    	
    	ProductResponse response = sellerService.showBids(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    	
    }
    
    @GetMapping("find-product/{productId}/{bidEndDate}")
    public ResponseEntity<Boolean> findProductByIdAndBidEndDate(@Valid @PathVariable("productId")String id, @PathVariable("bidEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
    	boolean response = sellerService.findProductByIdAndBidEndDate(id,date);
    	return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") String productId) {
        boolean deleted = sellerService.deleteProduct(productId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
