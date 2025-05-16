package ezee.hotel.management.crud.dto;

import java.sql.Date;

public class DiscountDto {
    private int discountId;
    private String discountName;
    private String discountType;
    private double discountValue;
    private Date discountValidFrom;
    private Date discountValidTo;
	public int getDiscountId() {
		return discountId;
	}
	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public double getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}
	public Date getDiscountValidFrom() {
		return discountValidFrom;
	}
	public void setDiscountValidFrom(Date discountValidFrom) {
		this.discountValidFrom = discountValidFrom;
	}
	public Date getDiscountValidTo() {
		return discountValidTo;
	}
	public void setDiscountValidTo(Date discountValidTo) {
		this.discountValidTo = discountValidTo;
	}

    
}
