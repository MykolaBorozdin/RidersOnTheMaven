package dao.jpa;

import dao.DAOFactory;
import dao.DriverDAO;
import dao.SalaryBonusDAO;
import dao.SalaryRequestDAO;
import dao.RouteDAO;
import dao.SalaryTaxDAO;
import dao.TripDAO;
import entities.Driver;
import entities.SalaryBonus;
import entities.SalaryRequest;
import entities.Route;
import entities.SalaryTax;
import entities.Trip;

public class JPADAOFactory extends DAOFactory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3046085388812386991L;

	public DriverDAO getDriverDAO() {
		return new JPADriverDAO(Driver.class);
	}
	
	public RouteDAO getRouteDAO() {
		return new JPARouteDAO(Route.class);
	}
	
	public TripDAO getTripDAO() {
		return new JPATripDAO(Trip.class);
	}

	@Override
	public SalaryRequestDAO getSalaryRequestDAO() {
		return new JPARequestDAO(SalaryRequest.class);
	}

	@Override
	public SalaryBonusDAO getSalaryBonusDAO() {
		return new JPASalaryBonusDAO(SalaryBonus.class);
	}

	@Override
	public SalaryTaxDAO getSalaryTaxDAO() {
		return new JPASalaryTaxDAO(SalaryTax.class);
	}
}	
