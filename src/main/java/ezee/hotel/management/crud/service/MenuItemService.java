package ezee.hotel.management.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezee.hotel.management.crud.dto.MenuItemDto;
import ezee.hotel.management.crud.repository.MenuItemRepo;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepo menuItemRepo;

    public String saveMenuItem(MenuItemDto item) {
        menuItemRepo.insertMenuItem(item);
		return "Inserted Successfully...!";
    }

    public List<MenuItemDto> getAllMenuItems() {
        return menuItemRepo.findAll();
    }

    public MenuItemDto getMenuItemById(int id) {
        return menuItemRepo.findById(id);
    }

    public String updateMenuItem(int id, MenuItemDto item) {
        menuItemRepo.updateMenuItem(id, item);
		return "Updated Successfully...!";
    }

    public int deleteMenuItem(int id) {
        return menuItemRepo.deleteMenuItem(id);
    }
}
