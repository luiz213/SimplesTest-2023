package com.test.simples.dto;

import java.sql.Date;
import org.modelmapper.ModelMapper;
import com.test.simples.entity.Asset;
import com.test.simples.entity.Order;

public class OrderDTO {
	
	private Long id;

    private Asset asset;
    
    private int type;
    
    private int quantity;

    private double price;
    
    private Date date;

    public static OrderDTO convertToDTO(Order order) {
    	return getModelMapper().map(order, OrderDTO.class);
    }
    
    static ModelMapper getModelMapper() {
		return new ModelMapper();
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	} 
}
