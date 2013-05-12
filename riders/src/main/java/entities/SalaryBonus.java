package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="salary_bonus")
public class SalaryBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_BONUS")
	private int idBonus;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="VALUE")
	private int value;
	
	@Column(name="SHORT_NAME")
	private String shortName;
	

	public SalaryBonus() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + value;
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
		if (!(obj instanceof SalaryBonus)) {
			return false;
		}
		SalaryBonus other = (SalaryBonus) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (shortName == null) {
			if (other.shortName != null) {
				return false;
			}
		} else if (!shortName.equals(other.shortName)) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

	public int getIdBonus() {
		return this.idBonus;
	}

	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}

	public String getDescription() {
		System.out.println("      Trying to get the salarybonus description!!!!!");
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}