package ezee.hotel.management.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezee.hotel.management.crud.dto.ReservationDto;
import ezee.hotel.management.crud.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/insert")
    public String createReservation(@RequestBody ReservationDto reservation) {
    	try {
        reservationService.saveReservation(reservation);
        return "Reservation saved successfully.";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid Reservation  input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while inserting Reservation:"+dae.getMessage();
    	}
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllReservations() {
    	try {
        List<ReservationDto> allReservations = reservationService.getAllReservations();
        return ResponseEntity.ok(allReservations);
    	}catch (DataAccessException dae) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database exception while retrieving Reservation: " + dae.getMessage());
        }
    	
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable int id) {
    	try {
         ReservationDto reservationById = reservationService.getReservationById(id);
         return ResponseEntity.ok(reservationById);
    }catch (IllegalArgumentException | NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid Reservation input: " + ex.getMessage());
    } catch (DataAccessException dae) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database exception while retrieving Reservation: " + dae.getMessage());
    }
    }

    @PostMapping("/update/{id}")
    public String updateReservation(@PathVariable int id, @RequestBody ReservationDto reservation) {
        try {
    	reservationService.updateReservation(id, reservation);
        return "Reservation updated successfully.";
        }catch(IllegalArgumentException iae) {
    		return "Invalid Reservation input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while Updating Reservation:"+dae.getMessage();
    	}
    }

    @PostMapping("/delete/{id}")
    public String deleteReservation(@PathVariable int id) {
    	try {
        reservationService.deleteReservation(id);
        return "Reservation deleted successfully.";
    	}catch(IllegalArgumentException iae) {
    		return "Invalid Reservation input:"+iae.getMessage();
    	}
    	catch (DataAccessException dae) {
    		return "Database exception while Deleting Reservation:"+dae.getMessage();
    	}
    }
}
