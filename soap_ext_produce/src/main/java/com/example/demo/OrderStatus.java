package com.example.demo;

public class OrderStatus {

    private  String status;
    private  String warehouse;

    public OrderStatus(Double price) {
    	  
		if(price < 100) {
			this.status = "0";
			this.warehouse = " A";
		
		}else if(price >= 100 && price <=200) {
			this.status = "0";
			this.warehouse = " B";
			
		}else if (price >200) {
			this.status = "1";
			this.warehouse = "Can't fullfill request";
		}
	
	
	
    }

    public String getStatus() {
        return status;
    }

    public String getWarehouse() {
        return warehouse;
    }
	

	
	
	
	
}