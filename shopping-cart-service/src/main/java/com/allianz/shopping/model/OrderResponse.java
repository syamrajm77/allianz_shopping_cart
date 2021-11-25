package com.allianz.shopping.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.allianz.shopping.entity.OrderedItems;

public class OrderResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String checkoutId;
	private String message;
	private String checkoutDateTime;
	private Double totalAmount;
	//private List<OrderedItems> itemsList;

	public String getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(String checkoutId) {
		this.checkoutId = checkoutId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getCheckoutDateTime() {
		return checkoutDateTime;
	}

	public void setCheckoutDateTime(String checkoutDateTime) {
		this.checkoutDateTime = checkoutDateTime;
	}

	/*public List<OrderedItems> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<OrderedItems> itemsList) {
		this.itemsList = itemsList;
	}
*/
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
