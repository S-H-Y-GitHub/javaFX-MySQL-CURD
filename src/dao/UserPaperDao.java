package dao;
import model.UserPaper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
public class UserPaperDao extends Dao
{
	public UserPaperDao() throws Exception
	{
		super();
	}
	@Override
	public List query(String sql) throws Exception
	{
		ResultSet rs = stmt.executeQuery(sql);
		List<UserPaper> result = new LinkedList<>();
		UserPaper userPaper;
		while (rs.next())
		{
			userPaper = new UserPaper();
			userPaper.setId(rs.getInt("id"));
			userPaper.setPaperId(rs.getInt("paper_id"));
			userPaper.setUserId(rs.getInt("user_id"));
			result.add(userPaper);
		}
		rs.close();
		closeDao();
		return result;
	}
}
