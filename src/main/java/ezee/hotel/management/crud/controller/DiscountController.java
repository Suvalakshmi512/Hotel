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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ezee.hotel.management.crud.dto.DiscountDto;
import ezee.hotel.management.crud.service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountController {
	@Autowired
	private DiscountService discountService;
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody  DiscountDto discountdto) {
		try {
		discountService.Insert(discountdto);
		return ResponseEntity.ok("Inserted Successfully");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Inserting discount: " + dae.getMessage());
	    }
	}
	@GetMapping("/getall")
	public ResponseEntity<?> findAll(){
		try {
		return ResponseEntity.ok(discountService.getAll());
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving discount: " + dae.getMessage());
	    }
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id){
		try {
		return ResponseEntity.ok(discountService.getById(id));
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving discount: " + dae.getMessage());
	    }
	}
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody DiscountDto discountdto) {
		try {
		discountService.update(discountdto);
		return ResponseEntity.ok("Data Updated");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Updating discount: " + dae.getMessage());
	    }
		
	}
	@PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
		try {
		discountService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
		}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while deleting discount: " + dae.getMessage());
	    }
    }

}
