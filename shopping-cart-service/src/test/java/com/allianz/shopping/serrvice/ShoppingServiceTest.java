package com.allianz.shopping.serrvice;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.allianz.shopping.model.OrderDetailsInfo;
import com.allianz.shopping.model.OrderResponse;
import com.allianz.shopping.model.OrderedItemsInfo;
import com.allianz.shopping.repository.OrderDetailsRepository;
import com.allianz.shopping.service.ShoppingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingServiceTest {

	private static final Double DOUBLE_2 = 2.0;
	private ShoppingService shoppingService;
	private OrderDetailsInfo orderDetailsInfo;
	private List<OrderedItemsInfo> orderedItems;
	@Mock
	private OrderDetailsRepository orderDetailsRepository;

	@BeforeEach
	void setUp() {
		shoppingService = new ShoppingService();
	}

	@Test
	public void givenRequestIsValidShoundReturnResponseWithValidTotalAmountWhenInvokingOrderShoping() {
		
		orderDetailsInfo = new OrderDetailsInfo();
		orderDetailsInfo.setUserId(123);
		orderedItems = new ArrayList<OrderedItemsInfo>();

		orderedItems.add(addOrderedItem(1.0, "Bread"));
		orderedItems.add(addOrderedItem(.40, "Milk"));
		orderedItems.add(addOrderedItem(.60, "Butter"));

		orderDetailsInfo.setOrderedItemsInfo(orderedItems);
		OrderResponse response = shoppingService.orderShopping(orderDetailsInfo);
		Assert.assertNotNull(response);
		Assert.assertEquals(DOUBLE_2, response.getTotalAmount());
	}

	private OrderedItemsInfo addOrderedItem(double prize, String productName) {
		OrderedItemsInfo orderedItemsInfo = new OrderedItemsInfo();
		orderedItemsInfo.setPrice(prize);
		orderedItemsInfo.setProductName(productName);
		return orderedItemsInfo;
	}
}
