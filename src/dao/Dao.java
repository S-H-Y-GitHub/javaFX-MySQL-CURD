package dao;
import model.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public abstract class Dao
{
	protected Statement stmt;
	private Connection conn;
	
	public Dao() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papermanage?useSSL=false", "root", "coding");
		stmt = conn.createStatement();
	}
	abstract public List query(String sql) throws Exception;
	public int update(String sql) throws Exception
	{
		return stmt.executeUpdate(sql);
	}
	
	protected void closeDao() throws Exception
	{
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}
}
