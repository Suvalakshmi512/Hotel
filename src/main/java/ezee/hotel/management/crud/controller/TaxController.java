package ezee.hotel.management.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.TaxDto;
import ezee.hotel.management.crud.service.TaxService;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/insert")
    public String insert(@RequestBody TaxDto dto) {
    	try {
        taxService.insert(dto);
        return "Tax inserted successfully";
    	}
    	catch(IllegalArgumentException iae) {
    		return "Invalid tax input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while adding tax:"+dae.getMessage();
		}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<TaxDto> taxes = taxService.getAll();
            return ResponseEntity.ok(taxes);
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(500)
                    .body("Database exception while getting tax: " + e.getMessage());
        }
    }


    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            TaxDto tax = taxService.getById(id);
            if (tax != null) {
                return ResponseEntity.ok(tax);
            } else {
                return ResponseEntity.status(500)
                                     .body("Tax with ID " + id + " not found.");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(500)
                                 .body("Database error while getting tax: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody TaxDto dto) {
    	try {
    		taxService.update(dto);
            return "Tax updated successfully";
    	}
    	catch(IllegalArgumentException e) {
    		return "Invalid input:"+e.getMessage();
    	}catch (DataAccessException e) {
            return "Database error while Updating tax: " + e.getMessage();
}
        
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
    	try {
        taxService.delete(id);
        return "Tax deleted successfully";
    	}
    	catch (DataAccessException e) {
            return "Database error while deleting tax: " + e.getMessage();
}
}
}
