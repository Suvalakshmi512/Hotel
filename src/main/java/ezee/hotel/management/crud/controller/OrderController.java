package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.service.OrderService;

@RestController
@RequestMapping("/Order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderdto) {
		try {
		orderService.insertOrder(orderdto);
		return ResponseEntity.ok("Inserted successfully");
		}catch(IllegalArgumentException iae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Invalid staff input:"+iae.getMessage());
    	}
    	catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while inserting Order:"+dae.getMessage());
    	}
	}

	    @GetMapping("/ordergetall")
	    public ResponseEntity<?> getAllOrders() {
	    	try {
	        return ResponseEntity.ok(orderService.getAllOrders());
	    	}catch (DataAccessException dae) {
	            return ResponseEntity
	                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Database exception while retrieving Order: " + dae.getMessage());
	        }
	    }

	    @GetMapping("/ordergetbyid/{id}")
	    public ResponseEntity<?> getOrderById(@PathVariable int id) {
	    	try {
	        return ResponseEntity.ok(orderService.getOrderById(id));
	    	}catch (IllegalArgumentException | NoSuchElementException ex) {
		        return ResponseEntity
		                .status(HttpStatus.BAD_REQUEST)
		                .body("Invalid staff_role input: " + ex.getMessage());
		    } catch (DataAccessException dae) {
		        return ResponseEntity
		                .status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Database exception while retrieving Order: " + dae.getMessage());
		    }
	    }

	    @PostMapping("/updateorder/{id}")
	    public ResponseEntity<String> updateOrder(@PathVariable int id, @RequestBody OrderDto order) {
	        orderService.updateOrder(id, order);
	        return ResponseEntity.ok("Order updated successfully");
	    }

	    @PostMapping("/deleteorder/{id}")
	    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
	    	try {
	        orderService.deleteOrder(id);
	        return ResponseEntity.ok("Order deleted successfully");
	        }
	    catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting Order: " + dae.getMessage());
	    }
	    }
	}


