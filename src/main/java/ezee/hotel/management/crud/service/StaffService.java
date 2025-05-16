package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.StaffDto;
import ezee.hotel.management.crud.repository.StaffRepo;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    public void saveStaff(StaffDto staff) {
        staffRepo.insertStaff(staff);
    }

    public List<StaffDto> getAllStaff() {
        return staffRepo.findAll();
    }

    public StaffDto getStaffById(int id) {
        return staffRepo.findById(id);
    }

    public void updateStaff(int id, StaffDto staff) {
        staffRepo.updateStaff(id, staff);
    }

    public int deleteStaff(int id) {
        return staffRepo.deleteStaff(id);
    }
}
