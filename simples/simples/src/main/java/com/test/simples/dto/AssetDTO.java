package com.test.simples.dto;

import org.modelmapper.ModelMapper;

import com.test.simples.entity.Asset;

public class AssetDTO {
	
	private Long id;

	private String code;

    private double price;
    
    public static AssetDTO convertDTO(Asset asset) {
    	return getModelMapper().map(asset, AssetDTO.class);
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
