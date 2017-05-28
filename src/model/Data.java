package model;
import java.io.Serializable;
public class Data implements Serializable
{
	private Integer id;
	private String name;
	private String country;
	private String district;
	private Integer population;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	public String getDistrict()
	{
		return district;
	}
	public void setDistrict(String district)
	{
		this.district = district;
	}
	public Integer getPopulation()
	{
		return population;
	}
	public void setPopulation(Integer population)
	{
		this.population = population;
	}
}
