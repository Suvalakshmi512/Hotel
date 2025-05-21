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
import ezee.hotel.management.crud.exception.ResponceException;
import ezee.hotel.management.crud.exception.ServiceException;
import ezee.hotel.management.crud.service.CustomerFeedBackService;

@RestController
public class CustomerFeedBackController {
	@Autowired
	private CustomerFeedBackService customerFeedService;
	
	@PostMapping("/customerfeedbackinsert")
	public ResponseEntity<?> CreateCustomerFB(@RequestBody CustomerFeedBackDto customerFB) {
		try {
		customerFeedService.saveCustomerFB(customerFB);
		return ResponseEntity.ok(ResponceException.success("Inserted Success Fully"));
		}catch (ServiceException se) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(ResponceException.error(se.getErrorCode()));
	    }
	}
	
	@GetMapping("/customerfeebackgetall")
	public ResponseEntity<?> fetchAll(){
		try {
		List<CustomerFeedBackDto> customerFeedBackDtos= customerFeedService.getAll();
		return ResponseEntity.ok(customerFeedBackDtos);
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body("Database exception while retrieving cutomerFeedback: " + dae.getMessage());
	    }
	}
	
	@GetMapping("/customerfeedbackgetbyid/{id}")
	public ResponseEntity<?> fetchById(@PathVariable int id) {
		try {
		return ResponseEntity.ok(customerFeedService.getById(id));
		}catch (ServiceException se) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(ResponceException.error(se.getErrorCode()));
	    }
	}
	@PostMapping("/updatecustomerfeedback/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody CustomerFeedBackDto customerFeedback) {
		try {
		customerFeedService.Update(id, customerFeedback);
		return ResponseEntity.ok(ResponceException.success("Update successfull"));
		}catch (ServiceException se) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(ResponceException.error(se.getErrorCode()));
	    }
	}
	
	@PostMapping("/deletecustomerfeedback/{id}")
	public ResponseEntity<?> CustomerfeedbackDelete(@PathVariable int id) {
		try {
		customerFeedService.DeleteCustomerFeedback(id);
		return ResponseEntity.ok(ResponceException.success("Deleted successfully"));
		}catch (ServiceException se) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(ResponceException.error(se.getErrorCode()));
	    }
	}
	

}
