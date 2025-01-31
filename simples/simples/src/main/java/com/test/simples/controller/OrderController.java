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

import com.test.simples.dto.OrderDTO;
import com.test.simples.service.OrderService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/getBalance/{code}")
	public @ResponseBody ResponseEntity<?> getBalance(@PathVariable String code){
		try {
			double Balance = getOrderService().getBalance(code);
			return new ResponseEntity<>(Balance, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to Find the Orders, Please Try Again Latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/searchBy/{code}")
	public @ResponseBody ResponseEntity<?> getByAssetCode(@PathVariable String code){
		try {
			List<OrderDTO> ordersDTO = getOrderService().getByAsset(code);
			return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to Find the Orders, Please Try Again Latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id){
		try {
			OrderDTO orderDTO = getOrderService().getById(id);
			return new ResponseEntity<>(orderDTO, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to find the Order, Please try Again latter" ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping()
	public @ResponseBody ResponseEntity<?> getAll(){
		try {
			List<OrderDTO> ordersDTO = getOrderService().getAll();
			return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Failed Trying to find the Order, Please try Again latter" ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public @ResponseBody ResponseEntity<?> post(@RequestBody OrderDTO orderDTO){
		try {
			getOrderService().create(orderDTO);
			return new ResponseEntity<>("Order Created Successfully " ,HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Trying to Create the Order, Please try Again latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping()
	public @ResponseBody ResponseEntity<?> delete(@RequestParam Long id){
		try {
			getOrderService().delete(id);
			return new ResponseEntity<>("Order delete Successfully", HttpStatus.OK);
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			return new ResponseEntity<>("Failed Trying to Delete the Order, Please try Again latter", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping()
	public @ResponseBody ResponseEntity<?> update(@RequestParam OrderDTO orderDTO){
		try {
			getOrderService().update(orderDTO);
			return new ResponseEntity<>("Order Updated Succesfully", HttpStatus.OK);
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			return new ResponseEntity<>("Failed Trying to update the Order, Please Try Again latter" ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private OrderService getOrderService() {
		return this.orderService;
	}
}
