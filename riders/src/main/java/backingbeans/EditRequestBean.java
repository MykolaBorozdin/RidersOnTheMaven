package backingbeans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import dao.DAOFactory;
import dao.SalaryRequestDAO;

import entities.RequestStatus;
import entities.SalaryRequest;

@ManagedBean(name="editRequest")
@ViewScoped
public class EditRequestBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4746272332258929685L;
	private int id;
	private SalaryRequest currentRequest;
	private DAOFactory factory;
	private static Logger log = Logger.getGlobal();
	
	@PostConstruct
	public void init() {
		log.info("POSTCRONSTRUCT CALLED!");
		factory = DAOFactory.getFactory(DAOFactory.JPA);
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		currentRequest = dao.find(id);
		log.info("POSTCRONSTRUCT CALLED! + id" + id);
	}
	
	public void saveChanges() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		dao.update(getCurrentRequest());
		FacesContext.getCurrentInstance().addMessage("result", new FacesMessage("Succesfully changed."));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDriverName() {
		log.info("GETNAME CALLED!");
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		SalaryRequest req = dao.find(id);
		return req.getDriver().getFullName();
	}

	public SalaryRequest getCurrentRequest() {
		log.info("GETREQUEST CALLED!");
		return currentRequest;
	}

	public void setCurrentRequest(SalaryRequest currentRequest) {
		this.currentRequest = currentRequest;
	}
	
	public void countDirtySalary() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		currentRequest.setSalary(dao.countDirtySalary(currentRequest.getStartSalaryDate(), currentRequest.getEndSalaryDate(), currentRequest.getDriver()));
		currentRequest.setRequestStatus(RequestStatus.DIRTY_SALARY);
		log.info(" ......... ....    ..... I counted this: " + currentRequest.getSalary());
		dao.update(currentRequest);
		FacesContext.getCurrentInstance().addMessage("result", new FacesMessage("Succesfully counted."));
	}
}
