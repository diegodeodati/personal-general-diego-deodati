package flex.samples.crm.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flex.samples.ConnectionHelper;
import flex.samples.crm.ConcurrencyException;
import flex.samples.crm.DAOException;

public class CompanyDAO
{
	public List findCompanies(String name, String industry) throws DAOException
	{
		List list = new ArrayList();
		Connection c = null;
		try
		{
            String sql = "SELECT * FROM company";
            if (industry != null && industry != "All")
                sql += " WHERE industry = '" + industry + "'";
            if (name != null)
            {
               if (industry == null)
                   sql += " WHERE company.name LIKE '%"+name+"%'";
               else
                   sql += " AND company.name LIKE '%"+name+"%'";
            }
            sql += " ORDER BY company.name";
			c = ConnectionHelper.getConnection();

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			Company company;
			while (rs.next())
			{
				company = new Company();
				company.setCompanyId(rs.getInt("company_id"));
				company.setName(rs.getString("name"));
				company.setAddress(rs.getString("address"));
				company.setCity(rs.getString("city"));
				company.setZip(rs.getString("zip"));
				company.setState(rs.getString("state"));
				company.setIndustry(rs.getString("industry"));
				list.add(company);
			}
            rs.close();
            s.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally
		{
			ConnectionHelper.close(c);
		}
		return list;
	}

	public Company getCompany(int companyId) throws DAOException
	{
		Company company = null;
		Connection c = null;
		try
		{
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM company WHERE company_id=" + companyId);
			if (rs.next())
			{
				company = new Company();
				company.setCompanyId(rs.getInt("company_id"));
				company.setName(rs.getString("name"));
				company.setAddress(rs.getString("address"));
				company.setCity(rs.getString("city"));
				company.setZip(rs.getString("zip"));
				company.setState(rs.getString("state"));
				company.setIndustry(rs.getString("industry"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally
		{
			ConnectionHelper.close(c);
		}
		return company;
	}

	public Company create(Company company) throws DAOException
	{
		Connection c = null;
        PreparedStatement ps = null;
		try
		{
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("INSERT INTO company (name, address, city, zip, state, industry) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, company.getName());
			ps.setString(2, company.getAddress());
			ps.setString(3, company.getCity());
			ps.setString(4, company.getZip());
			ps.setString(5, company.getState());
			ps.setString(6, company.getIndustry());
			ps.execute();
            ps.close();
            ps = null;
			Statement s = c.createStatement();
			// HSQLDB Syntax to get the identity (company_id) of inserted row
			ResultSet rs = s.executeQuery("CALL IDENTITY()");
			rs.next();

            // Update the id in the returned object.  This is important as this
            // value must get returned to the client.
			company.setCompanyId(rs.getInt(1));

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally
		{
			ConnectionHelper.close(c);
		}
		return company;
	}

	public void update(Company newVersion, Company previousVersion, List changes) throws DAOException, ConcurrencyException
	{
		Connection c = null;
        PreparedStatement ps = null;
		try
		{
			c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("UPDATE company SET name=?, address=?, city=?, zip=?, state=?, industry=? WHERE company_id=? AND name=? AND address=? AND city=? AND zip=? AND state=?" );
            ps.setString(1, newVersion.getName());
            ps.setString(2, newVersion.getAddress());
            ps.setString(3, newVersion.getCity());
            ps.setString(4, newVersion.getZip());
            ps.setString(5, newVersion.getState());
            ps.setString(6, newVersion.getIndustry());
            ps.setInt(7, newVersion.getCompanyId());
			ps.setString(8, previousVersion.getName());
			ps.setString(9, previousVersion.getAddress());
			ps.setString(10, previousVersion.getCity());
			ps.setString(11, previousVersion.getZip());
			ps.setString(12, previousVersion.getState());
			if (ps.executeUpdate() == 0)
			{
				throw new ConcurrencyException("Item not found");
			}
            ps.close();
            ps = null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally
		{
			ConnectionHelper.close(c);
		}
	}

	public void delete(Company company) throws DAOException, ConcurrencyException
	{
		Connection c = null;
        PreparedStatement ps = null;
		try
		{
			c = ConnectionHelper.getConnection();

			ps = c.prepareStatement("DELETE FROM company WHERE company_id=? AND name=? AND address=? AND city=? AND zip=? AND state=?");
			ps.setInt(1, company.getCompanyId());
			ps.setString(2, company.getName());
			ps.setString(3, company.getAddress());
			ps.setString(4, company.getCity());
			ps.setString(5, company.getZip());
			ps.setString(6, company.getState());
			if (ps.executeUpdate() == 0)
			{
				throw new ConcurrencyException("Item not found");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally
		{
			ConnectionHelper.close(c);
		}
	}
}