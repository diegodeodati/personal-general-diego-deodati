package it.betplus.awg.db.dto;

import java.io.Serializable;

public class RoleAuthenticationDTO implements Serializable {
	private static final long serialVersionUID = 5495293410453557671L;
	
	private Integer roleid;
	private String rolename;
	private String roledescription;
	
	
	public RoleAuthenticationDTO() {
		
	}
	
	public RoleAuthenticationDTO(Integer roleid, String rolename, String roledescription) {
		
		this.roleid = roleid;
		this.rolename = rolename;
		this.roledescription = roledescription;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledescription() {
		return roledescription;
	}

	public void setRoledescription(String roledescription) {
		this.roledescription = roledescription;
	}

}
