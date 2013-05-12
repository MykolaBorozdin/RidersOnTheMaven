package dao.jpa;

import javax.persistence.EntityManager;

import dao.SalaryBonusDAO;
import entities.SalaryBonus;

public class JPASalaryBonusDAO extends JPAAbstractDAO<SalaryBonus> implements SalaryBonusDAO{

	public JPASalaryBonusDAO(Class<SalaryBonus> clazz) {
		super(clazz);
	}

	@Override
	protected EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	}

}
