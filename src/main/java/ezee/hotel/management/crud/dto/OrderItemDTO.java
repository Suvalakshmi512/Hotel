package ezee.hotel.management.crud.dto;
public class OrderItemDTO {
    private int orderItemId;
    private OrderDto orderDto;
    private MenuItemDto menuitemDto;
    private int quantity;
    private double subTotal;
	
    public OrderItemDTO() {}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderDto getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}

	public MenuItemDto getMenuitemDto() {
		return menuitemDto;
	}

	public void setMenuitemDto(MenuItemDto menuitemDto) {
		this.menuitemDto = menuitemDto;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
    
   
}
