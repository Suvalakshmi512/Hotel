package ezee.hotel.management.crud.dto;

public class StaffAttenanceDto {
	private int attendanceId; 
	private StaffDto staffDto;  
	private String date;  
	private String checkIn;  
	private String checkOut;
	public StaffAttenanceDto() {}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAttendanceId() {
		return attendanceId;
	}


	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}


	public String getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}


	public String getCheckOut() {
		return checkOut;
	}


	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}


	public StaffDto getStaffDto() {
		return staffDto;
	}
	public void setStaffDto(StaffDto staffDto) {
		this.staffDto = staffDto;
	}
	

}
