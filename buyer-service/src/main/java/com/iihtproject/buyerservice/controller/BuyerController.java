package com.iihtproject.buyerservice.controller;

import com.iihtproject.buyerservice.dto.BidDto;
import com.iihtproject.buyerservice.response.BidResponse;
import com.iihtproject.buyerservice.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/e-auction/api/v1/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/place-bid")
    public  ResponseEntity<BidResponse> placeBid(@Valid @RequestBody BidDto bidDto){
        return new ResponseEntity<>(buyerService.placeBid(bidDto),HttpStatus.OK);
    }

    @PutMapping("/update-bid/{productId}/{buyerEmailId}/{newBidAmount}")
    public ResponseEntity<BidResponse> updateBid(@PathVariable("productId") String productId,
                                            @PathVariable("buyerEmailId") String buyerEmail,
                                            @PathVariable("newBidAmount") Double newBidAmount){
        return new ResponseEntity<>(buyerService.updateBid(productId,buyerEmail,newBidAmount),HttpStatus.OK);
    }

    @GetMapping("/getBids/{productId}")
    public ResponseEntity<List<BidResponse>> getAllBids(@PathVariable("productId") String productId){
        return new ResponseEntity<>(buyerService.getBids(productId),HttpStatus.OK);
    }
}
