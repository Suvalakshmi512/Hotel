package ezee.hotel.management.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customerinsert")
	public ResponseEntity<?> CreateUser(@RequestBody CustomerDto customer) {
		try {
		customerService.saveCustomer(customer);
		return ResponseEntity.ok("Inserted Successfully");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Inserting customer: " + dae.getMessage());
	    }
		
	}
	
	@GetMapping("/getcustomer")
	public ResponseEntity<?> findAllCustomer(){
		try {
		return ResponseEntity.ok(customerService.getAllCustomers());
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving customer: " + dae.getMessage());
	    }
		
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id){
		try {
		return ResponseEntity.ok(customerService.getById(id));
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving customer: " + dae.getMessage());
	    }
		
	}
	
	@PostMapping("/updatecustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customer) {
		try {
		customerService.updateCustomer(customer);
		return ResponseEntity.ok("Data Updated");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Updating customer: " + dae.getMessage());
	    }
	}
	
	@PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {
		try {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting customer: " + dae.getMessage());
	    }
    }
}
