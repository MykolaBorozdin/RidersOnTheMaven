package dao.jpa;

import javax.persistence.EntityManager;

import dao.TripDAO;
import entities.Trip;

public class JPATripDAO extends JPAAbstractDAO<Trip> implements TripDAO {

	public JPATripDAO(Class<Trip> clazz) {
		super(clazz);
	}

	@Override
	protected EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	}


	
}
