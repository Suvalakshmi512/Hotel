package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.exception.ErrorCode;
import ezee.hotel.management.crud.exception.ResponceException;
import ezee.hotel.management.crud.exception.ServiceException;
import ezee.hotel.management.crud.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public void saveCustomer(CustomerDto cutomer) {
		customerRepository.insert(cutomer);
	}
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll();
	}
	public ResponceException<CustomerDto> getById(int id){
		 CustomerDto byId = customerRepository.findById(id);
		
	   return ResponceException.success(byId);
	}
	public void updateCustomer(CustomerDto cutomer) {
		customerRepository.updateCustomer(cutomer);
		
	}
	public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
