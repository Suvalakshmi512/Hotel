package ezee.hotel.management.crud.dto;

public class ReservationDto {
    private int reservationId;
    private CustomerDto customerDto;
    private TableDto tableDto;
    private String reservationTime;
    private int guestsCount;
    private String status;

    
    public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public int getGuestsCount() {
		return guestsCount;
	}

	public void setGuestsCount(int guestsCount) {
		this.guestsCount = guestsCount;
	}

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
}
