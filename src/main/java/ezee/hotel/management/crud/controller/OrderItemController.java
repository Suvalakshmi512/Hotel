package ezee.hotel.management.crud.controller;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.OrderItemDTO;
import ezee.hotel.management.crud.service.OrderItemService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> addOrderItem(@RequestBody OrderItemDTO dto) {
    	try {
        service.addOrderItem(dto);
        return ResponseEntity.ok("Order Item Added");
    	}
    	catch(IllegalArgumentException iae) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body("Invalid staff input:"+iae.getMessage());
    	}
    	catch (DataAccessException dae) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body("Database exception while inserting order-items:"+dae.getMessage());
    	}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllOrderItems() {
//    	try {
        List<OrderItemDTO> allOrderItems = service.getAllOrderItems();
		return ResponseEntity.ok(allOrderItems);
//    	}catch (IllegalArgumentException | NoSuchElementException ex) {
//	        return ResponseEntity
//	                .status(HttpStatus.BAD_REQUEST)
//	                .body("Invalid staff_role input: " + ex.getMessage());
//	    } catch (DataAccessException dae) {
//	        return ResponseEntity
//	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body("Database exception while retrieving order-itemse: " + dae.getMessage());
//	    }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable int id) {
    	try {
        OrderItemDTO orderItemById = service.getOrderItemById(id);
        return ResponseEntity.ok(orderItemById);
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving order-items: " + dae.getMessage());
	    }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateOrderItem(@RequestBody OrderItemDTO dto) {
        service.updateOrderItem(dto);
        return ResponseEntity.ok("Order Item Updated");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable int id) {
    	try {
        service.deleteOrderItem(id);
        return ResponseEntity.ok("Order Item Deleted");
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting order-items: " + dae.getMessage());
	    }
    }
}
