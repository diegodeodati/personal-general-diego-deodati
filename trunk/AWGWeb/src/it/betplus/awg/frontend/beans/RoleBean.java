package it.betplus.awg.frontend.beans;

import it.betplus.awg.db.dto.RoleAuthenticationDTO;
import it.betplus.awg.db.dto.UserAuthenticationDTO;
import it.betplus.web.framework.frontend.beans.BeanBase;

public class RoleBean extends BeanBase {
	private static final long serialVersionUID = 2127895323839241061L;
	
	private Integer roleid;
	private String rolename;
	private String roledescription;
	
	public RoleBean() {
		
	}
	
	public RoleBean(RoleAuthenticationDTO dto) {
		
		try {
			
			beanReflectionFromDto(dto);		
			
			    
		} catch(Exception e) {
			e.printStackTrace();
		}
		
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
