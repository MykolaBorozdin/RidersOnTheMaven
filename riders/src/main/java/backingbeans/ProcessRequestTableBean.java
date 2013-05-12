package backingbeans;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.TransactionRequiredException;

import dao.DAOFactory;
import dao.Paginator;
import dao.SalaryBonusDAO;
import dao.SalaryRequestDAO;
import dao.SalaryTaxDAO;
import entities.RequestStatus;
import entities.SalaryBonus;
import entities.SalaryRequest;
import entities.SalaryTax;

@ManagedBean(name = "processRequestTable")
@SessionScoped
public class ProcessRequestTableBean {
	private static final int PAGE_SIZE = 5;

	private DAOFactory factory;
	private Paginator paginator;
	private transient DataModel<SalaryRequest> requestList;
	private SalaryRequest currentRequest;
	private static Logger logger;
	private boolean useCurrentBonus;
	private boolean useCurrentTax;
	private SalaryBonus currentBonus;
	private SalaryTax currentTax;
	private Map<String,SalaryBonus> bonuses;
	private Map<String,SalaryTax> taxes;

	public ProcessRequestTableBean() {
		logger = Logger.getGlobal();
		factory = DAOFactory.getFactory(DAOFactory.JPA);
		setRequestList(new ListDataModel<SalaryRequest>());
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		setPaginator(new Paginator(PAGE_SIZE,
				dao.countByStatus(RequestStatus.DIRTY_SALARY)));
		getRequestList().setWrappedData(
				dao.findByStatusInRange(RequestStatus.DIRTY_SALARY,
						getPaginator().getStart(), getPaginator().getEnd()));
		logger.info("                                   "
				+ dao.findByStatusInRange(RequestStatus.EMPTY, getPaginator()
						.getStart(), getPaginator().getEnd()) + '\n'
				+ getPaginator().getEnd() + '\n'
				+ dao.findByStatus(RequestStatus.DIRTY_SALARY).size());
		SalaryBonusDAO salaryBonusDAO = factory.getSalaryBonusDAO();
		bonuses = new LinkedHashMap<String,SalaryBonus>();
		List<SalaryBonus> bonusList = salaryBonusDAO.findAll();
		for (int i = 0; i< bonusList.size(); i++) {
			getBonuses().put(bonusList.get(i).getShortName(), bonusList.get(i));
		}
		SalaryTaxDAO salaryTaxDAO = factory.getSalaryTaxDAO();
		taxes = new LinkedHashMap<String,SalaryTax>();
		List<SalaryTax> taxList = salaryTaxDAO.findAll();
		for (int i = 0; i < taxList.size(); i++) {
			getTaxes().put(taxList.get(i).getShortName(), taxList.get(i));
		}
	}
	
	public void bonusChanged(ValueChangeEvent e) {
		this.currentBonus = (SalaryBonus) e.getNewValue();
	}
	
	public void taxChanged(ValueChangeEvent e) {
		this.currentTax = (SalaryTax) e.getNewValue();
	}
	
	public DataModel<SalaryRequest> getRequestList() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();//
		requestList.setWrappedData(dao.findByStatusInRange(
				RequestStatus.DIRTY_SALARY, getPaginator().getStart(),
				getPaginator().getEnd()));
		return requestList;
	}

	public void setRequestList(DataModel<SalaryRequest> requestList) {
		this.requestList = requestList;
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

	public String editRequest() {
		try {
			setCurrentRequest(requestList.getRowData());
		} catch (FacesException ex) {
			FacesContext.getCurrentInstance().addMessage("res",
					new FacesMessage("Error in getting data from table."));
		}
		logger.info("          Trying to edit the request///");
		return "success";
	}

	public void removeRequest() {
		SalaryRequest currentRequest = requestList.getRowData();
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		try {
			dao.delete(currentRequest);
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
				new FacesMessage("Succesfully removed."));
	}

	public void saveChanges() {
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		dao.update(getCurrentRequest());
		FacesContext.getCurrentInstance().addMessage("result",
				new FacesMessage("Succesfully changed."));
	}
	
	public String countCleanSalary() {
		if (currentRequest.getRequestStatus() == RequestStatus.DIRTY_SALARY || currentRequest.getRequestStatus() == RequestStatus.WITH_BONUSES) {
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage("res",
					new FacesMessage("Some error, talk to admin."));
			return "fail";
		}
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public SalaryRequest getCurrentRequest() {
		return currentRequest;
	}

	public void setCurrentRequest(SalaryRequest currentRequest) {
		this.currentRequest = currentRequest;
	}

	public SalaryBonus getCurrentBonus() {
		return currentBonus;
	}

	public void setCurrentBonus(SalaryBonus currentBonus) {
		logger.info("The new current bonus is:" + currentBonus.getShortName());
		this.currentBonus = currentBonus;
		useCurrentBonus = currentRequest.getBonuses().contains(currentBonus);
	}

	public boolean isUseCurrentBonus() {
		return useCurrentBonus;
	}

	public void setUseCurrentBonus(boolean useCurrentBonus) {
		if (this.useCurrentBonus != useCurrentBonus) {
			if (this.useCurrentBonus) {
				currentRequest.getBonuses().remove(currentBonus);
			} else {
				currentRequest.getBonuses().add(currentBonus);
			}
		}
		this.useCurrentBonus = useCurrentBonus;
	}

	public Map<String,SalaryBonus> getBonuses() {
		return bonuses;
	}

	public void setBonuses(Map<String,SalaryBonus> bonuses) {
		this.bonuses = bonuses;
	}
	
	public String countBonuses() {
		Double result = currentRequest.getSalary();
		for(int i = 0; i < currentRequest.getBonuses().size(); i++) {
			logger.info(currentRequest.getBonuses().toString());
			logger.info(currentRequest.getBonuses().get(i).getShortName());
			result += result * ((double) currentRequest.getBonuses().get(i).getValue()/100);
		}
		currentRequest.setSalary(result);
		currentRequest.setRequestStatus(RequestStatus.WITH_BONUSES);
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		dao.update(currentRequest);
		return "taxes.xhtml?faces-redirect=true";
	}
	
	public String countTaxes() {
		Double result = currentRequest.getSalary();
		for(int i = 0; i < currentRequest.getTaxes().size(); i++) {
			result -= result * ((double) currentRequest.getTaxes().get(i).getValue()/100);
		}
		currentRequest.setSalary(result);
		currentRequest.setRequestStatus(RequestStatus.CLEAN_SALARY);
		SalaryRequestDAO dao = factory.getSalaryRequestDAO();
		dao.update(currentRequest);
		return "acc2.xhtml?faces-redirect=true";
	}

	public boolean isUseCurrentTax() {
		return useCurrentTax;
	}

	public void setUseCurrentTax(boolean useCurrentTax) {
		if (this.useCurrentTax != useCurrentTax) {
			if (this.useCurrentTax) {
				currentRequest.getTaxes().remove(currentRequest);
			} else {
				currentRequest.getTaxes().add(currentTax);
			}
		}
		this.useCurrentTax = useCurrentTax;
	}

	public SalaryTax getCurrentTax() {
		return currentTax;
	}

	public void setCurrentTax(SalaryTax currentTax) {
		this.currentTax = currentTax;
		useCurrentTax = currentRequest.getTaxes().contains(currentTax);
	}

	
	public Map<String,SalaryTax> getTaxes() {
		return taxes;
	}

	public void setTaxes(Map<String,SalaryTax> taxes) {
		this.taxes = taxes;
	}
}
