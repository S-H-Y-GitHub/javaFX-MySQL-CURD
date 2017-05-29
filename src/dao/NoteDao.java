package dao;
import model.Note;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class NoteDao extends Dao
{
	public NoteDao() throws Exception
	{
		super();
	}
	
	@Override
	public List<Note> query(String sql) throws Exception
	{
		ResultSet rs = stmt.executeQuery(sql);
		List<Note> result = new LinkedList<>();
		Note note;
		while (rs.next())
		{
			note = new Note();
			note.setId(rs.getInt("id"));
			note.setAuthor(rs.getInt("author"));
			note.setPaper(rs.getInt("paper"));
			note.setTitle(rs.getString("title"));
			note.setContent(rs.getString("content"));
			result.add(note);
		}
		rs.close();
		closeDao();
		return result;
	}
}
