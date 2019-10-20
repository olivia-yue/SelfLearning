package com.example.learningspring.payroll;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private final EmployeeRepository repository;
	private final EmployeeResourceAssembler resourceAssembler;
	
	EmployeeController(EmployeeRepository repository, EmployeeResourceAssembler resourceAssembler){
		this.repository = repository;
		this.resourceAssembler = resourceAssembler;
	}
	
	@GetMapping("/employees")
	Resources<Resource<Employee>> all(){
//		List<Resource<Employee>> employees = repository.findAll().stream()
//				.map(employee -> new Resource<>(employee, 
//						linkTo(methodOn(EmployeeController.class).all()).withRel("employees"),
//						linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel()))
//				.collect(Collectors.toList());
		
		List<Resource<Employee>> employees = repository.findAll().stream()
				.map(resourceAssembler::toResource)
				.collect(Collectors.toList());
		
		return new Resources<>(employees, 
				linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
				
	}
	
	@PostMapping("/employees")
	ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee ) throws URISyntaxException {
		Resource<Employee> employeeResource = resourceAssembler.toResource(repository.save(newEmployee)); 	
		return ResponseEntity.created(new URI(employeeResource.getId().expand().getHref())).body(employeeResource);
	}
	
	@GetMapping("/employees/{id}")
	Resource<Employee> one(@PathVariable long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		
//		return new Resource<>(employee, 
//				linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
//				linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
		return resourceAssembler.toResource(employee);
	}
	
	@PutMapping("/employees/{id}")
	ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id) throws URISyntaxException {
		Employee updatedEmployee = repository.findById(id)
				.map(employee -> {
				employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});
		Resource<Employee> resource = resourceAssembler.toResource(updatedEmployee);
		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
		
	}
	
	@DeleteMapping("/employees/{id}")
	ResponseEntity<?> deleteEmployee(@PathVariable long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
