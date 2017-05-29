package dao;
import model.Paper;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
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
			paper.setPublishDate(rs.getDate("publishDate"));
			Collection<String> author = new LinkedList<>();
			String authors = rs.getString("author");
			Collections.addAll(author, authors.split(";"));
			paper.setAuthors(author);
			paper.setAbstct(rs.getString("abstct"));
			paper.setFileURI(rs.getString("fileURI"));
			Collection<String> keyword = new LinkedList<>();
			String keywords = rs.getString("keyword");
			Collections.addAll(keyword, keywords.split(";"));
			paper.setKeywords(keyword);
			result.add(paper);
		}
		rs.close();
		closeDao();
		return result;
	}
}
