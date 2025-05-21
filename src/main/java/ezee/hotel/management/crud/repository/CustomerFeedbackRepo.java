package ezee.hotel.management.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.CustomerFeedBackDto;
import ezee.hotel.management.crud.dto.OrderDto;
import ezee.hotel.management.crud.exception.ErrorCode;
import ezee.hotel.management.crud.exception.ServiceException;

@Repository
public class CustomerFeedbackRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public boolean existsById(int id) {
	    String sql = "SELECT COUNT(*) FROM customer_feedback WHERE feedback_id = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
	    return count != null && count > 0;
	}

    public int insertCustomerFeedback(CustomerFeedBackDto customerFeedback) {
    	
    	 if (customerFeedback == null || customerFeedback.getCustomerDto() == null) {
    	        throw new ServiceException(ErrorCode.INVALID_INPUT);
    	    }

    	    if (existsById(customerFeedback.getFeedbackId())) {
    	        throw new ServiceException(ErrorCode.ID_ALREADY_EXISTS);
    	    }

        String sql = "INSERT INTO customer_feedback(feedback_id, customer_id,rating, feedback_comment, feedback_date) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            customerFeedback.getFeedbackId(),
            customerFeedback.getCustomerDto().getCustomerId(),
            customerFeedback.getRating(),
            customerFeedback.getFeedbackComment(),
            customerFeedback.getFeedbackDate()
        );
    }

    private final org.springframework.jdbc.core.RowMapper<CustomerFeedBackDto> rowMapper = (rs, rowNum) -> {
        CustomerDto customer = new CustomerDto();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setPhone(rs.getLong("customer_phone"));
        customer.setEmail(rs.getString("customer_email"));
        customer.setRegisteredOn(rs.getString("customer_registered_on"));
  
        CustomerFeedBackDto feedback = new CustomerFeedBackDto();
        feedback.setFeedbackId(rs.getInt("feedback_id"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setFeedbackComment(rs.getString("feedback_comment"));
        feedback.setFeedbackDate(rs.getString("feedback_date"));
        feedback.setCustomerDto(customer);;

        return feedback;
    };

    public List<CustomerFeedBackDto> findAll() {
    	String sql = " SELECT  cf.feedback_id, cf.customer_id, cf.rating, cf.feedback_comment, cf.feedback_date,c.customer_name, c.customer_phone, c.customer_email, c.customer_registered_on FROM customer_feedback cf JOIN customer c ON cf.customer_id = c.customer_id";
		return jdbcTemplate.query(sql,rowMapper);

    }

    public CustomerFeedBackDto findById(int feedbackId) {
        String sql = "SELECT  cf.feedback_id, cf.customer_id, cf.rating, cf.feedback_comment, cf.feedback_date,c.customer_name, c.customer_phone, c.customer_email, c.customer_registered_on FROM customer_feedback cf JOIN customer c ON cf.customer_id = c.customer_id where feedback_id = ?";
        try {
        return jdbcTemplate.queryForObject(sql, rowMapper, feedbackId);
        }catch (Exception e) {
			throw new ServiceException(ErrorCode.ID_NOT_FOUND_EXCEPTION);
		}
        }

    public int updateFeedback(int id,CustomerFeedBackDto feedback) {
    	if (feedback == null) {
            throw new ServiceException(ErrorCode.INVALID_INPUT);
        }

        if (id != feedback.getFeedbackId()) {
            throw new ServiceException(ErrorCode.ID_MISMATCH);
        }
        String sql = "UPDATE customer_feedback SET rating = ?, feedback_comment = ?, feedback_date = ? WHERE feedback_id = ?";
        
         int update = jdbcTemplate.update(sql, 
            feedback.getRating(),
            feedback.getFeedbackComment(),
            feedback.getFeedbackDate(),
            feedback.getFeedbackId()
        );
         if(update==0) {
        	 throw new ServiceException(ErrorCode.ID_NOT_FOUND_EXCEPTION); 
         }
		return update;
        
       
    }

    public int deleteFeedbackById(int feedbackId) {
        String sql = "DELETE FROM customer_feedback WHERE feedback_id = ?";
         int update = jdbcTemplate.update(sql, feedbackId);
         if (update == 0) {
 	        throw new ServiceException(ErrorCode.ID_NOT_FOUND_EXCEPTION);
 	    }
 	    return update;
    }
}
