package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class JPAAbstractDAO<T> {
private EntityManagerFactory emf;
private Class<T> clazz;
private final String FIND_ALL;
private final String COUNT;
private final String PERSISTENCE_UNIT = "Riders";
	
	public JPAAbstractDAO(Class<T> clazz) {
		this.clazz = clazz;
		FIND_ALL = "SELECT t FROM " + this.clazz.getSimpleName() + " t";
		COUNT = "SELECT COUNT(t.id" + this.clazz.getSimpleName() + ") FROM " + this.clazz.getSimpleName() + " t";
		setEmf(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT));
	}
	
	protected abstract EntityManager getEntityManager();
	
	public void create(T t) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public void update(T t) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void delete(T t) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(t));
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<T> findAll() {
		EntityManager em = getEntityManager();
		return em.createQuery(FIND_ALL, this.clazz).getResultList();
	}

	public T find(int id) {
		EntityManager em = getEntityManager();
		return em.find(clazz, id);
	}
	
	public long count() {
		EntityManager em = getEntityManager();
		return em.createQuery(COUNT, long.class).getSingleResult();
	}
	
	public List<T> findInRange(long start, long end) {
		if (start >= end) {
			throw new IllegalArgumentException("start>=end");
		}
		EntityManager em = getEntityManager();
		return em.createQuery(FIND_ALL, this.clazz).setFirstResult((int)start).setMaxResults((int)end - (int)start + 1).getResultList();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
}
