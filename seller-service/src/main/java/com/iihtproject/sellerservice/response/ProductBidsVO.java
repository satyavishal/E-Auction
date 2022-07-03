package com.iihtproject.sellerservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBidsVO {
    private ProductResponse productResponse;
    private List<?> bids;
}
