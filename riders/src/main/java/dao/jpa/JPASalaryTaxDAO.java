package dao.jpa;

import javax.persistence.EntityManager;

import dao.SalaryTaxDAO;
import entities.SalaryTax;

public class JPASalaryTaxDAO extends JPAAbstractDAO<SalaryTax> implements SalaryTaxDAO {

	public JPASalaryTaxDAO(Class<SalaryTax> clazz) {
		super(clazz);
	}

	@Override
	protected EntityManager getEntityManager() {
		return getEmf().createEntityManager();
	}

	

}
