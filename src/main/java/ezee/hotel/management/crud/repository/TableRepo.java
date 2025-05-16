package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.TableDto;

@Repository
public class TableRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(TableDto tableDto) {
        String sql = "INSERT INTO table_info (table_id, table_number, table_capacity, table_is_occupied) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            tableDto.getTableId(),
            tableDto.getTableNumber(),
            tableDto.getCapacity(),
            tableDto.isOccupied());
    }

    public List<TableDto> findAll() {
        String sql = "SELECT table_id AS tableId, table_number AS tableNumber, table_capacity, table_is_occupied AS isOccupied FROM table_info";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TableDto.class));
    }

    public TableDto findById(int id) {
        String sql = "SELECT table_id AS tableId, table_number AS tableNumber, table_capacity, table_is_occupied AS isOccupied FROM table_info WHERE table_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(TableDto.class));
    }

    public int update(TableDto tableDto) {
        String sql = "UPDATE table_info SET table_number = ?, table_capacity = ?, table_is_occupied = ? WHERE table_id = ?";
        return jdbcTemplate.update(sql,
            tableDto.getTableNumber(),
            tableDto.getCapacity(),
            tableDto.isOccupied(),
            tableDto.getTableId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM table_info WHERE table_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
