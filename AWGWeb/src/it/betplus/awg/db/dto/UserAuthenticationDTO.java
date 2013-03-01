package it.betplus.awg.db.dto;

import java.io.Serializable;
import java.util.List;

public class UserAuthenticationDTO implements Serializable {
	private static final long serialVersionUID = -8374302372363636610L;

	private String employeeid;
	private String firstname;
	private String lastname;
	private String nlogin;
	private String email;
	private String superadm;
	private List<RoleAuthenticationDTO> roleList;
	
	
	public UserAuthenticationDTO() {
		
	}
	
	public UserAuthenticationDTO(String employeeid, String firstname, String lastname, String nlogin, String email, String superadm
				, List<RoleAuthenticationDTO> roleList) {
		
		this.employeeid = employeeid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.nlogin = nlogin;
		this.email = email;
		this.superadm = superadm;
		this.roleList = roleList;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNlogin() {
		return nlogin;
	}

	public void setNlogin(String nlogin) {
		this.nlogin = nlogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSuperadm() {
		return superadm;
	}

	public void setSuperadm(String superadm) {
		this.superadm = superadm;
	}

	public List<RoleAuthenticationDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleAuthenticationDTO> roleList) {
		this.roleList = roleList;
	}

	
}
