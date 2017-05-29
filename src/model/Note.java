package model;
import java.io.Serializable;

public class Note implements Serializable
{
	private int id;
	private int author;
	private int paper;
	private String title;
	private String content;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getAuthor()
	{
		return author;
	}
	public void setAuthor(int author)
	{
		this.author = author;
	}
	public int getPaper()
	{
		return paper;
	}
	public void setPaper(int paper)
	{
		this.paper = paper;
	}
}
