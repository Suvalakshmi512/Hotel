package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.CustomerFeedBackDto;
import ezee.hotel.management.crud.repository.CustomerFeedbackRepo;

@Service
public class CustomerFeedBackService {
	@Autowired
	private CustomerFeedbackRepo customerFeedRepo;
	
	public void saveCustomerFB(CustomerFeedBackDto custeomerFeedback) {
		 customerFeedRepo.insertCustomerFeedback(custeomerFeedback);
		
	}
	public List<CustomerFeedBackDto> getAll()
	{
		return customerFeedRepo.findAll();
	}
	public CustomerFeedBackDto getById(int id){
		return customerFeedRepo.findById(id);
	}
	public void Update(int id,CustomerFeedBackDto custeomerFeedback) {
		customerFeedRepo.updateFeedback(id, custeomerFeedback);
	}
	
	public int DeleteCustomerFeedback(int id) {
		return customerFeedRepo.deleteFeedbackById(id);
		
	}

}
