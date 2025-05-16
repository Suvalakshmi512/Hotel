package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.CustomerFeedBackDto;
import ezee.hotel.management.crud.dto.RoleDto;
import ezee.hotel.management.crud.dto.StaffAttenanceDto;
import ezee.hotel.management.crud.dto.StaffDto;

@Repository
public class StaffAttendananceRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert(StaffAttenanceDto staffAttenanceDto) {
		String sql="INSERT INTO staff_attendance(attendance_id,staff_id,date,check_in,check_out)"
				+ " VALUES(?,?,?,?,?)";
		return jdbcTemplate.update(
				sql,
				staffAttenanceDto.getAttendanceId(),
				staffAttenanceDto.getStaffDto().getStaffId(),
				staffAttenanceDto.getDate(),
				staffAttenanceDto.getCheckIn(),
				staffAttenanceDto.getCheckOut()
				);
	}
	
	    private final RowMapper<StaffAttenanceDto> rowMapper = (rs, rowNum) -> {
	        RoleDto role = new RoleDto();
	        role.setRoleId(rs.getInt("role_id"));
	        role.setRoleName(rs.getString("role_name"));
	        role.setRoleDescription(rs.getString("role_description"));

	        StaffDto staff = new StaffDto();
	        staff.setStaffId(rs.getInt("staff_id"));
	        staff.setName(rs.getString("name"));
	        staff.setContact(rs.getLong("contact"));
	        staff.setEmail(rs.getString("email"));
	        staff.setHireDate(rs.getString("hire_date"));
	        staff.setRoleDto(role);

	        StaffAttenanceDto attendance = new StaffAttenanceDto();
	        attendance.setAttendanceId(rs.getInt("attendance_id"));
	        attendance.setDate(rs.getString("date"));
	        attendance.setCheckIn(rs.getString("check_in"));
	        attendance.setCheckOut(rs.getString("check_out"));
	        attendance.setStaffDto(staff);

	        return attendance;
	    };

	    public List<StaffAttenanceDto> findAll() {
	        String sql = "SELECT sa.attendance_id, sa.date, sa.check_in, sa.check_out, s.staff_id, s.name, s.contact, s.email, s.hire_date, " +
	                     "r.role_id,r.role_name, r.role_description " +
	                     "FROM staff_attendance sa " +
	                     "JOIN staff s ON sa.staff_id = s.staff_id " +
	                     "JOIN staff_role r ON s.role_id = r.role_id";

	        return jdbcTemplate.query(sql, rowMapper);
	    }

	    public StaffAttenanceDto findById(int id) {
	    	String sql = "SELECT sa.attendance_id, sa.date, sa.check_in, sa.check_out, s.staff_id, s.name, s.contact, s.email, s.hire_date, " +
                    "r.role_id,r.role_name, r.role_description " +
                    "FROM staff_attendance sa " +
                    "JOIN staff s ON sa.staff_id = s.staff_id " +
                    "JOIN staff_role r ON s.role_id = r.role_id "
                    + " WHERE sa.attendance_id=? ";

	        return jdbcTemplate.queryForObject(sql, rowMapper, id);
	    }

	    public int updateAttendance(int id, StaffAttenanceDto attendance) {
	        String sql = "UPDATE staff_attendance SET staff_id = ?, date = ?, check_in = ?, check_out = ? WHERE attendance_id = ?";
	        return jdbcTemplate.update(sql,
	            attendance.getStaffDto().getStaffId(),
	            attendance.getDate(),
	            attendance.getCheckIn(),
	            attendance.getCheckOut(),
	            id
	        );
	    }

	    public int deleteAttendance(int id) {
	        String sql = "DELETE FROM staff_attendance WHERE attendance_id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	}

	


