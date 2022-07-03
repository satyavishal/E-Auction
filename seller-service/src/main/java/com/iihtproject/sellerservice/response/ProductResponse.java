package com.iihtproject.sellerservice.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProductResponse {
    private String id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
    private Double startingPrice;
    private Date bidEndDate;
    private String sellerFirstName;
    private String sellerLastName;
    private String sellerAddress;
    private String sellerCity;
    private String sellerState;
    private String sellerPinCode;
    private String sellerPhone;
    private String sellerEmail;
}
