package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="salary_request")
public class SalaryRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5812350690610832824L;

	@Id
	@Column(name = "id_salary_request")
	private Integer idSalaryRequest;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_start_request")
	private Date startRequestDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_end_request")
	private Date endRequestDate;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_start_salary")
	private Date startSalaryDate;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_end_salary")
	private Date endSalaryDate;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private RequestStatus requestStatus;
	
	@Column(name = "salary_value")
	private Double salary;
	
	@OneToOne
	@JoinColumn(name = "id_driver")
	private Driver driver;
	

	@ManyToMany
	@JoinTable(name="salary_request_salary_bonus", joinColumns = @JoinColumn(name="ID_SALARY_REQUEST"), inverseJoinColumns = @JoinColumn(name="ID_BONUS"))
	private List<SalaryBonus> bonuses;
	
	@ManyToMany
	@JoinTable(name="salary_request_salary_tax", joinColumns = @JoinColumn(name="ID_SALARY_REQUEST"), inverseJoinColumns = @JoinColumn(name="ID_TAX"))
	private List<SalaryTax> taxes;
	
	public SalaryRequest() {
		startRequestDate = new Date();
		endRequestDate = new Date();
		startSalaryDate = new Date();
		endSalaryDate = new Date();
	}

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result
				+ ((endRequestDate == null) ? 0 : endRequestDate.hashCode());
		result = prime * result
				+ ((endSalaryDate == null) ? 0 : endSalaryDate.hashCode());
		result = prime * result
				+ ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime
				* result
				+ ((startRequestDate == null) ? 0 : startRequestDate.hashCode());
		result = prime * result
				+ ((startSalaryDate == null) ? 0 : startSalaryDate.hashCode());
		result = prime * result + ((taxes == null) ? 0 : taxes.hashCode());
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SalaryRequest)) {
			return false;
		}
		SalaryRequest other = (SalaryRequest) obj;
		if (bonuses == null) {
			if (other.bonuses != null) {
				return false;
			}
		} else if (!bonuses.equals(other.bonuses)) {
			return false;
		}
		if (driver == null) {
			if (other.driver != null) {
				return false;
			}
		} else if (!driver.equals(other.driver)) {
			return false;
		}
		if (endRequestDate == null) {
			if (other.endRequestDate != null) {
				return false;
			}
		} else if (!endRequestDate.equals(other.endRequestDate)) {
			return false;
		}
		if (endSalaryDate == null) {
			if (other.endSalaryDate != null) {
				return false;
			}
		} else if (!endSalaryDate.equals(other.endSalaryDate)) {
			return false;
		}
		if (requestStatus != other.requestStatus) {
			return false;
		}
		if (salary == null) {
			if (other.salary != null) {
				return false;
			}
		} else if (!salary.equals(other.salary)) {
			return false;
		}
		if (startRequestDate == null) {
			if (other.startRequestDate != null) {
				return false;
			}
		} else if (!startRequestDate.equals(other.startRequestDate)) {
			return false;
		}
		if (startSalaryDate == null) {
			if (other.startSalaryDate != null) {
				return false;
			}
		} else if (!startSalaryDate.equals(other.startSalaryDate)) {
			return false;
		}
		if (taxes == null) {
			if (other.taxes != null) {
				return false;
			}
		} else if (!taxes.equals(other.taxes)) {
			return false;
		}
		return true;
	}



	public int getIdSalaryRequest() {
		return idSalaryRequest;
	}

	public void setIdSalaryRequest(int requestId) {
		this.idSalaryRequest = requestId;
	}

	public Date getStartRequestDate() {
		return startRequestDate;
	}

	public void setStartRequestDate(Date startRequestDate) {
		this.startRequestDate = startRequestDate;
	}

	public Date getEndRequestDate() {
		return endRequestDate;
	}

	public void setEndRequestDate(Date endRequestDate) {
		this.endRequestDate = endRequestDate;
	}

	public Date getStartSalaryDate() {
		return startSalaryDate;
	}

	public void setStartSalaryDate(Date startSalaryDate) {
		this.startSalaryDate = startSalaryDate;
	}

	public Date getEndSalaryDate() {
		return endSalaryDate;
	}

	public void setEndSalaryDate(Date endSalaryDate) {
		this.endSalaryDate = endSalaryDate;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driverId) {
		this.driver = driverId;
	}



	public List<SalaryBonus> getBonuses() {
		return bonuses;
	}



	public void setBonuses(List<SalaryBonus> bonuses) {
		this.bonuses = bonuses;
	}



	public List<SalaryTax> getTaxes() {
		return taxes;
	}



	public void setTaxes(List<SalaryTax> taxes) {
		this.taxes = taxes;
	}

	
}
