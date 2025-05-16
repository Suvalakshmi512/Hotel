package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.InventryItemDto;
import ezee.hotel.management.crud.service.IventoryService;

@RestController
@RequestMapping("/inventory")
public class InventryController {
	@Autowired
	private IventoryService inventryService;
	
	@PostMapping("/inventryinsert")
	public ResponseEntity<?> InventryUser(@RequestBody InventryItemDto inventry) {
		try {
		 inventryService.saveInventry(inventry);
		return ResponseEntity.ok("Inserted Successfully");
		}
		catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while inserting Inventry:"+dae.getMessage());
    	}
	}
	@GetMapping("/getinventry")
	public ResponseEntity<?> findAllInventry(){
		try {
		List<InventryItemDto> getlAInventryItemDtos = inventryService.getlAInventryItemDtos();
		return ResponseEntity.ok(getlAInventryItemDtos);
		} catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving Inventry: " + dae.getMessage());
	    }
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id){
		try {
		return ResponseEntity.ok(inventryService.getById(id));
		} catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving Inventry: " + dae.getMessage());
	    }
	}
	@PostMapping("/updateinventry")
	public String updateInventry(@RequestBody InventryItemDto inventry) {
		inventryService.updateInventry(inventry);
		return "Data Updated";
		
	}
	@PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventry(@PathVariable("id") int id) {
		try {
		inventryService.deleteInventry(id);
        return ResponseEntity.ok("Customer deleted successfully");
		}
		catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting Inventry: " + dae.getMessage());
	    }
    }
	
	

}
