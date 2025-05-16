package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.TaxDto;
import ezee.hotel.management.crud.repository.TaxRepo;

@Service
public class TaxService {

    @Autowired
    private TaxRepo taxRepo;

    public void insert(TaxDto taxDto) {
        taxRepo.insert(taxDto);
    }

    public List<TaxDto> getAll() {
        return taxRepo.findAll();
    }

    public TaxDto getById(int id) {
        return taxRepo.findById(id);
    }

    public void update(TaxDto taxDto) {
        taxRepo.update(taxDto);
    }

    public void delete(int id) {
        taxRepo.deleteById(id);
    }
}
