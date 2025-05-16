package ezee.hotel.management.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.SupplierDto;
import ezee.hotel.management.crud.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/insert")
    public String insert(@RequestBody SupplierDto dto) {
    	try {
        supplierService.insert(dto);
        return "Supplier inserted successfully";
    	}
    catch(IllegalArgumentException iae) {
		return "Invalid suplier input:"+iae.getMessage();
	}
	catch (DataAccessException dae) {
		return "Database exception while inserting suplier:"+dae.getMessage();
	}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
    	try {
         ResponseEntity<List<SupplierDto>> list = ResponseEntity.ok(supplierService.getAll()) ;
         return list;
    	}catch (DataAccessException dae) {
    		return ResponseEntity.status(500).body("Database exception while getting suplier :"+dae.getMessage());
    	}
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
    	try {
    		SupplierDto suplier = supplierService.getById(id);
        return ResponseEntity.ok(suplier) ;
    	}
    	catch (DataAccessException dae) {
    		return ResponseEntity.status(500).body("Database exception while inserting suplier:"+dae.getMessage());
    	}
    }

    @PostMapping("/update")
    public String update(@RequestBody SupplierDto dto) {
    	try {
        supplierService.update(dto);
        return "Supplier updated successfully";
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while updating suplier:"+dae.getMessage();
    	}
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
    	try {
        supplierService.delete(id);
        return "Supplier deleted successfully";
    	}catch (DataAccessException dae) {
    		return "Database exception while deleting suplier:"+dae.getMessage();
    	}
    }
}
