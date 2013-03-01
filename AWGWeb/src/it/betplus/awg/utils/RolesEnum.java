package it.betplus.awg.utils;

public enum RolesEnum {

	CSR	(1),
	VLT	(2),
    SLA (3);

	private int roleCode;

	private RolesEnum(int roleCode) {
		this.roleCode = roleCode;
	}

	public static RolesEnum getRoleEnum(String roleName) {

		for (RolesEnum role : values()) {
			if (role.name().equals(roleName)) {
				return role;
			}
		}
		return null;
	}

	public int roleCode() {
		return roleCode;
	}

	public static RolesEnum fromRoleCode(int roleCode) {
		for (RolesEnum role : values()) {
			if (role.roleCode == roleCode) {
				return role;
			}
		}
		return null;
	}
}
