package ezee.hotel.management.crud.dto;

public class OrderDto {
	private int orderId;  
	private CustomerDto customerDto;  
	private TableDto tableDto; 
	private String orderTime;           
	private String status;
	
	public OrderDto() {}
	
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	public TableDto getTableDto() {
		return tableDto;
	}
	public void setTableDto(TableDto tableDto) {
		this.tableDto = tableDto;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	
}
