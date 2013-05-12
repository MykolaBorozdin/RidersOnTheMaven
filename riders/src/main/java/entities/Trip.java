package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "trip")
public class Trip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8433174798618219627L;

	@Id
	@Column(name = "ID_TRIP")
	private Integer idTrip;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROUTE")
	private Route idRoute;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATE_START")
	private Date startDate;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATE_END")
	private Date endDate;
	
	@ManyToMany
	@JoinTable(name = "fk_id_driver", joinColumns =  @JoinColumn(name = "ID_TRIP"), inverseJoinColumns = @JoinColumn(name = "ID_DRIVER"))
	private Set<Driver> drivers;
	
	
	public Integer getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(Integer idTrip) {
		this.idTrip = idTrip;
	}

	public Route getRoute() {
		return idRoute;
	}

	public void setRoute(Route idRoute) {
		this.idRoute = idRoute;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drivers == null) ? 0 : drivers.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((idRoute == null) ? 0 : idRoute.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Trip)) {
			return false;
		}
		Trip other = (Trip) obj;
		if (drivers == null) {
			if (other.drivers != null) {
				return false;
			}
		} else if (!drivers.equals(other.drivers)) {
			return false;
		}
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (idRoute == null) {
			if (other.idRoute != null) {
				return false;
			}
		} else if (!idRoute.equals(other.idRoute)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		return true;
	}
	
	
}
