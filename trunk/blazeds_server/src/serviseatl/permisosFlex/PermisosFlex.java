package serviseatl.permisosFlex;

import java.io.Serializable;

public class PermisosFlex implements Serializable {
    static final long serialVersionUID = 103844514002770123L;
    
    private String MenuName;
    private boolean Access;
    private boolean Modified;

    public PermisosFlex() {
   	
    }
    
    public PermisosFlex(String MenuName, boolean Access, boolean Modified)   
    {
		this.MenuName = MenuName;
		this.Access = Access;
		this.Modified = Modified;

    }

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setAccess(boolean access) {
		Access = access;
	}

	public boolean isAccess() {
		return Access;
	}

	public void setModified(boolean modified) {
		Modified = modified;
	}

	public boolean isModified() {
		return Modified;
	}
   
      
}
