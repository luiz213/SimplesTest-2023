package utils;

import org.springframework.stereotype.Component;

import com.test.simples.dto.OrderDTO;
import com.test.simples.entity.Order;

@Component
public class OrderMapper {

	public Order corvertToEntity(OrderDTO orderDTO) {
		Order order = new Order();
		order.setId(orderDTO.getId());
		order.setAssetCode(orderDTO.getAsset());
		order.setQuantity(orderDTO.getQuantity());
		order.setType(orderDTO.getType());
		return order;
	}
}
