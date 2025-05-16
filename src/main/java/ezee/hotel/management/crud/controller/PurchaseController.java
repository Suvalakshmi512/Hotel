package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.PurchaseDto;
import ezee.hotel.management.crud.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/insert")
    public String addPurchase(@RequestBody PurchaseDto purchase) {
    	try {
        purchaseService.savePurchase(purchase);
		return "Inserted Successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid Reservation  input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting purchase:"+dae.getMessage();
    	}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllPurchases() {
    	try {
         List<PurchaseDto> allPurchases = purchaseService.getAllPurchases();
         return ResponseEntity.ok(allPurchases);
    	}
    	catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving purchase: " + dae.getMessage());
	    }
    	
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getPurchaseById(@PathVariable int id) {
    	try {
        PurchaseDto purchaseById = purchaseService.getPurchaseById(id);
        return ResponseEntity.ok(purchaseById);
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving purchase: " + dae.getMessage());
	    }
    	
    }

    @PostMapping("/update/{id}")
    public String updatePurchase(@PathVariable int id, @RequestBody PurchaseDto purchase) {
    
    	purchaseService.updatePurchase(id, purchase);
		return "Updated Successfully";
       
    }

    @PostMapping("/delete/{id}")
    public String deletePurchase(@PathVariable int id) {
    	try {
         purchaseService.deletePurchase(id);
         return "Deleted successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid Reservation  input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while Deleting purchase:"+dae.getMessage();
    	}
    }
}
