package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.CustomerDto;
import ezee.hotel.management.crud.dto.InventryItemDto;
import ezee.hotel.management.crud.repository.InventoryRepo;

@Service
public class IventoryService {
	@Autowired
	private InventoryRepo inventoryRepo;
	
	public void saveInventry(InventryItemDto inventry) {
		inventoryRepo.insert(inventry);
    }
	public List<InventryItemDto> getlAInventryItemDtos(){
		return inventoryRepo.findAll();
		
	}
	public InventryItemDto getById(int id) {
		return inventoryRepo.findById(id);
		
	}
	public void updateInventry(InventryItemDto inventry) {
		inventoryRepo.updateInventry(inventry);
	}
	public void deleteInventry(int id) {
		inventoryRepo.deleteById(id);
	}
	}
