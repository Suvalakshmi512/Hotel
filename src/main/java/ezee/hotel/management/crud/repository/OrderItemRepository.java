package ezee.hotel.management.crud.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.MenuCategoryDto;
import ezee.hotel.management.crud.dto.MenuItemDto;
import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.dto.OrderItemDTO;
import ezee.hotel.management.crud.dto.TableDto;

import java.util.List;

@Repository
public class OrderItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<OrderItemDTO> rowMapper = (rs, rowNum) -> {
    		CustomerDto customer = new CustomerDto();
 	        customer.setCustomerId(rs.getInt("customer_id"));
 	        customer.setName(rs.getString("customer_name"));
 	        customer.setPhone(rs.getLong("customer_phone"));
 	        customer.setEmail(rs.getString("customer_email"));
 	        customer.setRegisteredOn(rs.getString("customer_registered_on"));
 	        
 	        TableDto table=new TableDto();;
 	        table.setTableId(rs.getInt("table_id"));
 	        table.setTableNumber(rs.getInt("table_number"));
 	        table.setCapacity(rs.getInt("table_capacity"));
 	        table.setOccupied(rs.getBoolean("table_is_occupied"));
 	        
 	        OrderDto order = new OrderDto();
 	        order.setOrderId(rs.getInt("order_id"));
 	        order.setOrderTime(rs.getString("order_time"));
 	        order.setStatus(rs.getString("status"));
 	        order.setCustomerDto(customer);
 	        order.setTableDto(table);
 	        
 	        MenuCategoryDto menu1=new MenuCategoryDto();
	        menu1.setMenuCategoryId(rs.getInt("menu_category_id"));
	        menu1.setMenuCategoryName(rs.getString("menu_category_name"));
	        menu1.setMenuCategoryDescription(rs.getString("menu_category_description"));
	        
 	        MenuItemDto menuItem = new MenuItemDto();
	        menuItem.setMenuItemId(rs.getInt("menu_item_id"));
	        menuItem.setMenuName(rs.getString("menu_name"));
	        menuItem.setMenuPrice(rs.getDouble("menu_price"));
	        menuItem.setAvailable(rs.getBoolean("is_available"));
	        menuItem.setMenuCategoryDto(menu1);
 	        
 	        OrderItemDTO orderitem=new OrderItemDTO();
 	       orderitem.setOrderItemId(rs.getInt("order_item_id"));
 	       orderitem.setOrderDto(order);
 	       orderitem.setMenuitemDto(menuItem);
 	       orderitem.setQuantity(rs.getInt("quantity"));
 	       orderitem.setSubTotal(rs.getDouble("subtotal"));
 	        
			return orderitem;
        
    };

    public int save(OrderItemDTO orderItem) {
        String sql = "INSERT INTO order_item (order_item_id,order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";
         int update = jdbcTemplate.update(sql, orderItem.getOrderItemId(), orderItem.getOrderDto().getOrderId(), orderItem.getMenuitemDto().getMenuItemId(), orderItem.getQuantity(),0);
        String updateSubTotal= "{CALL EZEE_SP_CALCULATE_SUB_TOTAL()}";
         jdbcTemplate.update(updateSubTotal);
         return update;
        
    }

    public List<OrderItemDTO> findAll() {
    	String sql="SELECT o.order_id, o.order_time, o.status, o.customer_id, o.table_id, "
    			+ "oi.subtotal, c.customer_name, c.customer_phone, c.customer_email, c.customer_registered_on,"
    			+ " t.table_number, t.table_capacity, t.table_is_occupied, "
    			+ "m.menu_category_id, m.menu_category_name, m.menu_category_description, "
    			+ "mi.menu_item_id, mi.menu_name, mi.menu_price, mi.is_available, mi.category_id, "
    			+ "oi.order_item_id, oi.order_id, oi.item_id, oi.quantity "
    			+ "FROM order_deteail o JOIN customer c ON o.customer_id = c.customer_id "
    			+ "JOIN table_info t ON o.table_id = t.table_id "
    			+ "JOIN order_item oi ON o.order_id=oi.order_id "
    			+ "JOIN menu_item mi ON oi.item_id=mi.menu_item_id "
    			+ "JOIN menu_category m ON  mi.category_id = m.menu_category_id ";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public OrderItemDTO findById(int id) {
        String sql = "SELECT o.order_id,o.order_time,o.status,o.customer_id,o.table_id,oi.subtotal,c.customer_name,c.customer_phone,c.customer_email,c.customer_registered_on,t.table_number,t.table_capacity,t.table_is_occupied, "
    			+ "m.menu_category_id, m.menu_category_name, m.menu_category_description, mi.menu_item_id, mi.menu_name, mi.menu_price, mi.is_available, mi.category_id, "
    			+ "oi.order_item_id, oi.order_id, oi.item_id, oi.quantity "
    			+ "FROM order_deteail o JOIN customer c ON o.customer_id = c.customer_id "
    			+ "JOIN table_info t ON o.table_id = t.table_id "
    			+ "JOIN order_item oi ON o.order_id=oi.order_id "
    			+ "JOIN menu_item mi ON oi.item_id=mi.menu_item_id "
    			+ "JOIN menu_category m ON  mi.category_id = m.menu_category_id "
        		+ "WHERE order_item_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int update(OrderItemDTO orderItem) {
        String sql = "UPDATE order_item SET order_id = ?, item_id = ?, quantity = ? WHERE order_item_id = ?";
        return jdbcTemplate.update(sql, orderItem.getOrderItemId(), orderItem.getOrderDto().getOrderId(), orderItem.getMenuitemDto().getMenuItemId(), orderItem.getQuantity());
    }

    public int delete(int id) {
        String sql = "DELETE FROM order_item WHERE order_item_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
