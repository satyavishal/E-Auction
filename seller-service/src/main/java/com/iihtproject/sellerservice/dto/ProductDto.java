package com.iihtproject.sellerservice.dto;

import com.iihtproject.sellerservice.enums.ProductCategory;
import com.iihtproject.sellerservice.validation.EnumValueValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Data
//@AllArgsConstructor(staticName = "build")
//@NoArgsConstructor
public class ProductDto {

    // Product Details
    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string")
    private String productName;

    @Schema(type = "string")
    private String shortDescription;

    @Schema(type = "string")
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
    @Schema(type = "string")
    private String sellerFirstName;

    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string")
    private String sellerLastName;

    @Schema(type = "string")
    private String sellerAddress;

    @Schema(type = "string")
    private String sellerCity;

    @Schema(type = "string")
    private String sellerState;

    @Schema(type = "string")
    private String sellerPinCode;

    @NotNull
    @Size(min = 10, max = 10)
    @Schema(type = "string")
    private String sellerPhone;

    @NotNull
    @Email
    @Schema( type = "string")
    private String sellerEmail;
}
