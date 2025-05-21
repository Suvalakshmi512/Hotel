//package ezee.hotel.management.crud.exception;
//
//import java.time.LocalDateTime;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//public class GlobalException {
//	@ExceptionHandler(ServiceException.class)
//	 public ResponseEntity<ResponceException<Object>> handleServiceException(ServiceException ex){
//		ResponceException<Object> responce= new ResponceException<>(
//				ex.getErrorCode().getCode(),
//				ex.getErrorCode().getMessage(),
//				LocalDateTime.now()
//				);
//				
//		
//	}
//
//}
