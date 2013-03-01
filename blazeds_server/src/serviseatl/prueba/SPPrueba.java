package serviseatl.prueba;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


import serviseatl.connection.DAOException;


public class SPPrueba  implements Serializable {
    static final long serialVersionUID = 103841544507777777L;
	public List<Prueba> getSPPrueba() throws DAOException {

		List<Prueba> list = new ArrayList<Prueba>();
		int i = 0;
		int n = 5;

			
			while (i<=n) {
				i=i+1;
				list.add(new Prueba(
					    "prueba "+i
						));
			}
			
		
		return list;
	}

}
