package model;
import java.io.Serializable;
public class UserPaper implements Serializable
{
	private int id;
	private int userId;
	private int paperId;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public int getPaperId()
	{
		return paperId;
	}
	public void setPaperId(int paperId)
	{
		this.paperId = paperId;
	}
}
