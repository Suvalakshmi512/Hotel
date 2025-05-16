package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.DiscountDto;
import ezee.hotel.management.crud.repository.DiscountRepo;
@Service
public class DiscountService {
	@Autowired
	private DiscountRepo discountRepo;
	
	public void Insert(DiscountDto discountDto) {
		discountRepo.insert(discountDto);
	}
	public List<DiscountDto> getAll(){
		return discountRepo.findAll();
		
	}
	public DiscountDto getById(int id) {
		return discountRepo.findById(id);
		
	}
	public void update(DiscountDto discountDto) {
		discountRepo.update(discountDto);
	}
	public void delete(int id) {
		discountRepo.deleteById(id);
	}

}
