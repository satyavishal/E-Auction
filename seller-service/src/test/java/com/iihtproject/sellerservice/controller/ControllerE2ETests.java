package com.iihtproject.sellerservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iihtproject.sellerservice.SellerServiceApplication;
import com.iihtproject.sellerservice.dto.CreateProductTest;
import com.iihtproject.sellerservice.dto.ProductDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellerServiceApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ControllerE2ETests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private final CreateProductTest createProductTest = new CreateProductTest();
	
	@Test
	@DisplayName("PRODUCT CREATION - SUCCESS")
	@WithMockUser(roles = {"ADMIN"})
	public void should_create_product() throws Exception {
		ProductDto product = createProductTest.should_create_new_product(); 
		
		String body = objectMapper.writeValueAsString(product);
		mockMvc
		.perform(MockMvcRequestBuilders.post("/e-auction/api/v1/seller/add-product").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(body))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated());
		
	}
	
	@Test
	@DisplayName("PRODUCT Deletion - SUCCESS")
	@WithMockUser(roles = {"ADMIN"})
	public void should_delete_product_by_id() throws Exception {
		
		JSONObject createdProduct = createProduct(createProductTest.should_create_new_product()); 
		String id = createdProduct.getString("id");
		mockMvc
		.perform(MockMvcRequestBuilders.delete("/e-auction/api/v1/seller/delete/"+id).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
		
		
	}
	
	@Test
	@DisplayName("PRODUCT BID RETRIEVE - SUCCESS")
	@WithMockUser(roles = {"ADMIN"})
	public void should_return_all_bids_of_product() throws Exception {
		
		JSONObject createdProduct = createProduct(createProductTest.should_create_new_product()); 
		String id = createdProduct.getString("id");
		mockMvc
		.perform(MockMvcRequestBuilders.get("/e-auction/api/v1/seller/show-bids/"+id).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("PRODUCT BID RETRIEVE - SUCCESS")
	@WithMockUser(roles = {"ADMIN"})
	public void should_find_product_by_id_and_date() throws Exception {
		
		JSONObject createdProduct = createProduct(createProductTest.should_create_new_product()); 
		String id = createdProduct.getString("id");
		mockMvc
		.perform(MockMvcRequestBuilders.get("/e-auction/api/v1/seller/find-product/"+id+"/2022-12-12").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	
	
	
	private JSONObject createProduct(ProductDto product) throws Exception {
		String body = objectMapper.writeValueAsString(product);
		String response = mockMvc
		.perform(MockMvcRequestBuilders.post("/e-auction/api/v1/seller/add-product").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(body))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated()).andReturn().getResponse()
		.getContentAsString();
		
		return new JSONObject(response);
	}

}
