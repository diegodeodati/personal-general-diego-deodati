package it.bplus.exception;

import it.bplus.util.UniqueIDGenerator;



public class BusinessLayerException extends BaseException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorDescription = null;
	private DataAccessException dae = null;
	private String tokenId=UniqueIDGenerator.getUniqueID();


	public BusinessLayerException(String errorCode,String errorDescription) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorDescription=errorDescription;
	}

	public BusinessLayerException(String errorCode) {
		super(errorCode);
		this.errorDescription = "";
		this.errorCode=errorCode;
	}


	public BusinessLayerException(String errorCode,String errorDescription, DataAccessException dae) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorDescription=errorDescription;
		this.dae=dae;
	}

	public BusinessLayerException(String errorCode, DataAccessException dae) {
		super(errorCode);
		this.errorDescription = "";
		this.errorCode=errorCode;
		this.dae=dae;
	}


	public DataAccessException getDae() {
		return dae;
	}

	public void setDae(DataAccessException dae) {
		this.dae = dae;
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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String toString() {
		String message = "BusinessLayerException: ErrorCode: " + getErrorCode() + " Message: " + getErrorDescription();
		return message;
	}


}
