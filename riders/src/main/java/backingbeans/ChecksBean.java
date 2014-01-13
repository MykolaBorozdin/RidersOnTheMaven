package backingbeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import dao.DAOFactory;
import dao.Paginator;
import dao.SalaryRequestDAO;
import entities.RequestStatus;
import entities.SalaryRequest;

@ManagedBean(name="checksBean")
@SessionScoped
public class ChecksBean{

private static final int PAGE_SIZE = 5;
	
private DAOFactory factory;
private Paginator paginator;
private transient DataModel<SalaryRequest> requestList;
private String checkText;

	public ChecksBean() {
		checkText = "";
		factory = DAOFactory.getFactory(DAOFactory.JPA);
		setRequestList(new ListDataModel<SalaryRequest>());
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		setPaginator(new Paginator(PAGE_SIZE,
				dao.countByStatus(RequestStatus.CLEAN_SALARY)));
		getRequestList().setWrappedData(
				dao.findByStatusInRange(RequestStatus.CLEAN_SALARY,
						getPaginator().getStart(), getPaginator().getEnd()));
	}
	
	public void goToNextPage() {
		if (!getPaginator().isLastPage()) {
			getPaginator().turnToNextPage();
			SalaryRequestDAO dao = factory.getSalaryRequestDAO();
			requestList
					.setWrappedData(
							dao.findByStatusInRange(RequestStatus.DIRTY_SALARY,
									getPaginator().getStart(), getPaginator()
											.getEnd()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res",
					new FacesMessage("This is last page"));
		}
	}

	public void goToPreviousPage() {
		if (!getPaginator().isFirstPage()) {
			getPaginator().turnPageBack();
			SalaryRequestDAO dao = factory.getSalaryRequestDAO();
			requestList
					.setWrappedData(
							dao.findByStatusInRange(RequestStatus.DIRTY_SALARY,
									getPaginator().getStart(), getPaginator()
											.getEnd()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res",
					new FacesMessage("This is first page"));
		}
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public DataModel<SalaryRequest> getRequestList() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		requestList.setWrappedData(dao.findByStatusInRange(
				RequestStatus.CLEAN_SALARY, getPaginator().getStart(),
				getPaginator().getEnd()));
		return requestList;
	}

	public void setRequestList(DataModel<SalaryRequest> requestList) {
		this.requestList = requestList;
	}
	
	public String printCheck() {
		SalaryRequest currentRequest = requestList.getRowData();
		checkText = "";
		checkText += currentRequest.getDriver().getFullName() + "<br/>";
		checkText += currentRequest.getDriver().getPassportCode() + "<br/>";
		checkText += currentRequest.getDriver().getCitizenship().toString() + "<br/>";
		checkText += currentRequest.getStartSalaryDate().toString() + "<br/>";
		checkText += currentRequest.getEndSalaryDate().toString() + "<br/>";
		checkText += currentRequest.getSalary().toString() + "<br/>";
		return "check.xhtml?faces-rediresct=true";
	}

	public String getCheckText() {
		return checkText;
	}

	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}
}
