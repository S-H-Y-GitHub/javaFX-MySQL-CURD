package dao;
import model.User;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends Dao
{
	public UserDao() throws Exception
	{
		super();
	}
	@Override
	public List<User> query(String sql) throws Exception
	{
		ResultSet rs = stmt.executeQuery(sql);
		List<User> result = new LinkedList<>();
		User user;
		while (rs.next())
		{
			user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setId(rs.getInt("id"));
			result.add(user);
		}
		rs.close();
		closeDao();
		return result;
	}
}