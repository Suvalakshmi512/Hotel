package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.MenuCategoryDto;
import ezee.hotel.management.crud.service.MenuCategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/menu-category")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryService service;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody MenuCategoryDto dto) {
    	try {
        service.insert(dto);
        return ResponseEntity.ok("Menu category inserted successfully");
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while inserting MenuCategory: " + dae.getMessage());
	    }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
    	try {
         List<MenuCategoryDto> all = service.getAll();
		return ResponseEntity.ok(all);
    	}catch (DataAccessException dae) {
    		return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while retrieving MenuCategory:"+dae.getMessage());
    	}
         
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
    	try {
        MenuCategoryDto byId = service.getById(id);
        return ResponseEntity.ok(byId);
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving MenuCategory: " + dae.getMessage());
	    }
    }

    @PostMapping("/update")
    public String update(@RequestBody MenuCategoryDto dto) {
        service.update(dto);
        return "Menu category updated successfully";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
    	try {
        service.delete(id);
        return "Menu category deleted successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid staff_role input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while Deleting MenuCategory:"+dae.getMessage();
    	}
    }
}
