package ezee.hotel.management.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.StaffDto;
import ezee.hotel.management.crud.service.StaffService;

@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/staffinsert")
    public String createStaff(@RequestBody StaffDto staff) {
    	try {
        staffService.saveStaff(staff);
        return "Staff inserted successfully";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid staff input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting staff:"+dae.getMessage();
    	}
    }

    @GetMapping("/staffgetall")
    public ResponseEntity<?> getAllStaff() {
        try {
            List<StaffDto> staffList = staffService.getAllStaff();
            return ResponseEntity.ok(staffList);
        } catch (DataAccessException dae) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while retrieving staff: " + dae.getMessage());
        }
    }


    @GetMapping("/staffgetbyid/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable int id) {
    	try {
        StaffDto staffById = staffService.getStaffById(id);
        return ResponseEntity.ok(staffById);
    	}
    	catch (DataAccessException dae) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while retrieving staff: " + dae.getMessage());
        }
    }

    @PostMapping("/updatestaff/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable int id, @RequestBody StaffDto staff) {
    	try {
        staffService.updateStaff(id, staff);
        return ResponseEntity.ok("Staff updated successfully");
    	}catch (DataAccessException dae) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while updating staff: " + dae.getMessage());
        }
    }

    @PostMapping("/deletestaff/{id}")
    public String deleteStaff(@PathVariable int id) {
    	try {
        staffService.deleteStaff(id);
        return "Staff deleted successfully";
    	}catch (DataAccessException dae) {
    		return "Database exception while deleting staff: " + dae.getMessage();
    	}
    }
}
