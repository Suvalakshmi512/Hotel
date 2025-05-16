package ezee.hotel.management.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.TableDto;
import ezee.hotel.management.crud.service.TableService;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping("/insert")
    public String insert(@RequestBody TableDto dto) {
    	try {
        tableService.insert(dto);
        return "Table inserted successfully";
    }catch(IllegalArgumentException iae) {
		return "Invalid table input:"+iae.getMessage();
	}
	catch (DataAccessException dae) {
		return "Database exception while adding table:"+dae.getMessage();
	}
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
    	try {
        return ResponseEntity.ok(tableService.getAll());
    	}
    	catch (DataAccessException e) {
    		return ResponseEntity.status(500).body("Database exception while getting table:"+e.getMessage());
		}
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
    	try {
    		return ResponseEntity.ok(tableService.getById(id)) ;
    	}
    	catch(DataAccessException e) {
    		return ResponseEntity.status(500).body("Database exception while getting table:"+e.getMessage());
    		}
        
    }

    @PostMapping("/update")
    public String update(@RequestBody TableDto dto) {
    	try {
        tableService.update(dto);
        return "Table updated successfully";
    	}catch(DataAccessException e) {
    		return "Database exception while getting table:"+e.getMessage();
    		}
    	
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
    	try {
        tableService.delete(id);
        return "Table deleted successfully";
    	}
    	catch(DataAccessException e) {
    		return "Database exception while getting table:"+e.getMessage();
    		}
    }
}
