package it.bplus.model;

// Generated 31-mar-2011 10.51.22 by Hibernate Tools 3.4.0.CR1

/**
 * SessionLog generated by hbm2java
 */
public class SessionLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionLogId id;

	public SessionLog() {
	}

	public SessionLog(SessionLogId id) {
		this.id = id;
	}

	public SessionLogId getId() {
		return this.id;
	}

	public void setId(SessionLogId id) {
		this.id = id;
	}

}
