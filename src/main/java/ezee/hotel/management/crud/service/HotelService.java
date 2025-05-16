package ezee.hotel.management.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.HotelDto;
import ezee.hotel.management.crud.repository.HotelRepo;

@Service
public class HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    public void insert(HotelDto dto) {
        hotelRepo.insert(dto);
    }

    public List<HotelDto> getAll() {
        return hotelRepo.findAll();
    }

    public HotelDto getById(int id) {
        return hotelRepo.findById(id);
    }

    public void update(HotelDto dto) {
        hotelRepo.update(dto);
    }

    public void delete(int id) {
        hotelRepo.deleteById(id);
    }
}
