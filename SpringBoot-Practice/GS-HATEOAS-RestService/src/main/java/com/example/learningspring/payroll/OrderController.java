package com.example.learningspring.payroll;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	private final OrderRepository orderRepository;
	private final OrderResourceAssembler orderAssembler;
	
	OrderController(OrderRepository orderRepository, OrderResourceAssembler orderAssembler) {
		this.orderRepository = orderRepository;
		this.orderAssembler = orderAssembler;
	}
	
	@GetMapping("/orders")
	Resources<Resource<Order>> all(){
		List<Resource<Order>> orders = orderRepository.findAll().stream()
				.map(orderAssembler::toResource)
				.collect(Collectors.toList());
		
		return new Resources<>(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
	}
	
	@GetMapping("/orders/{id}")
	Resource<Order> one(@PathVariable Long id){
		return orderAssembler.toResource(findOrder(id));
	}
	
	@PostMapping("/orders")
	ResponseEntity<?> newOrder(@RequestBody Order order){
		order.setStatus(Status.IN_PROGRESS);
		Order newOrder = orderRepository.save(order);
		
		return ResponseEntity.created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri())
				.body(orderAssembler.toResource(newOrder));
	}
	
	@PutMapping("/orders/{id}")
	ResponseEntity<?> cancel(@PathVariable Long id){
		Order order = findOrder(id);
		if(order.getStatus() == Status.IN_PROGRESS) {
			order.setStatus(Status.CANCEL);
			return ResponseEntity.ok(orderAssembler.toResource(orderRepository.save(order)));
		}
		
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(new VndErrors.VndError("Method not allowed", 
						"You cannot cancel an order that is in the " + order.getStatus() + " status."));
	}
	
	@PutMapping("/orders/{id}")
	ResponseEntity<?> complete(@PathVariable Long id){
		Order order = findOrder(id);
		if(order.getStatus() == Status.IN_PROGRESS) {
			order.setStatus(Status.COMPLETED);
			return ResponseEntity.ok(orderAssembler.toResource(orderRepository.save(order)));
		}
		
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(new VndErrors.VndError("Method not allowed", 
						"You cannot complete an order that is in the " + order.getStatus() + " status."));
	}

	private Order findOrder(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));
		return order;
	}
	
	
}
