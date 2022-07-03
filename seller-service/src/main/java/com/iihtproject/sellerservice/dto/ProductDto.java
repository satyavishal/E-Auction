package com.iihtproject.sellerservice.dto;

import com.iihtproject.sellerservice.enums.ProductCategory;
import com.iihtproject.sellerservice.validation.EnumValueValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ProductDto {

    // Product Details
    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string", example = "Lamp Stand")
    private String productName;

    @Schema(type = "string", example = "Ancient Lamp Stand")
    private String shortDescription;

    @Schema(type = "string", example = "Used for lightening up the area")
    private String detailedDescription;
    
    @EnumValueValidator(enumClass = ProductCategory.class, message = "Must be a Valid Product Category")
    private String category;

    @Min(value=1, message="numericField: positive number, min 1 is required")
    @Digits(message = "Starting Price must be number", integer = 7, fraction = 0)
    @Schema(type = "double", example = "10000")
    private double startingPrice;

    @Future
    @Schema(type = "string", example = "2022-08-22")
    private Date bidEndDate;

    // Seller Details
    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string", example = "Satya")
    private String sellerFirstName;

    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string", example = "Thota")
    private String sellerLastName;

    @Schema(type = "string", example = "B-6, Pradama Palace")
    private String sellerAddress;

    @Schema(type = "string", example = "Visakhapatnam")
    private String sellerCity;

    @Schema(type = "string", example = "Andhra Pradesh")
    private String sellerState;

    @Schema(type = "string", example = "530068")
    private String sellerPinCode;

    @NotNull
    @Size(min = 10, max = 10)
    @Schema(type = "string", example = "9492860779")
    private String sellerPhone;

    @NotNull
    @Email
    @Schema( type = "string", example = "satya.thota@gmail.com")
    private String sellerEmail;
}
