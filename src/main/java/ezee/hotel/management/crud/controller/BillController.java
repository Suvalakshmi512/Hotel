package ezee.hotel.management.crud.controller;

import ezee.hotel.management.crud.dto.BillDto;
import ezee.hotel.management.crud.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/insert")
    public ResponseEntity<?> addBill(@RequestBody BillDto bill) {
    	try {
    	billService.saveBill(bill);
		return ResponseEntity.ok("inserted sucessfully");
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while inserting bill: " + dae.getMessage());
	    }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllBill() {
    	try {
        return ResponseEntity.ok(billService.getAllBill());
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving bill: " + dae.getMessage());
	    }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getBillById(@PathVariable int id) {
    	try {
        return ResponseEntity.ok(billService.getBillById(id));
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while retrieving bill: " + dae.getMessage());
	    }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBill(@PathVariable int id, @RequestBody BillDto bill) {
    	try {
	      billService.updateBill(id, bill);
		return ResponseEntity.ok("Updated sucessfully");
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while Updating bill: " + dae.getMessage());
	    }
    	
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable int id) {
    	try {
        int deleteBill = billService.deleteBill(id);
		return ResponseEntity.ok(deleteBill);
    	}catch (DataAccessException dae) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database exception while deleting bill: " + dae.getMessage());
	    }
    }
}
