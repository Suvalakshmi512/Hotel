package ezee.hotel.management.crud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.BillDto;
import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.DiscountDto;
import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.dto.OrderItemDTO;
import ezee.hotel.management.crud.dto.TableDto;
import ezee.hotel.management.crud.dto.TaxDto;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class BillRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<BillDto> rowMapper = (rs, rowNum) -> {
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
	        
	        DiscountDto discountDto=new DiscountDto();
	        discountDto.setDiscountId(rs.getInt("discount_id"));
	        discountDto.setDiscountName(rs.getString("discount_name"));
	        discountDto.setDiscountType(rs.getString("discount_type"));
	        discountDto.setDiscountValidFrom(rs.getDate("discount_valid_from"));
	        discountDto.setDiscountValidTo(rs.getDate("discount_valid_to"));
	        discountDto.setDiscountValue(rs.getDouble("discount_value"));
	        
	        TaxDto tax=new TaxDto();
	        tax.setTaxId(rs.getInt("tax_id"));
	        tax.setTaxName(rs.getString("tax_name"));
	        tax.setTaxPercentage(rs.getDouble("tax_percentage"));
	        tax.setTaxDescription(rs.getString("tax_description"));
        
	        BillDto bill = new BillDto();
	        bill.setBillId(rs.getInt("bill_id"));
         	bill.setBillDate(rs.getString("bill_date"));
         	bill.setBillSubtotal(rs.getDouble("bill_subtotal"));
         	bill.setBillDiscountDto(discountDto);
         	bill.setTaxdto(tax);
         	bill.setBillTaxAmount(rs.getDouble("bill_tax_amount"));
         	bill.setBillTotalAmount(rs.getDouble("bill_total_amount"));
         	bill.setOrderDto(order);
         	return bill;
    	
    };

    public int insertBill(BillDto bill) {
    	 if (bill.getTaxdto() == null) {
    	        throw new IllegalArgumentException("TaxDto is null. Cannot insert bill without tax details.");
    	    }
        String sql =  "{ CALL EZEE_SP_INSERT_BILL_INFO(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
         int update = jdbcTemplate.update(sql, 
                bill.getBillId(),
                bill.getOrderDto().getOrderId(),
                bill.getBillDate(),
                bill.getBillSubtotal(),
                bill.getBillDiscountDto().getDiscountId(),
                bill.getTaxdto().getTaxId(),
                bill.getBillSubtotal(),
                bill.getBillTotalAmount()
        );
        String taxCalcSql = "{ CALL EZEE_SP_CALCULATE_AND_UPDATE_TAX(?) }";
        jdbcTemplate.update(taxCalcSql, bill.getBillId());

        return update;
    }

    public List<BillDto> findAll() {
        String sql = "SELECT o.order_id, o.order_time, o.status, o.customer_id, o.table_id, "
        		+ "c.customer_name, c.customer_phone, c.customer_email, c.customer_registered_on, "
        		+ "t.table_number, t.table_capacity, t.table_is_occupied, b.bill_id, b.bill_date, b.bill_subtotal, b.bill_discount_amount, b.bill_tax_amount, b.bill_total_amount, "
        		+ "d.discount_id, d.discount_name, d.discount_type, d.discount_valid_from, d.discount_valid_to, d.discount_value, "
        		+ "tx.tax_id, tx.tax_name, tx.tax_percentage, tx.tax_description "
        		+ "FROM order_detail o JOIN customer c ON o.customer_id = c.customer_id "
        		+ "JOIN table_info t ON o.table_id = t.table_id "
        		+ "JOIN bill_detail b ON o.order_id = b.order_id "
        		+ "LEFT JOIN discount d ON b.discount_id = d.discount_id "
        		+ "LEFT JOIN tax tx ON b.tax_id = tx.tax_id";

        return jdbcTemplate.query(sql, rowMapper);
       
    }

    public BillDto findById(int id) {
        String sql = "SELECT o.order_id,o.order_time,o.status,o.customer_id,o.table_id,c.customer_name,c.customer_phone,c.customer_email,c.customer_registered_on,t.table_number,t.capacity,t.is_occupied, "
        		+ "b.bill_id, b.bill_date, b.bill_subtotal, b.bill_discount_amount, b.bill_tax_amount, b.bill_total_amount, b.order_id "
    			+ "FROM order_deteail o JOIN customer c ON o.customer_id = c.customer_id "
    			+ "JOIN table_info t ON o.table_id = t.table_id "
    			+ "JOIN bill_detail b ON o.order_id=b.order_id "
        		+ "WHERE bill_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int updateBill(int id, BillDto bill) {
        String sql = "UPDATE bill SET order_id = ?, bill_date = ?, bill_subtotal = ?, bill_discount_amount = ?, bill_tax_amount = ?, bill_total_amount = ? WHERE bill_id = ?";
        return jdbcTemplate.update(sql,
                bill.getOrderDto().getOrderId(),
                bill.getBillDate(),
                bill.getBillSubtotal(),
                bill.getBillDiscountDto().getDiscountId(),
                bill.getBillSubtotal(),
                bill.getBillTotalAmount(),
                id
        );
    }

    public int deleteBill(int id) {
        String sql = "DELETE FROM bill WHERE bill_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
