package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.InventryItemDto;

@Repository
public class InventoryRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int insert(InventryItemDto inventoryDto) {
		String sql="Insert into inventory_item (inventory_item_id,inventory_name,inventory_quantity,inventory_unit,inventory_last_updated) values(?,?,?,?,?)";
		return jdbcTemplate.update(sql,inventoryDto.getItemId(),inventoryDto.getName(),inventoryDto.getQuantity(),inventoryDto.getUnit(),inventoryDto.getLastupdated());
	}
	public List<InventryItemDto> findAll() {
	    String sql = "SELECT " +
	                 "inventory_item_id AS item_id, " +
	                 "inventory_name AS name, " +
	                 "inventory_quantity AS quantity, " +
	                 "inventory_unit AS unit, " +
	                 "inventory_last_updated AS lastupdated " +
	                 "FROM inventory_item";
	                 
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(InventryItemDto.class));
	}

	public InventryItemDto findById(int id) {
	    String sql = "SELECT " +
	                 "inventory_item_id AS item_id, " +
	                 "inventory_name AS name, " +
	                 "inventory_quantity AS quantity, " +
	                 "inventory_unit AS unit, " +
	                 "inventory_last_updated AS lastupdated " +
	                 "FROM inventory_item WHERE inventory_item_id = ?";

	    return jdbcTemplate.queryForObject(
	        sql,
	        new Object[]{id},
	        new BeanPropertyRowMapper<>(InventryItemDto.class)
	    );
	}

	public int updateInventry(InventryItemDto inventryDto) {
		String sql="Update inventory_item set inventory_name=?, inventory_quantity=?, inventory_unit=?, inventory_last_updated=? where inventory_item_id=?";
		return jdbcTemplate.update(sql,inventryDto.getName(),inventryDto.getQuantity(),inventryDto.getUnit(),inventryDto.getLastupdated(),inventryDto.getItemId());
	}
	public int deleteById(int id) {
		String sql="DELETE FROM inventory_item where inventory_item_id=? ";
		return jdbcTemplate.update(sql, id);
	}


}
