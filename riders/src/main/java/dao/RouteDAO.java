package dao;

import java.util.List;

import entities.Route;

public interface RouteDAO {
	public void create(Route driver);
	public void update(Route driver);
	public void delete(Route driver);
	public List<Route> findAll();
	public Route find(int id);
	public long count();
}
