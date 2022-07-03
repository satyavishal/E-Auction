package com.iihtproject.buyerservice.service.impl;

import com.iihtproject.buyerservice.dto.BidDto;
import com.iihtproject.buyerservice.exception.customException.CustomException;
import com.iihtproject.buyerservice.model.BidEntity;
import com.iihtproject.buyerservice.repository.BuyerRepository;
import com.iihtproject.buyerservice.response.BidResponse;
import com.iihtproject.buyerservice.service.BuyerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerRepository buyerRepository;
    @Override
    public BidResponse placeBid(BidDto bidDto) {
        Optional<BidEntity> optionalBidEntity= buyerRepository.findByBuyerEmail(bidDto.getBuyerEmail());
        if(optionalBidEntity.isPresent()){
            throw new CustomException("A Buyer has already place the Bid with Email-ID: "+bidDto.getBuyerEmail());
        }
        BidEntity bidEntity = new BidEntity();
        BeanUtils.copyProperties(bidDto,bidEntity);
        BidResponse bidResponse = new BidResponse();
        BeanUtils.copyProperties(buyerRepository.save(bidEntity),bidResponse);
        return bidResponse;
    }

    @Override
    public BidResponse updateBid(String productId, String buyerEmail, Double newBidAmount) {
//        Criteria criteria = new Criteria();
//        criteria.andOperator(Criteria.where("productId").is(bidDto.getProductId()),
//                Criteria.where("buyerEmail").is(bidDto.getBuyerEmail()));
//        Query query = new Query(criteria);
        BidEntity bidEntity = buyerRepository.findByProductIdAndBuyerEmail(productId, buyerEmail);
        bidEntity.setBidAmount(newBidAmount);
        BidResponse bidResponse = new BidResponse();
        BeanUtils.copyProperties(buyerRepository.save(bidEntity),bidResponse);
        return bidResponse;
    }

    @Override
    public List<BidResponse> getBids(String productId) {
        List<BidEntity> bidEntities = buyerRepository.findALlByProductId(productId);
        List<BidResponse> bidResponses = new ArrayList<>();
        bidEntities.forEach(bidEntity -> {
            BidResponse biResponse = new BidResponse();
            BeanUtils.copyProperties(bidEntity,biResponse);
            bidResponses.add(biResponse);
        });
        return bidResponses;
    }
}
