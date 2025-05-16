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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.CustomerFeedBackDto;
import ezee.hotel.management.crud.service.CustomerFeedBackService;

@RestController
public class CustomerFeedBackController {
	@Autowired
	private CustomerFeedBackService customerFeedService;
	
	@PostMapping("/customerfeedbackinsert")
	public ResponseEntity<?> CreateCustomerFB(@RequestBody CustomerFeedBackDto customerFB) {
		try {
		customerFeedService.saveCustomerFB(customerFB);
		return ResponseEntity.ok("Inserted Success Fully");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while inserting cutomerFeedback: " + dae.getMessage());
	    }
	}
	
	@GetMapping("/customerfeebackgetall")
	public ResponseEntity<?> fetchAll(){
		try {
		List<CustomerFeedBackDto> customerFeedBackDtos= customerFeedService.getAll();
		return ResponseEntity.ok(customerFeedBackDtos);
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving cutomerFeedback: " + dae.getMessage());
	    }
	}
	
	@GetMapping("/customerfeedbackgetbyid/{id}")
	public ResponseEntity<?> fetchById(@PathVariable int id) {
		try {
		return ResponseEntity.ok(customerFeedService.getById(id));
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving cutomerFeedback: " + dae.getMessage());
	    }
	}
	@PostMapping("/updatecustomerfeedback/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody CustomerFeedBackDto customerFeedback) {
		try {
		customerFeedService.Update(id, customerFeedback);
		return ResponseEntity.ok("Update successfull");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Updating cutomerFeedback: " + dae.getMessage());
	    }
	}
	
	@PostMapping("/deletecustomerfeedback/{id}")
	public ResponseEntity<?> CustomerfeedbackDelete(@PathVariable int id) {
		try {
		customerFeedService.DeleteCustomerFeedback(id);
		return ResponseEntity.ok("deleted successfully");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting cutomerFeedback: " + dae.getMessage());
	    }
	}
	

}
