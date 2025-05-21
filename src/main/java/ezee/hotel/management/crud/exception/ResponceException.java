package ezee.hotel.management.crud.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponceException<T>{
	 private Integer status;
	    private String message;
	    private Integer errorCode;
	    private T data;
	    private String time;

	    public ResponceException(Integer status, String message, Integer errorCode, T data, String time) {
			super();
			this.status = status;
			this.message = message;
			this.errorCode = errorCode;
			this.data = data;
			this.time = time;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Integer getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(Integer errorCode) {
			this.errorCode = errorCode;
		}
		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}


		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}


		public static <T> ResponceException<T> success(T data) {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        return new ResponceException<>(200, null , null, data, LocalDateTime.now().format(formatter));
	    }

	   
	    public static <T> ResponceException<T> error(ErrorCode errorcode) {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        return new ResponceException<T>(null, errorcode.getMessage(),Integer.valueOf(errorcode.getCode()) , null, LocalDateTime.now().format(formatter));
	    }
}
