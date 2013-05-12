package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import dao.DriverDAO;
import entities.Driver;

public class JPADriverDAO extends JPAAbstractDAO<Driver> implements DriverDAO {

private static final String FIND_BY_NAME="SELECT d FROM Driver d WHERE d.fullName = :fullName";

	public JPADriverDAO(Class<Driver> clazz) {
		super(clazz);
	}

	protected EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	}
	
	public List<Driver> findByName (String fullName) {
		EntityManager em = getEntityManager();
		return em.createQuery(FIND_BY_NAME, Driver.class).setParameter("fullName", fullName).getResultList();
	}
}
