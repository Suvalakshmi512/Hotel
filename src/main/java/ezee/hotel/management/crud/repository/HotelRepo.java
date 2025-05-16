package ezee.hotel.management.crud.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.HotelDto;

@Repository
public class HotelRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(HotelDto dto) {
        String sql = "INSERT INTO hotel (hotel_id, hotel_name, hotel_location, hotel_branch) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            dto.getHotelId(),
            dto.getHotelName(),
            dto.getHotelLocation(),
            dto.getHotelBranch()
        );
    }

    public List<HotelDto> findAll() {
        String sql = "SELECT hotel_id AS hotelId, hotel_name AS hotelName, hotel_location AS hotelLocation, hotel_branch AS hotelBranch FROM hotel";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(HotelDto.class));
    }

    public HotelDto findById(int id) {
        String sql = "SELECT hotel_id AS hotelId, hotel_name AS hotelName, hotel_location AS hotelLocation, hotel_branch AS hotelBranch FROM hotel WHERE hotel_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(HotelDto.class));
    }

    public int update(HotelDto dto) {
        String sql = "UPDATE hotel SET hotel_name = ?, hotel_location = ?, hotel_branch = ? WHERE hotel_id = ?";
        return jdbcTemplate.update(sql,
            dto.getHotelName(),
            dto.getHotelLocation(),
            dto.getHotelBranch(),
            dto.getHotelId()
        );
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM hotel WHERE hotel_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
