package dao;

import model.Data;
import model.Log;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
public class LogDao extends Dao
{
	
	public LogDao() throws Exception
	{
		super();
	}
	
	@Override
	public List<Log> query(String sql) throws Exception
	{
		ResultSet rs = stmt.executeQuery(sql);
		List<Log> result = new LinkedList<>();
		Log log;
		while (rs.next())
		{
			log = new Log();
			log.setId(rs.getInt("id"));
			log.setOperatorid(rs.getInt("operatorid"));
			log.setTarget(rs.getInt("target"));
			log.setTargetid(rs.getInt("targetid"));
			log.setTime(rs.getTimestamp("time"));
			log.setType(rs.getInt("type"));
			result.add(log);
		}
		rs.close();
		closeDao();
		return result;
	}
}
