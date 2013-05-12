package dao.jpa;

import javax.persistence.EntityManager;

import dao.RouteDAO;
import entities.Route;

public class JPARouteDAO extends JPAAbstractDAO<Route> implements RouteDAO {

	public JPARouteDAO(Class<Route> clazz) {
		super(clazz);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	}


}
