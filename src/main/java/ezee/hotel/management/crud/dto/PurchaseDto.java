package ezee.hotel.management.crud.dto;

public class PurchaseDto {
    private int purchaseId;
    private SupplierDto supplierDto;
    private  InventryItemDto inventryDto;
    private int purchaseQuantity;
    private String purchaseOrderDate;
    private String purchaseDeliveryDate;

    

    public SupplierDto getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(SupplierDto supplierDto) {
        this.supplierDto = supplierDto;
    }

	public InventryItemDto getInventryDto() {
		return inventryDto;
	}

	public void setInventryDto(InventryItemDto inventryDto) {
		this.inventryDto = inventryDto;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(String purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public String getPurchaseDeliveryDate() {
		return purchaseDeliveryDate;
	}

	public void setPurchaseDeliveryDate(String purchaseDeliveryDate) {
		this.purchaseDeliveryDate = purchaseDeliveryDate;
	}
}
