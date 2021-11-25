package com.allianz.shopping.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allianz.shopping.entity.OrderDetails;
import com.allianz.shopping.entity.OrderedItems;
import com.allianz.shopping.model.OrderDetailsInfo;
import com.allianz.shopping.model.OrderResponse;
import com.allianz.shopping.repository.OrderDetailsRepository;
import com.allianz.shopping.utils.OrderUtils;

@Service
public class ShoppingService {
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	/**
	 * orderShopping
	 * 
	 * @param orderedItemsInfo
	 * @return
	 */
	@Transactional
	public OrderResponse orderShopping(OrderDetailsInfo orderedItemsInfo) {

		String orderId = OrderUtils.getUniqueId();
		OrderDetails orderDetails = OrderUtils.buildOrderDetails(orderedItemsInfo, orderId);
		List<OrderedItems> itemsList = OrderUtils.getUniqueOrderItems(orderedItemsInfo, orderDetails);
		orderDetails.setOrderedItems(itemsList);
		Double totalPrice = OrderUtils.calculateTotalAmount(itemsList);
		orderDetails.setTotalPrice(totalPrice);

		orderDetails = orderDetailsRepository.saveAndFlush(orderDetails);
		return OrderUtils.buildResponse(orderId, orderDetails, totalPrice);
	}

}
