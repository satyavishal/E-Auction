package com.iihtproject.sellerservice.controller;

import com.iihtproject.sellerservice.dto.ProductDto;
import com.iihtproject.sellerservice.response.ProductResponse;
import com.iihtproject.sellerservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/product")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World",HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse>addProduct(@Valid @RequestBody ProductDto productDto){
        return new ResponseEntity<>(sellerService.addProduct(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId) {
        sellerService.deleteProduct(productId);
        return new ResponseEntity<>("Product Delete Successfully", HttpStatus.OK);
    }
}
