package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.RoleDto;
import ezee.hotel.management.crud.repository.RoleRepo;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public void insert(RoleDto dto) {
        roleRepo.insert(dto);
    }

    public List<RoleDto> getAll() {
        return roleRepo.findAll();
    }

    public RoleDto getById(int id) {
        return roleRepo.findById(id);
    }

    public void update(RoleDto dto) {
        roleRepo.update(dto);
    }

    public void delete(int id) {
        roleRepo.deleteById(id);
    }
}
