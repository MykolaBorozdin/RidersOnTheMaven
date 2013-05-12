package backingbeans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.NoResultException;

import dao.DAOFactory;
import dao.DriverDAO;
import dao.SalaryRequestDAO;

import entities.RequestStatus;
import entities.SalaryRequest;

@ManagedBean(name = "createRequest")
@SessionScoped
public class CreateRequestBean {
	private int id;
	private SalaryRequest salaryRequest;
	private DAOFactory factory;
	
	public CreateRequestBean() {
		factory = DAOFactory.getFactory(DAOFactory.JPA);
		salaryRequest = new SalaryRequest();
		salaryRequest.setStartSalaryDate(new Date(System.currentTimeMillis()));
		salaryRequest.setRequestStatus(RequestStatus.EMPTY);
	}
	
	@PostConstruct
	public void init() {
		DriverDAO dao = factory.getDriverDAO();
		salaryRequest.setDriver(dao.find(id));
	}
	
	public String create() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		DriverDAO drivdao = factory.getDriverDAO();
		salaryRequest.setDriver(drivdao.find(id));
		dao.create(salaryRequest);
		return "acc1.xhtml?faces-redirect=true";
	}

	public SalaryRequest getSalaryRequest() {
		return salaryRequest;
	}

	public void setSalaryRequest(SalaryRequest request) {
		this.salaryRequest = request;
	}
	
	public String getDriverName() {
		DriverDAO dao = factory.getDriverDAO();
		return dao.find(id).getFullName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String addRequest() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		try {
			dao.find(id);
		} catch (NoResultException ex) {
			return "acc1.xhtml?faces-redirect=true";
		}
		return "createRequest.xhtml?faces-redirect=true";
	}

}
