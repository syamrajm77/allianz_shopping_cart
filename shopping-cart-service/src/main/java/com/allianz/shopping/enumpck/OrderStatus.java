package com.allianz.shopping.enumpck;

public enum OrderStatus {

	SUCCESS("SUCCESS"), 
    FAILED("FAILED"), 
    DELIVERED("DELIVERED");
 
    private String status;
 
    OrderStatus(String status) {
        this.status = status;
    }
 
    public String getStatus() {
        return status;
    }
}
