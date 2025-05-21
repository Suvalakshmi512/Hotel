package ezee.hotel.management.crud.exception;

public enum ErrorCode {
ID_NOT_FOUND_EXCEPTION("101","Id not found"),
ID_ALREADY_EXISTS("102","Id Already Exists"),
ID_MISMATCH("103", "Path ID and payload ID do not match."),
INVALID_INPUT("104", "Invalid input or missing required data."),
DATABASE_ERROR("105", "Database operation failed.");

	
	
	private final String code;
	private final String message;
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;

}
}
