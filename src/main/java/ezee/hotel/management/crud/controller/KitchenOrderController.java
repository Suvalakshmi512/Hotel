package ezee.hotel.management.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.KitchenOrderDto;
import ezee.hotel.management.crud.service.KitchenOrderService;

@RestController
public class KitchenOrderController {

    @Autowired
    private KitchenOrderService kitchenOrderService;

    @PostMapping("/kitchenorderinsert")
    public ResponseEntity<?> createKitchenOrder(@RequestBody KitchenOrderDto kitchenOrder) {
    	try {
        kitchenOrderService.saveKitchenOrder(kitchenOrder);
        return ResponseEntity.ok("Kitchen order inserted successfully");
    	}catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while inserting kitchenOrder:"+dae.getMessage());
    	}
    	
    }

    @GetMapping("/kitchenordergetall")
    public ResponseEntity<?> getAllKitchenOrders() {
    	try {
        return ResponseEntity.ok(kitchenOrderService.getAllKitchenOrders());
    	}catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while retrieving kitchenOrder:"+dae.getMessage());
    	}
    }

    @GetMapping("/kitchenordergetbyid/{id}")
    public ResponseEntity<?> getKitchenOrderById(@PathVariable int id) {
    	try {
        return ResponseEntity.ok(kitchenOrderService.getKitchenOrderById(id));
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving kitchenOrder: " + dae.getMessage());
	    }
    }

    @PostMapping("/updatekitchenorder/{id}")
    public ResponseEntity<?> updateKitchenOrder(@PathVariable int id, @RequestBody KitchenOrderDto kitchenOrder) {
       try {
    	   kitchenOrderService.updateKitchenOrder(id, kitchenOrder);
        return ResponseEntity.ok("Kitchen order updated successfully");
       }catch (DataAccessException dae) {
   		return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database exception while Updating kitchenOrder:"+dae.getMessage());
	}
    }

    @PostMapping("/deleteKitchenorder/{id}")
    public ResponseEntity<?> deleteKitchenOrder(@PathVariable int id) {
    	try {
        kitchenOrderService.deleteKitchenOrder(id);
        return ResponseEntity.ok("Kitchen order deleted successfully");
    	}
    	catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while  Deleting kitchenOrder:"+dae.getMessage());
    	}
    }
}
