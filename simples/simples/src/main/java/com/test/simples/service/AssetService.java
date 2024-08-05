package com.test.simples.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.simples.dto.AssetDTO;
import com.test.simples.entity.Asset;
import com.test.simples.repository.AssetRepository;
import jakarta.persistence.EntityNotFoundException;
import utils.AssetMapper;

@Service
public class AssetService {

	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private AssetMapper assetMapper;
	
	public AssetDTO getById(Long id) {
		Asset asset = getAssetRepository().findById(id).orElseThrow(
				() -> new EntityNotFoundException("There is no Asset with id equal to " + id));
		return AssetDTO.convertDTO(asset);
	}
	
	public List<AssetDTO> getAll(){
		List<Asset> assets = getAssetRepository().findAll();
		List<AssetDTO> assetsDTO = assets.stream().map(
				asset -> AssetDTO.convertDTO(asset)).toList();
		return assetsDTO;
	}
	
	public void create(AssetDTO assetDTO) {
		Asset asset = getAssetMapper().convertToEntity(assetDTO);
		getAssetRepository().save(asset);
	}
	
	public void delete(Long id) {
		getAssetRepository().findById(id).orElseThrow(
				() -> new EntityNotFoundException("There is no Asset with id equal to " + id));
		getAssetRepository().deleteById(id);
	}
	
	public void update(AssetDTO assetDTO) {
		Asset asset = getAssetRepository().findById(assetDTO.getId()).orElseThrow(
				() -> new EntityNotFoundException("The Asset you are trying to update doesn't exist"));
		asset.setId(assetDTO.getId());
		asset.setCode(assetDTO.getCode());
		asset.setPrice(assetDTO.getPrice());
		getAssetRepository().save(asset);
	}
	
	public AssetDTO getAssetByCode(String code){
		Asset asset = getAssetRepository().getByCode(code);
		if(asset == null) {
			throw new EntityNotFoundException("Assets with this code doesn't exist");
		}
		AssetDTO assetDTO = AssetDTO.convertDTO(asset);
		return assetDTO;
	}
	
	private AssetRepository getAssetRepository() {
		return this.assetRepository;
	}
	
	private AssetMapper getAssetMapper() {
		return this.assetMapper;
	}
}
