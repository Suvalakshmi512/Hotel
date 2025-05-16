package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.MenuItemDto;
import ezee.hotel.management.crud.service.MenuItemService;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/insert")
    public String addMenuItem(@RequestBody MenuItemDto item) {
    	try {
        menuItemService.saveMenuItem(item);
        return "Inserted successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid staff input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting menuItem:"+dae.getMessage();
    	}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllMenuItems() {
    	try {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    	}
    	catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while inserting retrieving menuItem:"+dae.getMessage());
    	}
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable int id) {
    	try {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving menuItem: " + dae.getMessage());
	    }
    }

    @PostMapping("update/{id}")
    public void updateMenuItem(@PathVariable int id, @RequestBody MenuItemDto item) {
        menuItemService.updateMenuItem(id, item);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable int id) {
    	try {
        return ResponseEntity.ok(menuItemService.deleteMenuItem(id));
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting menuItem: " + dae.getMessage());
	    }
    }
}
