package com.allianz.shopping.model;

import java.util.List;


public class OrderDetailsInfo {

    private Integer userId;
    private String offerStatus;
    
    List<OrderedItemsInfo> orderedItemsInfo;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<OrderedItemsInfo> getOrderedItemsInfo() {
		return orderedItemsInfo;
	}
	public void setOrderedItemsInfo(List<OrderedItemsInfo> orderedItemsInfo) {
		this.orderedItemsInfo = orderedItemsInfo;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	
    
}
