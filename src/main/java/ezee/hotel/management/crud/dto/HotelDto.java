package ezee.hotel.management.crud.dto;

public class HotelDto {
    private int hotelId;
    private String hotelName;
    private String hotelLocation;
    private String hotelBranch;

   
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getHotelBranch() {
        return hotelBranch;
    }

    public void setHotelBranch(String hotelBranch) {
        this.hotelBranch = hotelBranch;
    }
}
