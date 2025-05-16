package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.SupplierDto;

@Repository
public class SupplierRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(SupplierDto dto) {
        String sql = "INSERT INTO supplier (supplier_id, supplier_name, supplier_contact_person, supplier_phone, supplier_email) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            dto.getSupplierId(),
            dto.getSupplierName(),
            dto.getSupplierContactPerson(),
            dto.getSupplierPhone(),
            dto.getSupplierEmail()
        );
    }

    public List<SupplierDto> findAll() {
        String sql = "SELECT supplier_id AS supplierId, supplier_name AS supplierName, supplier_contact_person AS supplierContactPerson, supplier_phone AS supplierPhone, supplier_email AS supplierEmail FROM supplier";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SupplierDto.class));
    }

    public SupplierDto findById(int id) {
        String sql = "SELECT supplier_id AS supplierId, supplier_name AS supplierName, supplier_contact_person AS supplierContactPerson, supplier_phone AS supplierPhone, supplier_email AS supplierEmail FROM supplier WHERE supplier_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(SupplierDto.class));
    }

    public int update(SupplierDto dto) {
        String sql = "UPDATE supplier SET supplier_name = ?, supplier_contact_person = ?, supplier_phone = ?, supplier_email = ? WHERE supplier_id = ?";
        return jdbcTemplate.update(sql,
            dto.getSupplierName(),
            dto.getSupplierContactPerson(),
            dto.getSupplierPhone(),
            dto.getSupplierEmail(),
            dto.getSupplierId()
        );
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
