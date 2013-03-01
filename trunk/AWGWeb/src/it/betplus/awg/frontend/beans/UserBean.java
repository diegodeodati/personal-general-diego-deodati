package it.betplus.awg.frontend.beans;

import java.util.HashMap;
import java.util.List;

import it.betplus.awg.db.dto.RoleAuthenticationDTO;
import it.betplus.awg.db.dto.UserAuthenticationDTO;
import it.betplus.web.framework.frontend.beans.BeanBase;

public class UserBean extends BeanBase {
	private static final long serialVersionUID = -5866188363959317594L;
	
	private String employeeid;
	private String firstname;
	private String lastname;
	private String nlogin;
	private String email;
	private String superadm;
	private HashMap<String, RoleBean> roleMap;
	
	public UserBean(UserAuthenticationDTO dto, boolean useReflection) {
		
		try {
			
			if(useReflection) {
			
				beanReflectionFromDto(dto);		
				
			}

			this.roleMap = new HashMap<String, RoleBean>();
			
			for(RoleAuthenticationDTO role : dto.getRoleList()) {
				
				
				this.roleMap.put(role.getRolename(), new RoleBean(role));
				
			}
			    
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


//	public UserDTOlocal createDtoObject() {
//		
//		UserDTOlocal revertedObj = new UserDTOlocal();
//		
//		revertedObj.setEmployeeId(this.employeeId);
//		revertedObj.setName(this.name);
//		revertedObj.setLanguageId(this.languageId);
//		revertedObj.setPositionName(this.positionName);
//		revertedObj.setDepartmentName(this.departmentName);
//		revertedObj.setYyear(this.year);
//		revertedObj.setMmonth(this.month);
//		revertedObj.setDday(this.day);
//		
//		return revertedObj;
//		
//	}

	public boolean isRolePresent(String roleName){
	
		String[] roleNames = roleName.split(",");
	
		for (int i=0; i < roleNames.length; i++) {
			if(roleMap.get(roleNames[i]) != null)
				return true;
		}
		
		return false;
	
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


	public HashMap<String, RoleBean> getRoleMap() {
		return roleMap;
	}


	public void setRoleMap(HashMap<String, RoleBean> roleMap) {
		this.roleMap = roleMap;
	}
	
}
