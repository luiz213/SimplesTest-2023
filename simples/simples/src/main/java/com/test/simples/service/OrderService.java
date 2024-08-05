package com.test.simples.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.simples.dto.OrderDTO;
import com.test.simples.entity.Asset;
import com.test.simples.entity.Order;
import com.test.simples.repository.AssetRepository;
import com.test.simples.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import utils.OrderMapper;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	public OrderDTO getById(Long id) {
		Order order = getOrderRepository().findById(id).orElseThrow(
				() -> new EntityNotFoundException("There is no Order with id equal to " + id));
		return OrderDTO.convertToDTO(order);
	}
	
	public List<OrderDTO> getAll(){
		List<Order> orders = getOrderRepository().findAll();
		List<OrderDTO> ordersDTO = orders.stream().map(
				order -> OrderDTO.convertToDTO(order)).toList();
		return ordersDTO;
	}
	
	public void create(OrderDTO orderDTO) {
		Order order = getOrderMapper().corvertToEntity(orderDTO);
		Asset assetOnlyId = order.getAssetCode();
		Asset completAsset = getAssetRespository().findById(assetOnlyId.getId()).orElseThrow(
				() -> new EntityNotFoundException("There is no Asset with this id"));
		order.setPrice(completAsset.getPrice() * orderDTO.getQuantity());
		getOrderRepository().save(order);
	}
	
	public void delete(Long id) {
		getOrderRepository().findById(id).orElseThrow(
				() -> new EntityNotFoundException("There is no Order with id equal to " + id));
		getOrderRepository().deleteById(id);
	}
	
	public void update(OrderDTO orderDTO) {
		Order order = getOrderRepository().findById(orderDTO.getId()).orElseThrow(
				() -> new EntityNotFoundException("The Order you are trying to update doesn't exist "));
		order.setId(orderDTO.getId());
		order.setQuantity(orderDTO.getQuantity());
		order.setPrice(order.getQuantity() * order.getPrice());
		getOrderRepository().save(order);
	}
	
	public List<OrderDTO> getByAsset(String Code){
		Asset asset = getAssetRespository().getByCode(Code);
		if(asset == null) {
			throw new EntityNotFoundException("Assets with this code doesn't exist");
		}
		List<Order> orders = getOrderRepository().getByAsset(asset);
		List<OrderDTO> ordersDTO = orders.stream().map(
				order -> OrderDTO.convertToDTO(order)).toList();
		return ordersDTO;
	}
	
	public double getBalance(String Code) {
		List<Order> ordersBuyType = getOrderRepository().getByType(1);
		List<Order> ordersSellType = getOrderRepository().getByType(2);
		double buyPrice = 0;
		double sellPrice = 0;
		for(Order order: ordersBuyType) {
			buyPrice += order.getPrice();
		}
		for(Order order: ordersSellType) {
			sellPrice += order.getPrice();
		}
		return buyPrice - sellPrice;
	}
	
	private OrderRepository getOrderRepository() {
		return this.orderRepository;
	}
	
	private AssetRepository getAssetRespository() {
		return this.assetRepository;
	}
	
	private OrderMapper getOrderMapper() {
		return this.orderMapper;
	}
}
