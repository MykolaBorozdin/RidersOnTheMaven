package backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.TransactionRequiredException;

import dao.DAOFactory;
import dao.Paginator;
import dao.SalaryRequestDAO;

import entities.RequestStatus;
import entities.SalaryRequest;

@ManagedBean(name = "requestBean")
@ViewScoped
public class RequestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5954425677467943135L;
	private static final int PAGESIZE = 5;

	private List<SalaryRequest> requestList;
	private Paginator paginator;
	private DAOFactory factory;
	private static Logger log = Logger.getGlobal();

	@PostConstruct
	public void init() {
		factory = DAOFactory.getFactory(DAOFactory.JPA);
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		requestList = new ArrayList<SalaryRequest>();
		log.info("dao.count gived me this: "
				+ dao.countByStatus(RequestStatus.EMPTY));
		paginator = new Paginator(PAGESIZE,
				dao.countByStatus(RequestStatus.EMPTY));
		requestList = dao.findByStatusInRange(RequestStatus.EMPTY,
				getPaginator().getStart(), getPaginator().getEnd());
	}

	public List<SalaryRequest> getRequestList() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		requestList = dao.findByStatusInRange(RequestStatus.EMPTY,
				getPaginator().getStart(), getPaginator().getEnd());
		return requestList;
	}

	public void setRequestList(List<SalaryRequest> requestList) {
		this.requestList = requestList;
	}

	public void goToNextPage() {
		if (!getPaginator().isLastPage()) {
			getPaginator().turnToNextPage();
			SalaryRequestDAO dao = factory.getSalaryRequestDAO();
			requestList.clear();
			requestList = dao.findByStatusInRange(RequestStatus.EMPTY,
					getPaginator().getStart(), getPaginator().getEnd());
		} else {
			FacesContext.getCurrentInstance().addMessage("res",
					new FacesMessage("This is last page"));
		}
	}

	public void goToPreviousPage() {
		if (!getPaginator().isFirstPage()) {
			paginator.turnPageBack();
			SalaryRequestDAO dao = factory.getSalaryRequestDAO();
			requestList = dao.findByStatusInRange(RequestStatus.EMPTY,
					getPaginator().getStart(), getPaginator().getEnd());
		} else {
			FacesContext.getCurrentInstance().addMessage("res",
					new FacesMessage("This is first page"));
		}
	}

	public String editRequest(SalaryRequest request) {
		return "editRequest.xhtml?faces-redirect=true&id="
				+ request.getIdSalaryRequest();
	}

	public void removeRequest(SalaryRequest request) {
		log.info("I`m trying to remove the shit");
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		try {
			dao.delete(request);
		} catch (IllegalStateException ex1) {
			FacesContext.getCurrentInstance().addMessage(
					"res",
					new FacesMessage(
							"Got an error, check the results manually."));
		} catch (IllegalArgumentException ex2) {
			FacesContext.getCurrentInstance().addMessage(
					"res",
					new FacesMessage(
							"Got an error, check the results manually."));
		} catch (TransactionRequiredException ex3) {
			FacesContext.getCurrentInstance().addMessage(
					"res",
					new FacesMessage(
							"Got an error, check the results manually."));
		}
		paginator.setMaxRow(paginator.getMaxRow() - 1);
		FacesContext.getCurrentInstance().addMessage("res",
				new FacesMessage("You killed Kenny!"));
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

}
