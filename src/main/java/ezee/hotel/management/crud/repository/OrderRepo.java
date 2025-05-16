package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.dto.TableDto;
@Repository
public class OrderRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertOrder(OrderDto orderdto) {
		String sql = "INSERT INTO order_deteail(order_id, customer_id, table_id, order_time, status)"
				+ "VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(
				sql,
				orderdto.getOrderId(),
				orderdto.getCustomerDto().getCustomerId(),
				orderdto.getTableDto().getTableId(),
				orderdto.getOrderTime(),
				orderdto.getStatus()
				);
	}
	    private final org.springframework.jdbc.core.RowMapper<OrderDto> rowMapper = (rs, rowNum) -> {
	        CustomerDto customer = new CustomerDto();
	        customer.setCustomerId(rs.getInt("customer_id"));
	        customer.setName(rs.getString("customer_name"));
	        customer.setPhone(rs.getLong("customer_phone"));
	        customer.setEmail(rs.getString("customer_email"));
	        customer.setRegisteredOn(rs.getString("customer_registered_on"));
	        
	        TableDto table=new TableDto();;
	        table.setTableId(rs.getInt("table_id"));
	        table.setTableNumber(rs.getInt("table_number"));
	        table.setCapacity(rs.getInt("capacity"));
	        table.setOccupied(rs.getBoolean("is_occupied"));

	        OrderDto order = new OrderDto();
	        order.setOrderId(rs.getInt("order_id"));
	        order.setOrderTime(rs.getString("order_time"));
	        order.setStatus(rs.getString("status"));
	        order.setCustomerDto(customer);
	        order.setTableDto(table);
	        return order;
	    };

	    public List<OrderDto> findAll() {
	    	String sql="SELECT o.order_id,o.order_time,o.status,o.customer_id,o.table_id,c.customer_name,c.customer_phone,c.customer_email,c.customer_registered_on,t.table_number,t.capacity,t.is_occupied "
	    			+ "FROM order_deteail o JOIN customer c ON o.customer_id = c.customer_id "
	    			+ "JOIN table_info t ON o.table_id = t.table_id";
	        return jdbcTemplate.query(sql, rowMapper);
	    }

	    public OrderDto findById(int id) {
	        String sql = "SELECT o.order_id,o.order_time,o.status,o.customer_id,o.table_id,c.customer_name,c.customer_phone,c.customer_email,c.customer_registered_on,t.table_number,t.capacity,t.is_occupied "
	    			+ "FROM order_deteail o JOIN customer c ON o.customer_id = c.customer_id "
	    			+ "JOIN table_info t ON o.table_id = t.table_id WHERE o.order_id = ?";
	        return jdbcTemplate.queryForObject(sql, rowMapper, id);
	    }

	    public int updateOrder(int id, OrderDto order) {
	        String sql = "UPDATE order_deteail SET customer_id = ?, table_id = ?, order_time = ?, status = ? WHERE order_id = ?";
	        return jdbcTemplate.update(
	            sql,
	            order.getCustomerDto().getCustomerId(),
	            order.getTableDto().getTableId(),
	            order.getOrderTime(),
	            order.getStatus(),
	            order.getOrderId()
	        );
	    }

	    public int deleteOrderById(int id) {
	        String sql = "DELETE FROM order_deteail WHERE order_id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	}



