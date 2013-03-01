package it.betplus.awg.db.facade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.betplus.awg.db.dto.CompensiInspireDTO;
import it.betplus.awg.db.dto.LocationDTO;
import it.betplus.awg.db.dto.LocationDataDTO;
import it.betplus.awg.db.dto.ManufactureDateDTO;
import it.betplus.awg.db.dto.RoleAuthenticationDTO;
import it.betplus.awg.db.dto.UserAuthenticationDTO;
import it.betplus.awg.db.dto.VltDTO;
import it.betplus.database.connector.facade.DBFacade;
import it.betplus.web.framework.exceptions.DataLayerException;

public class AwgFacade extends DBFacade{
	
	public AwgFacade(){
		super();
	}
	
	public List<VltDTO> getVLT(String dataDa, String dataA, Long idManufacture) throws DataLayerException {

		String query = "select distinct m.ManufacturerName as manufacturername, r.aamsvltid as aamsvltid, " +
						"v.cabinet as cabinet, " +
						"case when (v.aamsmanufacturerid) = '1711000045' then 11.7 else case when (cabinet) = 'SLANT TOP' then 9.5 else 9.125 end end as feePerc, " +
						"r.AAMSLocationId as AAMSLocationId, " +
						"l.AAMSLocationName as AAMSLocationName, " +
						"l.AAMSCity as AAMSCity " +
						"from Slot.ReMetersDayV2 r " +
						"INNER JOIN slot.vlt v on v.aamsvltid = r.aamsvltid " +
						"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
						"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId " +
						"where fecham >= ? and fecham <= ? ";
		if (idManufacture != 0)
			query += " and v.aamsmanufacturerid = ? ";
		query += "GROUP BY r.AAMSLocationId, r.aamsvltid, cabinet, l.AAMSLocationName, l.AAMSCity, v.aamsmanufacturerid, m.ManufacturerName";

		List<VltDTO> vltList = new ArrayList<VltDTO>();
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, dataDa);  
	        preparedStatement.setObject(2, dataA); 
	        if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	        
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (vltList == null)
					vltList = new ArrayList<VltDTO>();
				
				VltDTO vltDto = new VltDTO(resultSet.getString("manufacturername"), resultSet.getString("aamsvltid"), resultSet.getString("cabinet"), 
							resultSet.getDouble("feePerc"), resultSet.getString("AAMSLocationId"), 
							resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSCity")); 
				
				vltList.add(vltDto);
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return vltList;
		
	}
	
	public List<CompensiInspireDTO> getCompensi(String dataDa, String dataA, Long idManufacture) throws DataLayerException {

		String query = "select distinct " +
						"r.aamsvltid as AAMSVLTID, " +
						"m.ManufacturerName as manufacturername, " +
						"cabinet as Cabinet, " +
						"round(sum(r.meterin), 2) as Bet, " +
						"sum(r.meterout) as Win, " +
						"sum(r.JackpotContribution) as JACKPOTCONTRIBUTION, " +
						"sum(r.MeterJackpot) as JackpotWins, " +
						"round((sum(r.meterin)-sum(r.meterout)-sum(r.JackpotContribution)), 2) as NETWIN, " +
						"round((sum(r.meterin)-sum(r.meterout)-sum(r.JackpotContribution)) - (sum(r.MeterIn) * 0.048), 2) as TotalHouseWin, " +
						"case when (v.aamsmanufacturerid) = '1711000045' then 11.7 else case when (cabinet) = 'SLANT TOP' then 9.5 else 9.125 end end as FEEperc, " +
						"round(((sum(r.meterin)-sum(r.meterout)-sum(r.JackpotContribution)) - (sum(r.MeterIn) * 0.048)) * ((case when (v.aamsmanufacturerid) = '1711000045' then 11.7 else case when (cabinet) = 'SLANT TOP' then 9.5 else 9.125 end end)/100),2)  as RicavoInspired " +
						"from Slot.ReMetersDayV2 r inner join slot.vlt v on v.aamsvltid = r.aamsvltid " +
						"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
						"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId " +
						"where fecham >= ? and fecham <= ? ";
		if (idManufacture != 0)
			query += " and v.aamsmanufacturerid = ? ";
		query += " group by  r.aamsvltid,cabinet, m.ManufacturerName, v.AAMSManufacturerId";
		
		List<CompensiInspireDTO> compensiinspireList = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, dataDa);  
	        preparedStatement.setObject(2, dataA); 
	        if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	        
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (compensiinspireList == null)
					compensiinspireList = new ArrayList<CompensiInspireDTO>();
				
