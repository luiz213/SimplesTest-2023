package com.test.simples.entity;

import java.sql.Date;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import utils.DateUtil;

@Entity
@Table(name ="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(name = "ASSET_ID", referencedColumnName = "ID", nullable = false) 
    @ManyToOne
    private Asset asset;

    @Column(name="TYPE", nullable=false)
    private int type;

    @Column(name="QUANTITY", nullable=false)
    private int quantity;

    @Column(name="PRICE", nullable=false)
    private double price;

    @Column(name="ORDERS_DATE", nullable=false)
    private Date date;
    
    public Order(){
    	this.date = DateUtil.asDate(LocalDate.now());
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Asset getAssetCode() {
		return asset;
	}

	public void setAssetCode(Asset asset) {
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
