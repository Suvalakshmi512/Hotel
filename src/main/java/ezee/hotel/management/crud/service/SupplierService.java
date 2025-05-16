package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.SupplierDto;
import ezee.hotel.management.crud.repository.SupplierRepo;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public void insert(SupplierDto dto) {
        supplierRepo.insert(dto);
    }

    public List<SupplierDto> getAll() {
        return supplierRepo.findAll();
    }

    public SupplierDto getById(int id) {
        return supplierRepo.findById(id);
    }

    public void update(SupplierDto dto) {
        supplierRepo.update(dto);
    }

    public void delete(int id) {
        supplierRepo.deleteById(id);
    }
}
