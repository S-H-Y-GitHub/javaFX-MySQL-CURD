package model;
import java.io.Serializable;

/**
 * 用户的model，保存用户的相关信息
 */
public class User implements Serializable
{
	private String username;
	private String password;
	private int id;
	
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}
