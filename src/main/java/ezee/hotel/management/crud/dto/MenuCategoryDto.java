package ezee.hotel.management.crud.dto;

public class MenuCategoryDto {

	
    private int menuCategoryId;
    private String menuCategoryName;
    private String menuCategoryDescription;

    public int getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(int menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getMenuCategoryName() {
        return menuCategoryName;
    }

    public void setMenuCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }

    public String getMenuCategoryDescription() {
        return menuCategoryDescription;
    }

    public void setMenuCategoryDescription(String menuCategoryDescription) {
        this.menuCategoryDescription = menuCategoryDescription;
    }
}
