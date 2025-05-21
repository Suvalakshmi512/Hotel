package ezee.hotel.management.crud.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID=123L;
	

	   
    private final  ErrorCode errorCode;
  
	public ServiceException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	

}
