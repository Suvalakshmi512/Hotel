package ezee.hotel.management.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.ReservationDto;
import ezee.hotel.management.crud.repository.ReservationRepo;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    public void saveReservation(ReservationDto reservation) {
        reservationRepo.insertReservation(reservation);
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepo.findAll();
    }

    public ReservationDto getReservationById(int id) {
        return reservationRepo.findById(id);
    }

    public void updateReservation(int id, ReservationDto reservation) {
        reservationRepo.updateReservation(id, reservation);
    }

    public void deleteReservation(int id) {
        reservationRepo.deleteReservation(id);
    }
}
