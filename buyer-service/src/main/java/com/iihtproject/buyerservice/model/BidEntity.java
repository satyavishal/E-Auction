package com.iihtproject.buyerservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "bids")
@Getter
@Setter
public class BidEntity {
    @Id
    private String id;
    @Field
    private String buyerFirstName;

    @Field
    private String buyerLastName;

    @Field
    private String buyerAddress;

    @Field
    private String buyerCity;

    @Field
    private String buyerState;

    @Field
    private String buyerPinCode;

    @Field
    private String buyerPhone;

    @Field
    private String buyerEmail;

    @Field
    private String productId;

    @Field
    private Double bidAmount;
}
