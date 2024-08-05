package utils;

import org.springframework.stereotype.Component;
import com.test.simples.dto.AssetDTO;
import com.test.simples.entity.Asset;

@Component
public class AssetMapper {

	public Asset convertToEntity(AssetDTO assetDTO) {
		Asset asset = new Asset();
		asset.setId(assetDTO.getId());
		asset.setCode(assetDTO.getCode());;
		asset.setPrice(assetDTO.getPrice());
		return asset;
	}
}
