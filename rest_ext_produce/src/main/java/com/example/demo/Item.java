package com.example.demo;

public class Item {

    private final String id;
    private final double price;
	private final boolean newItem;

    public Item(String id, Double price) {
        this.id = id;
        this.price = price;
		this.newItem = true;
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }
	
	public Boolean getNewItem() {
		return newItem;
	}
	
	public String toString() {
        return "Monitor name" + this.id + ""+ "Monitor Price:" + this.price + ",, "+ "Used:" + this.newItem;
    }
	
	
	
	
	
}