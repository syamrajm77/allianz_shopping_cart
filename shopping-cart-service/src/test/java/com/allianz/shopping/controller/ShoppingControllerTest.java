package com.allianz.shopping.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.allianz.shopping.model.OrderDetailsInfo;
import com.allianz.shopping.service.ShoppingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class ShoppingControllerTest {

	private static final String URL = "/shopping/order";
	private static final String INAVALID_URL = "/shop/order";
	@InjectMocks
	private ShoppingController controller;
	private ObjectMapper objectMapper;
	private OrderDetailsInfo orderedItemsInfo;

	private MockMvc mockMvc;
	@Mock
	RequestBuilder requestBuilder;
	@Mock
	private ShoppingService shoppingService;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this);
		objectMapper = new ObjectMapper();
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void givenURIIsValidWhenMockMVCThenVerifyResponse() throws Exception {
        Mockito.when(shoppingService.orderShopping(Mockito.any())).thenReturn(null);
		orderedItemsInfo = new OrderDetailsInfo();
		requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(orderedItemsInfo));
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}

	@Test
	public void givenURIIsInValidWhenMockMVCThenVerifyResponse() throws Exception {

		orderedItemsInfo = new OrderDetailsInfo();
		requestBuilder = MockMvcRequestBuilders.post(INAVALID_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(orderedItemsInfo));
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
	}

}
