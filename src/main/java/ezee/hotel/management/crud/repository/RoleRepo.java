package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.RoleDto;

@Repository
public class RoleRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(RoleDto roleDto) {
        String sql = "INSERT INTO staff_role (staff_role_id, staff_role_name, staff_role_description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, roleDto.getRoleId(), roleDto.getRoleName(), roleDto.getRoleDescription());
    }

    public List<RoleDto> findAll() {
        String sql = "SELECT staff_role_id AS roleId, staff_role_name AS roleName, staff_role_description AS roleDescription FROM staff_role";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RoleDto.class));
    }

    public RoleDto findById(int id) {
        String sql = "SELECT staff_role_id AS roleId, staff_role_name AS roleName, staff_role_description AS roleDescription FROM staff_role WHERE staff_role_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(RoleDto.class));
    }

    public int update(RoleDto roleDto) {
        String sql = "UPDATE staff_role SET staff_role_name = ?, staff_role_description = ? WHERE staff_role_id = ?";
        return jdbcTemplate.update(sql, roleDto.getRoleName(), roleDto.getRoleDescription(), roleDto.getRoleId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM staff_role WHERE staff_role_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
