package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.CustomerDto;
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
	public CustomerDto getById(int id){
		return customerRepository.findById(id);
		
	}
	public void updateCustomer(CustomerDto cutomer) {
		customerRepository.updateCustomer(cutomer);
		
	}
	public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
	

}
