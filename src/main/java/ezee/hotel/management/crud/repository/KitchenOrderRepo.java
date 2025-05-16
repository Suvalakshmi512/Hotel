package ezee.hotel.management.crud.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.KitchenOrderDto;
import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.dto.RoleDto;
import ezee.hotel.management.crud.dto.TableDto;


@Repository
public class KitchenOrderRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertKitchenOrder(KitchenOrderDto kitchenOrder) {
        String sql = "INSERT INTO kitchen_order (kitchen_order_id, order_id, chef_id, status, prepared_time) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            kitchenOrder.getKitchenOrderId(),
            kitchenOrder.getOrderDto().getOrderId(),
            kitchenOrder.getRoleDto().getRoleId(),
            kitchenOrder.getStatus(),
            kitchenOrder.getPreparedTime()
        );
    }

    private final org.springframework.jdbc.core.RowMapper<KitchenOrderDto> rowMapper = (rs, rowNum) -> {
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

        RoleDto chef=new RoleDto();
        chef.setRoleId(rs.getInt("role_id"));
        chef.setRoleName(rs.getString("role_name"));
        chef.setRoleDescription(rs.getString("role_description"));
        
        KitchenOrderDto kitchenOrder = new KitchenOrderDto();
        kitchenOrder.setKitchenOrderId(rs.getInt("kitchen_order_id"));
        kitchenOrder.setStatus(rs.getString("status"));
        kitchenOrder.setPreparedTime(rs.getString("prepared_time"));
        kitchenOrder.setOrderDto(order);
        kitchenOrder.setRoleDto(chef);
        
        return kitchenOrder;
    };

    public List<KitchenOrderDto> findAll() {
        String sql = "SELECT ko.kitchen_order_id, ko.order_id, ko.status, ko.prepared_time, ko.chef_id, " +
                     "r.role_id, r.role_name, r.role_description, " + 
                     "od.order_id, od.customer_id, od.table_id, od.order_time, od.status, "
                     + "c.customer_id,c.customer_name,c.customer_phone,c.customer_email,c.customer_registered_on,t.table_id,t.table_number,t.capacity,t.is_occupied " +
                     "FROM kitchen_order ko " +
                     "JOIN staff_role r ON ko.chef_id = r.role_id " +
                     "JOIN order_deteail od ON ko.order_id = od.order_id " +
                     "JOIN table_info t ON od.table_id=t.table_id "
                     + " JOIN customer c ON od.customer_id=c.customer_id ";

        return jdbcTemplate.query(sql, rowMapper);
    }


    public KitchenOrderDto findById(int id) {
        String sql = "SELECT ko.kitchen_order_id, ko.order_id, ko.status, ko.prepared_time, ko.chef_id, " +
                "r.role_id, r.role_name, r.role_description, " + 
                "od.order_id, od.customer_id, od.table_id, od.order_time, od.status, "
                + "c.customer_id,c.customer_name,c.customer_phone,c.customer_email,c.customer_registered_on,t.table_id,t.table_number,t.capacity,t.is_occupied " +
                "FROM kitchen_order ko " +
                "JOIN staff_role r ON ko.chef_id = r.role_id " +
                "JOIN order_deteail od ON ko.order_id = od.order_id " +
                "JOIN table_info t ON od.table_id=t.table_id "
                + " JOIN customer c ON od.customer_id=c.customer_id "
                + "WHERE ko.kitchen_order_id=?";

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int updateKitchenOrder(int id, KitchenOrderDto kitchenOrder) {
        String sql = "UPDATE kitchen_order SET order_id = ?, chef_id = ?, status = ?, prepared_time = ? WHERE kitchen_order_id = ?";
        return jdbcTemplate.update(sql,
            kitchenOrder.getOrderDto().getOrderId(),
            kitchenOrder.getRoleDto().getRoleId(),
            kitchenOrder.getStatus(),
            kitchenOrder.getPreparedTime(),
            kitchenOrder.getKitchenOrderId()
        );
    }

    public int deleteKitchenOrder(int id) {
        String sql = "DELETE FROM kitchen_order WHERE kitchen_order_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
