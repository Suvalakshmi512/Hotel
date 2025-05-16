package ezee.hotel.management.crud.dto;

public class BillDto {
    private int billId;
    private OrderDto orderDto;
    private String billDate;
    private double billSubtotal;
    private DiscountDto billDiscountDto;
    private TaxDto taxdto;
    private double billTaxAmount;
    private double billTotalAmount;
    private String paymentMethod;
    public BillDto() {}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public OrderDto getOrderDto() {
		return orderDto;
	}
	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public double getBillSubtotal() {
		return billSubtotal;
	}
	public void setBillSubtotal(double billSubtotal) {
		this.billSubtotal = billSubtotal;
	}
	
	public TaxDto getTaxdto() {
		return taxdto;
	}
	public void setTaxdto(TaxDto taxdto) {
		this.taxdto = taxdto;
	}
	public double getBillTaxAmount() {
		return billTaxAmount;
	}
	public void setBillTaxAmount(double billTaxAmount) {
		this.billTaxAmount = billTaxAmount;
	}
	public double getBillTotalAmount() {
		return billTotalAmount;
	}
	public void setBillTotalAmount(double billTotalAmount) {
		this.billTotalAmount = billTotalAmount;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public DiscountDto getBillDiscountDto() {
		return billDiscountDto;
	}
	public void setBillDiscountDto(DiscountDto billDiscountDto) {
		this.billDiscountDto = billDiscountDto;
	}
	
   }
