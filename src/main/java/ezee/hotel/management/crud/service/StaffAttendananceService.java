package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.StaffAttenanceDto;
import ezee.hotel.management.crud.repository.StaffAttendananceRepo;

@Service
public class StaffAttendananceService {
	@Autowired
	private StaffAttendananceRepo staffAttendananceRepo;
	
	public void saveStaffAttendanance(StaffAttenanceDto staffAttenanceDto) {
	staffAttendananceRepo.insert(staffAttenanceDto);
	}

	    public List<StaffAttenanceDto> getAllAttendance() {
	        return staffAttendananceRepo.findAll();
	    }

	    public StaffAttenanceDto getAttendanceById(int id) {
	        return staffAttendananceRepo.findById(id);
	    }

	    public void updateAttendance(int id, StaffAttenanceDto attendance) {
	    	staffAttendananceRepo.updateAttendance(id, attendance);
	    }

	    public int deleteAttendance(int id) {
	        return staffAttendananceRepo.deleteAttendance(id);
	    }
	}

	

