package ezee.hotel.management.crud.dto;

public class KitchenOrderDto {

    private int kitchenOrderId;
    private OrderDto orderDto;
    private RoleDto roleDto;
    private String status;
    private String preparedTime;

   

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}

	public int getKitchenOrderId() {
		return kitchenOrderId;
	}

	public void setKitchenOrderId(int kitchenOrderId) {
		this.kitchenOrderId = kitchenOrderId;
	}

	public String getPreparedTime() {
		return preparedTime;
	}

	public void setPreparedTime(String preparedTime) {
		this.preparedTime = preparedTime;
	}

	
}
