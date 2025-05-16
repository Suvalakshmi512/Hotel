package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.DiscountDto;

@Repository
public class DiscountRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(DiscountDto discountDto) {
        String sql = "INSERT INTO discount (discount_id, discount_name, discount_type, discount_value, discount_valid_from, discount_valid_to) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                discountDto.getDiscountId(),
                discountDto.getDiscountName(),
                discountDto.getDiscountType(),
                discountDto.getDiscountValue(),
                discountDto.getDiscountValidFrom(),
                discountDto.getDiscountValidTo()
        );
    }

    public List<DiscountDto> findAll() {
        String sql = "SELECT discount_id AS discountId, discount_name AS discountName, discount_type AS discountType, discount_value AS discountValue, discount_valid_from AS discountValidFrom, discount_valid_to AS discountValidTo FROM discount";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DiscountDto.class));
    }

    public DiscountDto findById(int id) {
        String sql = "SELECT discount_id AS discountId, discount_name AS discountName, discount_type AS discountType, discount_value AS discountValue, discount_valid_from AS discountValidFrom, discount_valid_to AS discountValidTo FROM discount WHERE discount_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(DiscountDto.class));
    }

    public int update(DiscountDto discountDto) {
        String sql = "UPDATE discount SET discount_name = ?, discount_type = ?, discount_value = ?, discount_valid_from = ?, discount_valid_to = ? WHERE discount_id = ?";
        return jdbcTemplate.update(
                sql,
                discountDto.getDiscountName(),
                discountDto.getDiscountType(),
                discountDto.getDiscountValue(),
                discountDto.getDiscountValidFrom(),
                discountDto.getDiscountValidTo(),
                discountDto.getDiscountId()
        );
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM discount WHERE discount_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
