package ezee.hotel.management.crud.dto;

public class CustomerFeedBackDto {
	private int feedbackId;
	private CustomerDto customerDto;
	private int rating; 
	private String feedbackComment;
	private String feedbackDate;
	public CustomerFeedBackDto() {}
	
	
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}


	public int getFeedbackId() {
		return feedbackId;
	}


	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}


	public String getFeedbackComment() {
		return feedbackComment;
	}


	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}


	public String getFeedbackDate() {
		return feedbackDate;
	}


	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	
	

}
