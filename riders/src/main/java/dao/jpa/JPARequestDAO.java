package dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import dao.SalaryRequestDAO;

import entities.Driver;
import entities.SalaryRequest;
import entities.RequestStatus;

public class JPARequestDAO extends JPAAbstractDAO<SalaryRequest> implements
		SalaryRequestDAO {

	private static final String FIND_BY_STATUS = "SELECT d FROM SalaryRequest d WHERE d.requestStatus = :status";
	private static final String COUNT_BY_STATUS = "SELECT count(d.idSalaryRequest) FROM SalaryRequest d WHERE d.requestStatus = :status";
	private static final String GET_TRIP_ID_LIST_BY_DRIVER = "SELECT t.idTrip FROM Trip t JOIN t.drivers d WHERE d.idDriver = :driv";
	private static final String GET_DIRTY_SALARY = "SELECT SUM(r.basicPayment) FROM Trip t JOIN t.idRoute r WHERE t.idTrip IN :idList AND t.startDate > :start AND t.endDate < :end";
	
	public JPARequestDAO(Class<SalaryRequest> clazz) {
		super(clazz);
	}

	@Override
	protected EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	}

	@Override
	public List<SalaryRequest> findByStatus(RequestStatus status) {
		EntityManager em = getEntityManager();
		return em.createQuery(FIND_BY_STATUS, SalaryRequest.class)
				.setParameter("status", status).getResultList();
	}

	@Override
	public List<SalaryRequest> findByStatusInRange(RequestStatus status,
			long start, long end) {
		EntityManager em = getEntityManager();
		if (start >= end) {
			throw new IllegalArgumentException("start>=end");
		}
		return em.createQuery(FIND_BY_STATUS, SalaryRequest.class)
				.setParameter("status", status).setFirstResult((int) start)
				.setMaxResults((int) end - (int) start + 1).getResultList();
	}

	@Override
	public long countByStatus(RequestStatus status) {
		EntityManager em = getEntityManager();
		return em.createQuery(COUNT_BY_STATUS, long.class)
				.setParameter("status", status).getSingleResult();
	}

	@Override
	public Double countDirtySalary(Date start, Date end, Driver driv) {
		if (start.after(end)) {
			throw new IllegalArgumentException("end > start");
		}
		EntityManager em = getEntityManager();
		List<Integer> idList = em.createQuery(GET_TRIP_ID_LIST_BY_DRIVER, Integer.class).setParameter("driv", driv.getIdDriver()).getResultList();
		Double result = em.createQuery(GET_DIRTY_SALARY, Double.class).setParameter("idList", idList).setParameter("start", start).setParameter("end", end).getSingleResult();
		if (idList.size() == 0 || result == null) {
			return 0.0;
		}
		return result;
	}

}
