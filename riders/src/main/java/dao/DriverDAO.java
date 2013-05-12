package dao;

import java.util.List;

import entities.Driver;

public interface DriverDAO {
	
	public void create(Driver driver);
	public void update(Driver driver);
	public void delete(Driver driver);
	public List<Driver> findAll();
	public Driver find(int id);
	public List<Driver> findByName(String fullName);
	public long count();
}
