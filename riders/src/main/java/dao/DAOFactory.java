package dao;

import java.io.Serializable;

import dao.jpa.JPADAOFactory;

public abstract class DAOFactory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4837219761428591918L;
	public static final int JPA = 1;
	
	public static DAOFactory getFactory(int factoryType){
		switch (factoryType) {
		case 1: return new JPADAOFactory();
		default: return null;
		}
	}
	
	public abstract DriverDAO getDriverDAO();
	public abstract TripDAO getTripDAO();
	public abstract RouteDAO getRouteDAO();
	public abstract SalaryRequestDAO getSalaryRequestDAO();
	public abstract SalaryBonusDAO getSalaryBonusDAO();
	public abstract SalaryTaxDAO getSalaryTaxDAO();
}
