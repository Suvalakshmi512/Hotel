package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.TaxDto;

@Repository
public class TaxRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(TaxDto taxDto) {
        String sql = "INSERT INTO tax (tax_id, tax_name, tax_percentage, tax_description) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            taxDto.getTaxId(),
            taxDto.getTaxName(),
            taxDto.getTaxPercentage(),
            taxDto.getTaxDescription());
    }

    public List<TaxDto> findAll() {
        String sql = "SELECT tax_id AS taxId, tax_name AS taxName, tax_percentage AS taxPercentage, tax_description AS taxDescription FROM tax";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaxDto.class));
    }

    public TaxDto findById(int id) {
        String sql = "SELECT tax_id AS taxId, tax_name AS taxName, tax_percentage AS taxPercentage, tax_description AS taxDescription FROM tax WHERE tax_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(TaxDto.class));
    }

    public int update(TaxDto taxDto) {
        String sql = "UPDATE tax SET tax_name = ?, tax_percentage = ?, tax_description = ? WHERE tax_id = ?";
        return jdbcTemplate.update(sql,
            taxDto.getTaxName(),
            taxDto.getTaxPercentage(),
            taxDto.getTaxDescription(),
            taxDto.getTaxId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM tax WHERE tax_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
