package com.allianz.shopping.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.allianz.shopping.constant.ShoppingConstants;
import com.allianz.shopping.entity.OrderDetails;
import com.allianz.shopping.entity.OrderedItems;
import com.allianz.shopping.enumpck.OrderStatus;
import com.allianz.shopping.model.OrderDetailsInfo;
import com.allianz.shopping.model.OrderResponse;
import com.allianz.shopping.model.OrderedItemsInfo;

/**
 * OrderUtils
 * 
 * Utility methods for shopping
 * 
 * @author syam
 * @since 24-11-2021
 *
 */
public class OrderUtils {

	/**
	 * private constructor to avoid creating objects
	 */
	private OrderUtils() {

	}

	/**
	 * 
	 * @return uniqueId
	 */
	public static String getUniqueId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static OrderResponse buildResponse(String orderId, OrderDetails orderDetails, Double totalPrice) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setCheckoutId(orderId);
		String formattedDate = orderDetails.getOrderDate()
				.format(DateTimeFormatter.ofPattern(ShoppingConstants.DATE_FORMAT));
		orderResponse.setCheckoutDateTime(formattedDate);
		orderResponse.setTotalAmount(totalPrice);
		orderResponse.setMessage(ShoppingConstants.TOTAL_AMOUNT_MESSAGE + totalPrice);
		return orderResponse;
	}

	public static OrderDetails buildOrderDetails(OrderDetailsInfo orderedItemsInfo, String orderId) {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderNum(orderId);
		orderDetails.setUserId(orderedItemsInfo.getUserId());
		orderDetails.setOrderDate(LocalDateTime.now());
		orderDetails.setOrderStatus(OrderStatus.SUCCESS.name());
		return orderDetails;
	}

	/**
	 * Calculating all the product prize
	 * 
	 * @param itemsList
	 * @return
	 */
	public static Double calculateTotalAmount(List<OrderedItems> itemsList) {
		return itemsList.stream().mapToDouble(i -> i.getPrice()).sum();
	}

	/**
	 * getUniqueOrderItems
	 * 
	 * @param orderedItemsInfo
	 * @return Set<OrderedItems>
	 */
	public static List<OrderedItems> getUniqueOrderItems(final OrderDetailsInfo orderedItemsInfo,
			OrderDetails orderDetails) {

		Map<String, OrderedItems> uniqueItems = new HashMap<String, OrderedItems>();

		if (null != orderedItemsInfo && !CollectionUtils.isEmpty(orderedItemsInfo.getOrderedItemsInfo())) {
			orderedItemsInfo.getOrderedItemsInfo().forEach(item -> {
				String productName = item.getProductName();
				OrderedItems orderedItem = uniqueItems.get(item.getProductName());
				if (null != orderedItem) {
					orderedItem.setQuanity(orderedItem.getQuanity() + 1);
					orderedItem.setPrice(item.getPrice() + orderedItem.getPrice());
				} else {
					OrderedItems items = buildOrderedItem(orderDetails, item, productName);
					uniqueItems.put(productName, items);
				}
			});
		}

		return uniqueItems.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
	}

	public static OrderedItems buildOrderedItem(OrderDetails orderDetails, OrderedItemsInfo item, String productName) {
		OrderedItems items = new OrderedItems();
		items.setProductName(productName);
		items.setPrice(item.getPrice());
		items.setQuanity(1);
		items.setOrderDetails(orderDetails);
		return items;
	}

}
