package dao;
import model.Paper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class PaperDao extends Dao
{
	public PaperDao() throws Exception
	{
		super();
	}
	
	@Override
	public List<Paper> query(String sql) throws Exception
	{
		ResultSet rs = stmt.executeQuery(sql);
		List<Paper> result = new LinkedList<>();
		Paper paper;
		while (rs.next())
		{
			paper = new Paper();
			paper.setId(rs.getInt("id"));
			paper.setTitle(rs.getString("title"));
			result.add(paper);
		}
		rs.close();
		closeDao();
		return result;
	}
}
