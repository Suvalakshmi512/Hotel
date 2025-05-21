package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.exception.ErrorCode;
import ezee.hotel.management.crud.exception.ServiceException;
@Repository
public class CustomerRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean existsById(int id) {
	    String sql = "SELECT COUNT(*) FROM customer WHERE customer_id = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
	    return count != null && count > 0;
	}
	
	public int insert(CustomerDto customer) {
		if (existsById(customer.getCustomerId())) {
	        throw new ServiceException(ErrorCode.ID_ALREADY_EXISTS);
	    }
		String sql="Insert into customer(customer_id,customer_name,customer_phone,customer_email,customer_registered_on) values(?,?,?,?,?) ";
        int update = jdbcTemplate.update(sql,customer.getCustomerId(),customer.getName(),customer.getPhone(),customer.getEmail(),customer.getRegisteredOn());
		return update;
        
	}
	public List<CustomerDto> findAll(){
		String sql="Select customer_id,customer_name,customer_phone,customer_email,customer_registered_on from customer";
		 return jdbcTemplate.query(sql, (rs, rowNum) -> {
		        CustomerDto customerDto = new CustomerDto();
		        customerDto.setCustomerId(rs.getInt("customer_id"));
		        customerDto.setName(rs.getString("customer_name"));
		        customerDto.setPhone(rs.getLong("customer_phone"));
		        customerDto.setEmail(rs.getString("customer_email"));
		        customerDto.setRegisteredOn(rs.getString("customer_registered_on"));
		        return customerDto;
		    });
		}
	public CustomerDto findById(int id) {
	    String sql = "SELECT customer_id, customer_name, customer_phone, customer_email, customer_registered_on FROM customer WHERE customer_id = ?";

    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
	            CustomerDto customerDto = new CustomerDto();
	            customerDto.setCustomerId(rs.getInt("customer_id"));
	            customerDto.setName(rs.getString("customer_name"));
	            customerDto.setPhone(rs.getLong("customer_phone"));
	            customerDto.setEmail(rs.getString("customer_email"));
	            customerDto.setRegisteredOn(rs.getString("customer_registered_on"));
	            return customerDto;
	        });
    	} catch (EmptyResultDataAccessException e) {
    		throw new ServiceException(ErrorCode.ID_NOT_FOUND_EXCEPTION);
    	}
	}

	public int updateCustomer(CustomerDto customer) {
		String sql="UPDATE customer SET customer_name=?,customer_phone=?,customer_email=?,customer_registered_on=? WHERE customer_id=? ";
		return jdbcTemplate.update(sql,customer.getName(),customer.getPhone(),customer.getEmail(),customer.getRegisteredOn(),customer.getCustomerId());
	}
	
	public int deleteById(int id) {
	    String sql = "DELETE FROM customer WHERE customer_id= ?";
	    int rowsAffected = jdbcTemplate.update(sql, id);
	    if (rowsAffected == 0) {
	        throw new ServiceException(ErrorCode.ID_NOT_FOUND_EXCEPTION);
	    }
	    return rowsAffected;
	}

	}


		
	

		
	

	
	


