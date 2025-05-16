package ezee.hotel.management.crud.dto;

public class MenuItemDto {
    private int menuItemId;
    private String menuName;
    private double menuPrice;
    private boolean isAvailable;
    private MenuCategoryDto menuCategoryDto;
	public int getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public double getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public MenuCategoryDto getMenuCategoryDto() {
		return menuCategoryDto;
	}
	public void setMenuCategoryDto(MenuCategoryDto menuCategoryDto) {
		this.menuCategoryDto = menuCategoryDto;
	}

    

   
}
