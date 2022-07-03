package com.iihtproject.buyerservice.response;

import lombok.Data;

@Data
public class BidResponse {
    private String buyerFirstName;
    private String buyerLastName;
    private String buyerAddress;
    private String buyerCity;
    private String buyerState;
    private String buyerPinCode;
    private String buyerPhone;
    private String buyerEmail;
    private String productId;
    private Double bidAmount;
}
