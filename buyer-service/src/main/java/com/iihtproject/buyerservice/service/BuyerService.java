package com.iihtproject.buyerservice.service;

import com.iihtproject.buyerservice.dto.BidDto;
import com.iihtproject.buyerservice.response.BidResponse;

import java.util.List;

public interface BuyerService {
    BidResponse placeBid(BidDto bidDto);
    BidResponse updateBid(String productId, String buyerEmail, Double newBidAmount);
    List<BidResponse> getBids(String productId);
}
