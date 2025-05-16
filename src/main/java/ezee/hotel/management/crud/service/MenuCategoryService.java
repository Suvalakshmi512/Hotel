package ezee.hotel.management.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.MenuCategoryDto;
import ezee.hotel.management.crud.repository.MenuCategoryRepo;

@Service
public class MenuCategoryService {

    @Autowired
    private MenuCategoryRepo repo;

    public void insert(MenuCategoryDto dto) {
        repo.insert(dto);
    }

    public List<MenuCategoryDto> getAll() {
        return repo.findAll();
    }

    public MenuCategoryDto getById(int id) {
        return repo.findById(id);
    }

    public void update(MenuCategoryDto dto) {
        repo.update(dto);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
