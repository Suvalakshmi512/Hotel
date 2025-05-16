package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.RoleDto;
import ezee.hotel.management.crud.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/insert")
    public String insert(@RequestBody RoleDto dto) {
    	try {
        roleService.insert(dto);
        return "Role inserted successfully";
    	}
    	catch(IllegalArgumentException iae) {
    		return "Invalid staff input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting staff_role:"+dae.getMessage();
    	}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
    	try {
        List<RoleDto> all = roleService.getAll();
        return ResponseEntity.ok(all);
    	}catch (DataAccessException dae) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while retrieving staff_role: " + dae.getMessage());
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
    	try {
        RoleDto byId = roleService.getById(id);
        return ResponseEntity.ok(byId);
    	}catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff_role input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving staff_role: " + dae.getMessage());
	    }

    }

    @PostMapping("/update")
    public String update(@RequestBody RoleDto dto) {
    	try {
        roleService.update(dto);
        return "Role updated successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid staff_role input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting staff_role:"+dae.getMessage();
    	}
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
    	try {
        roleService.delete(id);
        return "Role deleted successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid staff_role input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting staff_role:"+dae.getMessage();
    	}
    }
}
