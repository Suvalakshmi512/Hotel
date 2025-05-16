package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.TableDto;
import ezee.hotel.management.crud.repository.TableRepo;

@Service
public class TableService {

    @Autowired
    private TableRepo tableRepo;

    public void insert(TableDto tableDto) {
        tableRepo.insert(tableDto);
    }

    public List<TableDto> getAll() {
        return tableRepo.findAll();
    }

    public TableDto getById(int id) {
        return tableRepo.findById(id);
    }

    public void update(TableDto tableDto) {
        tableRepo.update(tableDto);
    }

    public void delete(int id) {
        tableRepo.deleteById(id);
    }
}
