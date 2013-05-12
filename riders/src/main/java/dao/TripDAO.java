package dao;

import java.util.List;

import entities.Trip;


public interface TripDAO {
	public void create(Trip driver);
	public void update(Trip driver);
	public void delete(Trip driver);
	public List<Trip> findAll();
	public Trip find(int id);
	public long count();
}
