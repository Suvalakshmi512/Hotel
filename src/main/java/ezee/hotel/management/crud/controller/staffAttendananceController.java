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

import ezee.hotel.management.crud.dto.StaffAttenanceDto;
import ezee.hotel.management.crud.service.StaffAttendananceService;

@RestController
@RequestMapping("/staffattendance")
public class staffAttendananceController {
	@Autowired
	private StaffAttendananceService staffAttendananceService;
	
	@PostMapping("/insert")
	public String createStaffAttendanance(@RequestBody StaffAttenanceDto staffAttenanceDto) {
		try {
		staffAttendananceService.saveStaffAttendanance(staffAttenanceDto);
		return "Inserted Successfully";
		}catch(IllegalArgumentException iae) {
    		return "Invalid staff input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting staff:"+dae.getMessage();
    	}
	}
	@GetMapping("/getall")
	public ResponseEntity<?> getAllAttendance() {
	    try {
	        List<StaffAttenanceDto> allAttendance = staffAttendananceService.getAllAttendance();
	        return ResponseEntity.ok(allAttendance);
	    } catch (IllegalArgumentException | NoSuchElementException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Invalid staff input: " + ex.getMessage());
	    } catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving staff: " + dae.getMessage());
	    }
	}


	    @GetMapping("/getbyid/{id}")
	    public ResponseEntity<?> getAttendanceById(@PathVariable int id) {
	    	try {
	        StaffAttenanceDto attendanceById = staffAttendananceService.getAttendanceById(id);
	        return ResponseEntity.ok(attendanceById);
	    	}catch (IllegalArgumentException | NoSuchElementException ex) {
		        return ResponseEntity
		                .status(HttpStatus.BAD_REQUEST)
		                .body("Invalid staff input: " + ex.getMessage());
		    } catch (DataAccessException dae) {
		        return ResponseEntity
		                .status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Database exception while retrieving staff: " + dae.getMessage());
		    }
	    }

	    @PostMapping("/update/{id}")
	    public ResponseEntity<?> updateAttendance(@PathVariable int id, @RequestBody StaffAttenanceDto attendance) {
	    	try {
	    	staffAttendananceService.updateAttendance(id, attendance);
	        return ResponseEntity.ok("Attendance updated successfully");
	    	}catch (DataAccessException dae) {
		        return ResponseEntity
		                .status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Database exception while retrieving staff: " + dae.getMessage());
		    }
	    }

	    @PostMapping("/delete/{id}")
	    public ResponseEntity<?> deleteAttendance(@PathVariable int id) {
	    	try {
	    	staffAttendananceService.deleteAttendance(id);
	        return ResponseEntity.ok("Attendance deleted successfully");
	    	}catch (DataAccessException dae) {
		        return ResponseEntity
		                .status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Database exception while retrieving staff: " + dae.getMessage());
		    }
	    }
	}

	


