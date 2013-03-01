package it.bplus.exception;


import it.bplus.util.IErrorCodes;

//import org.hibernate.HibernateException;
//import org.hibernate.exception.DataException;

public class DataAccessException extends BaseException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode = "0";
	private String errorDescription = null;
	private Throwable exc;


	public DataAccessException(String errorCode,String sMessage) {
		super(sMessage);
		this.errorDescription = sMessage;
		this.errorCode = errorCode;
	}
	
	public DataAccessException(String errorCode, String errorDescription, Throwable exc){
		super(errorDescription);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.setExc(exc);
	}

	public DataAccessException(String sMessage) {
		super(sMessage);
		this.errorDescription = sMessage;
		this.errorCode = IErrorCodes.DB_ERROR_KEY;
	}

//	public DataAccessException(final String message, final HibernateException he) {
//		super(he.getMessage());
//		this.errorDescription = he.getMessage();
//
//	}
//
//	public DataAccessException(final String message, final DataException he) {
//		super(he.getMessage());
//		this.errorCode = Integer.toString(he.getErrorCode());
//		this.errorDescription = he.getMessage();
//
//	}


	public String toString() {
		String message = "DataAccessException: ErrorCode: " + getErrorCode() + " Message: " + getErrorDescription();
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public void setExc(Throwable exc) {
		this.exc = exc;
	}

	public Throwable getExc() {
		return exc;
	}
}
