package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "driver")
public class Driver implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5702267894894262482L;

	@Id
	@Column(name = "ID_DRIVER")
	private int idDriver;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "DATE_START")
	private Date startDate;

	@Column(name = "PASSPORT_CODE")
	private String passportCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "CITIZENSHIP")
	private Citizenship citizenship;
	


	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((citizenship == null) ? 0 : citizenship.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((passportCode == null) ? 0 : passportCode.hashCode());
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
		if (!(obj instanceof Driver)) {
			return false;
		}
		Driver other = (Driver) obj;
		if (citizenship != other.citizenship) {
			return false;
		}
		if (fullName == null) {
			if (other.fullName != null) {
				return false;
			}
		} else if (!fullName.equals(other.fullName)) {
			return false;
		}
		if (passportCode == null) {
			if (other.passportCode != null) {
				return false;
			}
		} else if (!passportCode.equals(other.passportCode)) {
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
	
	public int getIdDriver() {
		return idDriver;
	}

	public void setIdDriver(int idDriver) {
		this.idDriver = idDriver;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getPassportCode() {
		return passportCode;
	}

	public void setPassportCode(String passportCode) {
		this.passportCode = passportCode;
	}

	public Citizenship getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Citizenship citizenship) {
		this.citizenship = citizenship;
	}

}
