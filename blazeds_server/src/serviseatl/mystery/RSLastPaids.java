package serviseatl.mystery;

import java.util.List;
import java.io.Serializable;
import flex.messaging.io.amf.client.*;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;

import serviseatl.connection.DAOException;


public class RSLastPaids implements Serializable {
    static final long serialVersionUID = 103811334006047232L;
	public List<LastPaids> getRSLastPaids (byte LocationID, int CodCasino, byte op) throws DAOException, ServerStatusException {
		AMFConnection amfConnection = new AMFConnection();
		String url = "";

		if (LocationID == 0 && CodCasino == 3){
			url = "http://20.80.1.9:8400/atlweb/messagebroker/amf";
			//en caso de curacao
			//url = "http://20.80.2.8:8400/atlweb/messagebroker/amf";			
		}
		else if (LocationID == 0 && CodCasino == 6){
			//paradise
			url = "http://20.80.1.206:8400/atlweb/messagebroker/amf";			
		}			
		else if (LocationID ==0 && CodCasino == 1) {
			//san geronimo
			url = "http://20.80.2.9:8400/atlweb/messagebroker/amf";				
		}
		else if (LocationID ==0 && CodCasino == 7) {
			// santo domingo
			url = "http://20.80.2.8:8400/atlweb/messagebroker/amf";				
		}
		else if (LocationID ==0 && CodCasino == 5) {
			// beach plaza
			url = "http://20.80.1.142:8400/atlweb/messagebroker/amf";				
		}
		else if (LocationID ==0 && CodCasino == 2) {
			// Paradise Santo domingo
			url = "http://20.80.1.10:8400/atlweb/messagebroker/amf";				
		}
		
		List<LastPaids> list =null;
		try
		{
			amfConnection.connect(url);
			Object result = amfConnection.call("SPLastPaids.getSPLastPaids",LocationID, CodCasino, op);
			list = extracted(result);
		}
		catch (ClientStatusException cse)
		{
			System.out.println(cse);
		}
		return list;
	}
	private List<LastPaids> extracted(Object result) {
		return (List<LastPaids>)result;
	}
}
