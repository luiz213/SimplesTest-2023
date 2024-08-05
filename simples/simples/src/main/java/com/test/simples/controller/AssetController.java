package com.test.simples.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.test.simples.dto.AssetDTO;
import com.test.simples.service.AssetService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/assets")
@CrossOrigin(origins = "http://localhost:4200")
public class AssetController {

	@Autowired
	private AssetService assetService;
	
	@GetMapping(value = "/searchBy/{code}")
	public @ResponseBody ResponseEntity<?> getByCode(@PathVariable String code){
		try {
			AssetDTO assetDTO = getAssetService().getAssetByCode(code);
			return new ResponseEntity<>(assetDTO, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to Search By Code, Please Try Again Latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id){
		try {
			AssetDTO assetDTO = getAssetService().getById(id);
			return new ResponseEntity<>(assetDTO, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to find the Asset, Please try Again latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping()
	public @ResponseBody ResponseEntity<?> getAll(){
		try {
			List<AssetDTO> assets = getAssetService().getAll();
			return new ResponseEntity<>(assets, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Failed Trying to find the Asset, Please try Again latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public @ResponseBody ResponseEntity<?> post(@RequestBody AssetDTO assetDTO){
		try {
			getAssetService().create(assetDTO);
			return new ResponseEntity<>("Asset Created Successfully " ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to Create the Asset, Please try Again latter" ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping()
	public @ResponseBody ResponseEntity<?> delete(@RequestParam Long id){
		try {
			getAssetService().delete(id);
			return new ResponseEntity<>("Asset Deleted Successfully", HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to delete the Asset, Please Try again latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping()
	public @ResponseBody ResponseEntity<?> update(@RequestBody AssetDTO assetDTO){
		try {
			getAssetService().update(assetDTO);
			return new ResponseEntity<>("Asset Updated Succesfully", HttpStatus.OK);
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			return new ResponseEntity<>("Failed Trying to update the Asset, Please Try Again latter" ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private AssetService getAssetService() {
		return this.assetService;
	}
}