				CompensiInspireDTO compensiinspireDTO = new CompensiInspireDTO(resultSet.getString("AAMSVLTID"), resultSet.getString("manufacturername"), resultSet.getString("Cabinet"), 
							resultSet.getDouble("Bet"), resultSet.getDouble("Win"), resultSet.getDouble("JACKPOTCONTRIBUTION"), resultSet.getDouble("JackpotWins"), 
							resultSet.getDouble("NETWIN"), resultSet.getDouble("TotalHouseWin"), resultSet.getDouble("FEEperc"), 
							resultSet.getDouble("RicavoInspired")); 
				
				compensiinspireList.add(compensiinspireDTO);
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return compensiinspireList;
		
	}
	
	public UserAuthenticationDTO getUserAuthentication(String nLogin, String nPassword) throws DataLayerException {

		String query = "select r.RoleId RoleID, r.Name RoleName, r.Description RoleDescription, " +
					"e.EmployeeID, e.FirstName, e.LastName, e.NLogin, e.Email, e.SuperAdm from Employee.Employee As e " +
					"LEFT JOIN Employee.EmployeeRoles AS re ON re.EmployeeID=e.EmployeeID " +
					"LEFT JOIN Employee.Roles AS r ON  r.RoleID=re.RoleID " +
					"where e.NLogin = ? AND e.NPassword = ? " +
					"AND StateAWGWeb = 1 ";
				
		UserAuthenticationDTO userDTO = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, nLogin);  
	        preparedStatement.setObject(2, nPassword); 
	           
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			String employeeid = "";
			String firstname = ""; 
			String lastname = "";
			String nlogin = "";
			String email = "";
			String superadm = "";
			boolean isStart = true;
			List<RoleAuthenticationDTO> roleList = new ArrayList<RoleAuthenticationDTO>();
			
			while (resultSet.next()) {
				if (isStart)
				{
					employeeid = resultSet.getString("EmployeeID");
					firstname = resultSet.getString("FirstName"); 
					lastname = resultSet.getString("LastName");
					nlogin = resultSet.getString("NLogin");
					email = resultSet.getString("Email");
					superadm = resultSet.getString("SuperAdm");
					isStart = false;
				}
				
				RoleAuthenticationDTO roleDTO = new RoleAuthenticationDTO(resultSet.getInt("RoleID"), resultSet.getString("RoleName"), resultSet.getString("RoleDescription"));
				roleList.add(roleDTO);
			}
			
			if (!isStart)
				userDTO = new UserAuthenticationDTO(employeeid, firstname, lastname, nlogin, email,superadm, roleList); 
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
		}
		
		return userDTO;
		
	}
	

	
	public String getTotali(String dataDa, String dataA, boolean isDistante,Long idManufacture) throws DataLayerException {
		String query = "";
		String out = "";
		
		DecimalFormat dec = (DecimalFormat) NumberFormat.getInstance(new Locale("it","IT"));
		dec.applyLocalizedPattern("###.###.##0,00");
		
		query += "select sum(r.meterin) as BET, " +
						"sum(r.meterout) as Win " +
						"FROM Slot.ReMetersDayV2 r inner join slot.vlt v on v.aamsvltid = r.aamsvltid " +
						"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
						"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId ";
						
		

		query += "where fecham >= ? and fecham <= ? ";
		
		if (isDistante)
		{
			query += "and r.AAMSLocationId in (SELECT AAMSLocationId FROM dbo.AAMSLocationDistante) ";
		}
		
		if (idManufacture != 0)
			query += " and m.aamsmanufacturerid = ? ";
		
						
	
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
			if (dataDa != "")
			{
				preparedStatement.setObject(1, dataDa);  
			    preparedStatement.setObject(2, dataA);
			}
			if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	           
			ResultSet resultSet = executeQueryStatement(preparedStatement);
			
			
			while (resultSet.next()) {
			
				out += "BET: "+dec.format(resultSet.getDouble(1))+" WIN: "+dec.format(resultSet.getDouble(2)); 
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return out;
		
	}
	
	public List<LocationDataDTO> getLocationData(String dataDa, String dataA, boolean groupByDate, Long idManufacture, boolean isDistante) throws DataLayerException {
		String query = "";

		if (groupByDate)
			query = "select fecham, ";
		else
		{
			query = "select l.AAMSLocationName AAMSLocationName, " +
					"r.AAMSLocationId AAMSLocationId, " +
					"l.AAMSAddress AAMSAddress, " +
					"l.AAMSProvince AAMSProvince, " +
					"l.AAMSCity AAMSCity,LocationStatus,VltStatus, ";
			//if (!isDistante)
			//	query += "m.ManufacturerName ManufacturerName, ";
		}
		
		query += "sum(r.meterin) as BET, " +
						"sum(r.meterout) as Win, " +
						"sum(r.MeterGames) as GamesPlayed, " +
						"sum(r.MeterBill)+sum(r.MeterVoucherIn) as TotalIn, " +
						"sum(r.VoucherOut)+sum(MeterHandPay)+sum(r.MeterJackpot) as TotalOut, " +
						"sum(r.MeterVoucherIn) as TicketIn, " +
						"sum(r.VoucherOut) as TicketOut, " +
						"0 as CoinIn, " +
						"sum(r.MeterBill) as BillIn, " +
						"0 as CardIn, " +
						"0 as TotalPrepaidIn, " +
						"sum(r.MeterJackpot) as JackpotWins, " +
						"sum(r.JackpotContribution) as JackpotContribution ";
						
					 if (!groupByDate)
						 query += ",LocationStatus,VltStatus ";
		
		
		                 query += "FROM Slot.ReMetersDayV2 r inner join slot.vlt v on v.aamsvltid = r.aamsvltid " +
						"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
						"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId ";
						
		if (isDistante)
		{
			query += "where r.AAMSLocationId in (SELECT AAMSLocationId FROM dbo.AAMSLocationDistante) ";
			if (dataDa != "")
				query += "AND fecham >= ? and fecham <= ? ";
		}
		else
			query += "where fecham >= ? and fecham <= ? ";
		
		if (idManufacture != 0)
			query += " and m.aamsmanufacturerid = ? ";
		
		if (groupByDate)
			query += "GROUP BY fecham " +
					"ORDER BY fecham";
		else
		{
			query += "GROUP BY r.AAMSLocationId, l.AAMSLocationName, l.AAMSAddress, l.AAMSProvince, l.AAMSCity,LocationStatus,VltStatus ";
			//if (!isDistante)
			//	query += ", m.ManufacturerName ";
			query += "ORDER BY l.AAMSLocationName, r.AAMSLocationId";
		}
						
		List<LocationDataDTO> locationDataDTO = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
			if (dataDa != "")
			{
				preparedStatement.setObject(1, dataDa);  
			    preparedStatement.setObject(2, dataA);
			}
			if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	           
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (locationDataDTO == null)
					locationDataDTO = new ArrayList<LocationDataDTO>();
				
				LocationDataDTO locationdataDTO;
				if (groupByDate)
					locationdataDTO = new LocationDataDTO(resultSet.getDate("fecham"),
							resultSet.getDouble("Bet"), resultSet.getDouble("Win"), resultSet.getDouble("GamesPlayed"), resultSet.getDouble("TotalIn"), 
							resultSet.getDouble("TotalOut"), resultSet.getDouble("TicketIn"), resultSet.getDouble("TicketOut"), resultSet.getDouble("CoinIn"),
							resultSet.getDouble("BillIn"), resultSet.getDouble("CardIn"), resultSet.getDouble("TotalPrepaidIn"), resultSet.getDouble("JackpotWins"),
							resultSet.getDouble("JACKPOTCONTRIBUTION"), "", "", "", "");
				else{
					locationdataDTO = new LocationDataDTO(resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSLocationId"), 
						resultSet.getDouble("Bet"), resultSet.getDouble("Win"), resultSet.getDouble("GamesPlayed"), resultSet.getDouble("TotalIn"), 
						resultSet.getDouble("TotalOut"), resultSet.getDouble("TicketIn"), resultSet.getDouble("TicketOut"), resultSet.getDouble("CoinIn"),
						resultSet.getDouble("BillIn"), resultSet.getDouble("CardIn"), resultSet.getDouble("TotalPrepaidIn"), resultSet.getDouble("JackpotWins"),
						resultSet.getDouble("JACKPOTCONTRIBUTION"), resultSet.getString("AAMSAddress"), resultSet.getString("AAMSProvince"),
						resultSet.getString("AAMSCity"), ""); 
				
				
					
					locationdataDTO.setLocationStatus(resultSet.getString("LocationStatus"));
					
					locationdataDTO.setVltStatus(resultSet.getString("VltStatus"));
				}
				
				locationDataDTO.add(locationdataDTO);
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return locationDataDTO;
		
	}
		
	public List<VltDTO> getVltMilionarie(String dataDa, String dataA, Long idManufacture) throws DataLayerException {
 		String query = "select r.fecham, r.aamsvltid as AAMSVLTID, m.ManufacturerName ManufacturerName, " +
					"l.AAMSLocationName AAMSLocationName, r.AAMSLocationId AAMSLocationId, " +
					"l.AAMSProvince AAMSProvince, l.AAMSCity AAMSCity, " +
					"sum(r.meterin) as BET, sum(r.meterout) as Win, sum(r.MeterGames) as GamesPlayed, " + 
					"sum(r.MeterBill)+sum(r.MeterVoucherIn) as TotalIn, sum(r.VoucherOut)+sum(MeterHandPay)+sum(r.MeterJackpot) as TotalOut, " + 
					"sum(r.MeterVoucherIn) as TicketIn, sum(r.VoucherOut) as TicketOut, 0 as CoinIn, sum(r.MeterBill) as BillIn, " + 
					"0 as CardIn, 0 as TotalPrepaidIn, sum(r.MeterJackpot) as JackpotWins, sum(r.JackpotContribution) as JackpotContribution " + 
					"FROM Slot.ReMetersDayV2 r " +
					"INNER JOIN slot.vlt v on v.aamsvltid = r.aamsvltid " + 
					"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " + 
					"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId " + 
					"where fecham >= ? and fecham <= ? ";
		
		if (idManufacture != 0)
			query += " and m.aamsmanufacturerid = ? ";
		
		query += "GROUP BY r.fecham, r.aamsvltid, r.AAMSLocationId, l.AAMSLocationName, m.ManufacturerName, l.AAMSProvince, l.AAMSCity " + 
					"HAVING sum(r.meterin) > 15000 " +
					"ORDER BY r.fecham, r.AAMSLocationId";
		
		List<VltDTO> vltDataDTO = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
			preparedStatement.setObject(1, dataDa);  
		    preparedStatement.setObject(2, dataA);
			if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	           
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (vltDataDTO == null)
					vltDataDTO = new ArrayList<VltDTO>();
				
				VltDTO vltDTO;
				vltDTO = new VltDTO(resultSet.getString("ManufacturerName"), resultSet.getString("AAMSVLTID"), resultSet.getString("AAMSLocationId"), resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSCity"), 
						resultSet.getString("AAMSProvince"), resultSet.getDouble("JackpotWins"), resultSet.getDate("fecham"));
					
				vltDTO.setBet(resultSet.getDouble("BET"));
				vltDTO.setWin(resultSet.getDouble("Win"));
				vltDTO.setGamesplayed(resultSet.getDouble("GamesPlayed"));
				vltDTO.setTotalin(resultSet.getDouble("TotalIn"));
				vltDTO.setTotalout(resultSet.getDouble("TotalOut"));	
				vltDataDTO.add(vltDTO);
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return vltDataDTO;
		
	}
	
	public List<LocationDataDTO> getVltByLocation(String dataDa, String dataA, Long idManufacture, boolean searchMilionarie) throws DataLayerException {

		String query = "select r.aamsvltid as AAMSVLTID, " + 
				"m.ManufacturerName ManufacturerName, " +
				"l.AAMSLocationName AAMSLocationName, " +
				"r.AAMSLocationId AAMSLocationId, " +
				"sum(r.meterin) as BET, " +
				"sum(r.meterout) as Win, " +
				"sum(r.MeterGames) as GamesPlayed, " +
				"sum(r.MeterBill)+sum(r.MeterVoucherIn) as TotalIn, " +
				"sum(r.VoucherOut)+sum(MeterHandPay)+sum(r.MeterJackpot) as TotalOut, " +
				"sum(r.MeterVoucherIn) as TicketIn, " +
				"sum(r.VoucherOut) as TicketOut, " +
				"0 as CoinIn, " +
				"sum(r.MeterBill) as BillIn, " +
				"0 as CardIn, " +
				"0 as TotalPrepaidIn, " +
				"sum(r.MeterJackpot) as JackpotWins, " +
				"sum(r.JackpotContribution) as JackpotContribution " +
				"FROM Slot.ReMetersDayV2 r " +
				"INNER JOIN slot.vlt v on v.aamsvltid = r.aamsvltid " +
				"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
				"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId " +
				"where fecham >= ? and fecham <= ? ";
		
		if (idManufacture != 0)
			query += " and v.aamsmanufacturerid = ? ";
		
		query += "GROUP BY r.aamsvltid, r.AAMSLocationId, l.AAMSLocationName, m.ManufacturerName ";
		
		if (searchMilionarie)
			query += " HAVING sum(r.meterin) > 15000 ";
				
		query += "ORDER BY r.AAMSLocationId";
		
		List<LocationDataDTO> locationDataDTOList = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, dataDa);  
	        preparedStatement.setObject(2, dataA); 
	        
	        if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	        
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			ArrayList<VltDTO> vltList = new ArrayList<VltDTO>(); 
			String currentIdLocation = "";
			String currentLocationName = "";
			
			while (resultSet.next()) {
				if (locationDataDTOList == null)
					locationDataDTOList = new ArrayList<LocationDataDTO>();
				
				if (currentIdLocation == "")
					currentIdLocation = resultSet.getString("AAMSLocationId");
				else if (!resultSet.getString("AAMSLocationId").equals(currentIdLocation))
				{
					locationDataDTOList.add(new LocationDataDTO(currentLocationName, currentIdLocation, vltList));
					vltList = new ArrayList<VltDTO>();
					currentIdLocation = resultSet.getString("AAMSLocationId");
					currentLocationName = resultSet.getString("AAMSLocationName");
				}
					
				VltDTO vltDTO = new VltDTO(resultSet.getString("ManufacturerName"), resultSet.getString("AAMSVLTID"), 
						resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSLocationId"), 
						resultSet.getDouble("Bet"), resultSet.getDouble("Win"), resultSet.getDouble("GamesPlayed"), resultSet.getDouble("TotalIn"), 
						resultSet.getDouble("TotalOut"), resultSet.getDouble("TicketIn"), resultSet.getDouble("TicketOut"), resultSet.getDouble("CoinIn"),
						resultSet.getDouble("BillIn"), resultSet.getDouble("CardIn"), resultSet.getDouble("TotalPrepaidIn"), resultSet.getDouble("JackpotWins"),
						resultSet.getDouble("JACKPOTCONTRIBUTION")); 
				
				vltList.add(vltDTO);
			}
			
			if (!vltList.isEmpty())
				locationDataDTOList.add(new LocationDataDTO(vltList.get(0).getAamslocationname(), vltList.get(0).getAamslocationid(), vltList));
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return locationDataDTOList;
		
	}
	
	public List<ManufactureDateDTO> getDateByManufacture() throws DataLayerException {

		String query = "(select m.AAMSManufacturerId, max(dateimported) maxDateImported from Slot.ReMetersDayV2 r " +
				"inner join slot.vlt v on v.aamsvltid = r.aamsvltid " +
				"inner join Slot.AAMSManufacturer m ON m.AAMSManufacturerId = v.AAMSManufacturerId " +
				"where v.aamsmanufacturerid = 1711000065 " +
				"AND not dateimported is null " +
				"GROUP BY m.AAMSManufacturerId) " +
				"union " +
				"(select m.AAMSManufacturerId, max(dateimported) maxDateImported from Slot.ReMetersDayV2 r " +
				"inner join slot.vlt v on v.aamsvltid = r.aamsvltid " +
				"inner join Slot.AAMSManufacturer m ON m.AAMSManufacturerId = v.AAMSManufacturerId " +
				"where v.aamsmanufacturerid = 1711000045 " +
				"AND not dateimported is null " +
				"GROUP BY m.AAMSManufacturerId)";
		
		List<ManufactureDateDTO> ManufactureDateList = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	           
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (ManufactureDateList == null)
					ManufactureDateList = new ArrayList<ManufactureDateDTO>();
				
				ManufactureDateList.add(new ManufactureDateDTO(resultSet.getString("AAMSManufacturerId"), resultSet.getDate("maxDateImported")));
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return ManufactureDateList;
		
	}

	public List<LocationDataDTO> getLocationDistante(String dataDa, String dataA, Long idManufacture) throws DataLayerException {

		String query = "select l.AAMSLocationName AAMSLocationName, " +
						"r.AAMSLocationId AAMSLocationId, " +
						"l.AAMSAddress AAMSAddress, " +
						"l.AAMSProvince AAMSProvince, " +
						"l.AAMSCity AAMSCity, " +
						"m.ManufacturerName ManufacturerName, " +
						"sum(r.meterin) as BET, " +
						"sum(r.meterout) as Win, " +
						"sum(r.MeterGames) as GamesPlayed, " +
						"sum(r.MeterBill)+sum(r.MeterVoucherIn) as TotalIn, " +
						"sum(r.VoucherOut)+sum(MeterHandPay)+sum(r.MeterJackpot) as TotalOut, " +
						"sum(r.MeterVoucherIn) as TicketIn, " +
						"sum(r.VoucherOut) as TicketOut, " +
						"0 as CoinIn, " +
						"sum(r.MeterBill) as BillIn, " +
						"0 as CardIn, " +
						"0 as TotalPrepaidIn, " +
						"sum(r.MeterJackpot) as JackpotWins, " +
						"sum(r.JackpotContribution) as JackpotContribution " +
						"FROM Slot.ReMetersDayV2 r inner join slot.vlt v on v.aamsvltid = r.aamsvltid " +
						"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
						"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId " +
						"where fecham >= ? and fecham <= ? ";
		if (idManufacture != 0)
			query += " and aamsmanufacturerid = ? ";
		
		query += "GROUP BY r.AAMSLocationId, l.AAMSLocationName, l.AAMSAddress , l.AAMSProvince, l.AAMSCity, m.ManufacturerName " +
				"ORDER BY r.AAMSLocationId";
						
		List<LocationDataDTO> locationDataDTO = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, dataDa);  
	        preparedStatement.setObject(2, dataA); 
	        if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	           
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (locationDataDTO == null)
					locationDataDTO = new ArrayList<LocationDataDTO>();
				
				LocationDataDTO locationdataDTO;
				
				locationdataDTO = new LocationDataDTO(resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSLocationId"), 
					resultSet.getDouble("Bet"), resultSet.getDouble("Win"), resultSet.getDouble("GamesPlayed"), resultSet.getDouble("TotalIn"), 
					resultSet.getDouble("TotalOut"), resultSet.getDouble("TicketIn"), resultSet.getDouble("TicketOut"), resultSet.getDouble("CoinIn"),
					resultSet.getDouble("BillIn"), resultSet.getDouble("CardIn"), resultSet.getDouble("TotalPrepaidIn"), resultSet.getDouble("JackpotWins"),
					resultSet.getDouble("JACKPOTCONTRIBUTION"), resultSet.getString("AAMSAddress"), resultSet.getString("AAMSProvince"),
					resultSet.getString("AAMSCity"), resultSet.getString("ManufacturerName")); 
				
				locationDataDTO.add(locationdataDTO);
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return locationDataDTO;
		
	}

	public List<LocationDTO> getLocation(boolean isDistante) throws DataLayerException {
		String query = "select AAMSLocationName, " + 
				"AAMSLocationId, " +
				"AAMSAddress, " +
				"AAMSProvince, " +
				"AAMSCity " +
				"FROM aamslocation ";
		if (isDistante)
			query += "WHERE AAMSLocationId NOT IN (SELECT AAMSLocationId FROM dbo.AAMSLocationDistante)"; 
				
		List<LocationDTO> LocationDTOList = null;
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			
			while (resultSet.next()) {
				if (LocationDTOList == null)
					LocationDTOList = new ArrayList<LocationDTO>();
				
				LocationDTO LocationDTO = new LocationDTO(resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSLocationId") 
						, resultSet.getString("AAMSAddress"), resultSet.getString("AAMSProvince"),
						resultSet.getString("AAMSCity")); 
				
				LocationDTOList.add(LocationDTO);
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return LocationDTOList;
		
	}

	public void DelDistanteLocation(String IDLocation) throws DataLayerException 
	{
		String query = "DELETE FROM AAMSLocationDistante WHERE AAMSLocationId = ? ";
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, IDLocation);  

	        preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
	}
	
	public void AddDistanteLocation(String IDLocation) throws DataLayerException 
	{
		String query = "INSERT INTO AAMSLocationDistante (AAMSLocationId) VALUES ( ? )";
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query); 
	        preparedStatement.setObject(1, IDLocation);  

	        preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
	}

	public List<VltDTO> getVltJackpotWin(String dataDa, String dataA, Long idManufacture) throws DataLayerException {

		String query = "select distinct j.PaidTimestamp as datafecham, " +
						"m.ManufacturerName as manufacturername " +
						", r.aamsvltid as aamsvltid, " +
						"r.AAMSLocationId as AAMSLocationId, " +
						"l.AAMSLocationName as AAMSLocationName, " +
						"l.AAMSProvince as AAMSProvince, " +
						"l.AAMSCity as AAMSCity, " +
						"round(j.Paid/100.0, 2) JackpotWins, " +
						"j.JackpotType,"+
						"j.JackpotDesc,"+
						"j.JackpotWinId,"+
						"j.GsJackpotId "+
						"from Slot.ReMetersDayV2 r " +
						"INNER JOIN slot.vlt v on v.aamsvltid = r.aamsvltid " +
						"INNER JOIN aamslocation l ON l.AAMSLocationId = r.AAMSLocationId " +
						"INNER JOIN Slot.AAMSManufacturer m ON m.aamsmanufacturerid = v.AAMSManufacturerId " +
						"INNER JOIN Slot.JackpotWin j " +
						"ON (convert(varchar(20), fecham, 110) = convert(varchar(20), j.PaidTimestamp, 110) OR " +
						"convert(varchar(20), fecham, 110) = convert(varchar(20), DATEADD(day, -1, j.PaidTimestamp), 110)) " +
						"and r.aamslocationid = j.aamslocationid and r.AAMSvltID = j.aamsvltid "+
						"where convert(varchar(20), j.PaidTimestamp, 110) >= ? and convert(varchar(20), j.PaidTimestamp, 110) <= ? AND r.MeterJackpot > 0 ";
		if (idManufacture != 0)
			query += " and v.aamsmanufacturerid = ? ";
		query += "GROUP BY fecham, r.AAMSLocationId, r.aamsvltid, l.AAMSLocationName, l.AAMSCity, v.aamsmanufacturerid, " +
				"m.ManufacturerName, r.MeterJackpot, l.AAMSProvince,j.JackpotType,j.JackpotDesc,j.JackpotWinId,j.GsJackpotId, j.PaidTimestamp, j.Paid";

		List<VltDTO> vltList = new ArrayList<VltDTO>();
		
		try {
			
			PreparedStatement preparedStatement = getCm().getConnection().prepareStatement(query);
			preparedStatement.setObject(1, dataDa.substring(4, 6)+'-'+dataDa.substring(6, 8)+'-'+dataDa.substring(0, 4));  
	        preparedStatement.setObject(2, dataA.substring(4, 6)+'-'+dataA.substring(6, 8)+'-'+dataA.substring(0, 4));
	        if (idManufacture != 0)
	        	preparedStatement.setObject(3, idManufacture);
	        
			ResultSet resultSet = executeQueryStatement(preparedStatement);

			while (resultSet.next()) {
				if (vltList == null)
					vltList = new ArrayList<VltDTO>();
				
				VltDTO vltDto = new VltDTO(resultSet.getString("manufacturername"), resultSet.getString("aamsvltid"), resultSet.getString("AAMSLocationId"), 
							resultSet.getString("AAMSLocationName"), resultSet.getString("AAMSCity"), resultSet.getString("AAMSProvince"),
							resultSet.getDouble("JackpotWins"), resultSet.getDate("datafecham")); 
				
				vltDto.setJackpotInfo(resultSet.getString("JackpotType")+": "+resultSet.getString("JackpotDesc"));
				vltDto.setJackpotId(resultSet.getLong("JackpotWinId"));
				vltDto.setGsJackpotId(resultSet.getString("GsJackpotId"));
				
				
				vltList.add(vltDto);
				
			}
			
		} catch (Exception e) {
			
			throw new DataLayerException(e.getMessage());
			
		} finally {
		
			try {	
				if(cm.getConnection() != null)
					cm.closeConnection();
			} catch (SQLException e) {
				throw new DataLayerException(e.getMessage());
			}
			
			
		}
		
		return vltList;
		
	}
}
