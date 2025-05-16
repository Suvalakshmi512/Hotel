package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.RoleDto;
import ezee.hotel.management.crud.dto.StaffDto;

@Repository
public class StaffRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertStaff(StaffDto staff) {
        String sql = "INSERT INTO staff(staff_id, staff_name, role_id, staff_contact, staff_email, staff_hire_date, staff_user_name, staff_password) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            staff.getStaffId(),
            staff.getName(),
            staff.getRoleDto().getRoleId(),
            staff.getContact(),
            staff.getEmail(),
            staff.getHireDate(),
            staff.getUsername(),
            staff.getPassword()
        );
    }

    private final org.springframework.jdbc.core.RowMapper<StaffDto> rowMapper = (rs, rowNum) -> {
        RoleDto role = new RoleDto();
        role.setRoleId(rs.getInt("role_id"));
        role.setRoleName(rs.getString("role_name"));
        role.setRoleDescription(rs.getString("role_description"));
        
        StaffDto staff = new StaffDto();
        staff.setStaffId(rs.getInt("staff_id"));
        staff.setName(rs.getString("staff_name"));
        staff.setContact(rs.getLong("staff_contact"));
        staff.setEmail(rs.getString("staff_email"));
        staff.setHireDate(rs.getString("staff_hire_date"));
        staff.setRoleDto(role);
        staff.setUsername(rs.getString("staff_user_name"));
        staff.setPassword(rs.getString("staff_password"));

        return staff;
    };

    public List<StaffDto> findAll() {
    	String sql = "SELECT s.staff_id, s.staff_name, s.staff_contact, s.staff_email, s.staff_hire_date, s.staff_user_name, s.staff_password, r.role_id, r.role_name, r.role_description FROM staff s JOIN staff_role r ON s.role_id = r.role_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public StaffDto findById(int id) {
    	String sql = "SELECT s.staff_id, s.staff_name, s.staff_contact, s.staff_email, s.staff_hire_date, s.staff_user_name, s.staff_password, r.role_id, r.role_name, r.role_description FROM staff s JOIN staff_role r ON s.role_id = r.role_id WHERE staff_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int updateStaff(int id, StaffDto staff) {
        String sql = "UPDATE staff SET staff_name = ?, staff_role_id = ?, staff_contact = ?, staff_email = ?, staff_hire_date = ?, s.staff_user_name, s.staff_password WHERE staff_id = ?";
        return jdbcTemplate.update(
            sql,
            staff.getName(),
            staff.getRoleDto().getRoleId(),
            staff.getContact(),
            staff.getEmail(),
            staff.getHireDate(),
            staff.getStaffId(),
            staff.getUsername(),
            staff.getPassword()
        );
    }

    public int deleteStaff(int id) {
        String sql = "DELETE FROM staff WHERE staff_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
