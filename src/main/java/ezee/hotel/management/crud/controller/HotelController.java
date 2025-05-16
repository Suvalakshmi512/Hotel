package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.HotelDto;
import ezee.hotel.management.crud.dto.MenuCategoryDto;
import ezee.hotel.management.crud.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody HotelDto dto) {
    	try {
        hotelService.insert(dto);
        return ResponseEntity.ok("Hotel inserted successfully");
    	} catch (DataAccessException dae) {
    	        return ResponseEntity
    	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
    	                .body("Database exception while Updating Hotel: " + dae.getMessage());
    	    }
        }
    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
    	try {
        List<HotelDto> all = hotelService.getAll();
		return ResponseEntity.ok(all);
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving Hotel: " + dae.getMessage());
	    }
        
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
    	try {
     HotelDto byId = hotelService.getById(id);
     return ResponseEntity.ok(byId);
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving Hotel: " + dae.getMessage());
	    }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody HotelDto dto) {
    	try {
        hotelService.update(dto);
        return ResponseEntity.ok("Hotel updated successfully");
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Updating Hotel: " + dae.getMessage());
	    }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
    	try {
        hotelService.delete(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Deleting Hotel: " + dae.getMessage());
	    }
    }
}
