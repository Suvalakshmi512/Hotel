package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.MenuCategoryDto;

@Repository
public class MenuCategoryRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(MenuCategoryDto dto) {
        String sql = "INSERT INTO menu_category (menu_category_id, menu_category_name, menu_category_description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, dto.getMenuCategoryId(), dto.getMenuCategoryName(), dto.getMenuCategoryDescription());
    }

    public List<MenuCategoryDto> findAll() {
        String sql = "SELECT menu_category_id AS menuCategoryId, menu_category_name AS menuCategoryName, menu_category_description AS menuCategoryDescription FROM menu_category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuCategoryDto.class));
    }

    public MenuCategoryDto findById(int id) {
        String sql = "SELECT menu_category_id AS menuCategoryId, menu_category_name AS menuCategoryName, menu_category_description AS menuCategoryDescription FROM menu_category WHERE menu_category_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(MenuCategoryDto.class));
    }

    public int update(MenuCategoryDto dto) {
        String sql = "UPDATE menu_category SET menu_category_name = ?, menu_category_description = ? WHERE menu_category_id = ?";
        return jdbcTemplate.update(sql, dto.getMenuCategoryName(), dto.getMenuCategoryDescription(), dto.getMenuCategoryId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM menu_category WHERE menu_category_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
