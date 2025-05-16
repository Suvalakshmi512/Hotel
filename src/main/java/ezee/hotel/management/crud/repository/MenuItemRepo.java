package ezee.hotel.management.crud.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.MenuCategoryDto;
import ezee.hotel.management.crud.dto.MenuItemDto;

@Repository
public class MenuItemRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final org.springframework.jdbc.core.RowMapper<MenuItemDto> rowMapper = (rs, rowNum) -> {
        MenuCategoryDto menu=new MenuCategoryDto();
        menu.setMenuCategoryId(rs.getInt("menu_category_id"));
        menu.setMenuCategoryName(rs.getString("menu_category_name"));
        menu.setMenuCategoryDescription(rs.getString("menu_category_description"));
        

        MenuItemDto menuItem = new MenuItemDto();
        menuItem.setMenuItemId(rs.getInt("menu_item_id"));
        menuItem.setMenuName(rs.getString("menu_name"));
        menuItem.setMenuPrice(rs.getDouble("menu_price"));
        menuItem.setAvailable(rs.getBoolean("is_available"));
        menuItem.setMenuCategoryDto(menu);

        return menuItem;
    };

    public int insertMenuItem(MenuItemDto item) {
        String sql = "INSERT INTO menu_item (menu_item_id, menu_name, menu_price, category_id, is_available) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            item.getMenuItemId(),
            item.getMenuName(),
            item.getMenuPrice(),
            item.getMenuCategoryDto().getMenuCategoryId(),
            item.isAvailable()
        );
    }

    public List<MenuItemDto> findAll() {
        String sql = "SELECT m.menu_category_id, m.menu_category_name, m.menu_category_description, mi.menu_item_id, mi.menu_name, mi.menu_price, mi.is_available, mi.category_id " +
                     "FROM menu_item mi " +
                     "JOIN menu_category m ON mi.category_id = m.menu_category_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public MenuItemDto findById(int id) {
        String sql = "SELECT m.menu_category_id, m.menu_category_name, m.menu_category_description, mi.menu_item_id, mi.menu_name, mi.menu_price, mi.is_available, mi.category_id " +
                "FROM menu_item mi " +
                "JOIN menu_category m ON mi.category_id = m.menu_category_id " +
                     " WHERE mi.menu_item_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int updateMenuItem(int id, MenuItemDto item) {
        String sql = "UPDATE menu_item SET menu_name = ?, menu_price = ?, category_id = ?, is_available = ? WHERE menu_item_id = ?";
        return jdbcTemplate.update(sql,
            item.getMenuName(),
            item.getMenuPrice(),
            item.getMenuCategoryDto().getMenuCategoryId(),
            item.isAvailable(),
            id
        );
    }

    public int deleteMenuItem(int id) {
        String sql = "DELETE FROM menu_item WHERE menu_item_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
