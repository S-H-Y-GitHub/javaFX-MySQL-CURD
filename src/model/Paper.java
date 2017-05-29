package model;
import java.io.Serializable;
/**
 * 文献的model，保存文献的成员变量和方法
 */
public class Paper implements Serializable
{
	private int id;
	private String title;
	
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
}
