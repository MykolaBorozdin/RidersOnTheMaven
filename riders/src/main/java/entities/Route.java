package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5583282562677390447L;

	@Id
	@Column(name = "ID_ROUTE")
	private int idRoute;
	
	@Column(name = "ROUTE_NAME")
	private String routeName;
	
	@Column(name = "ROUTE_LENGTH")
	private int routeLength;
	
	@Column(name = "POINT_OF_START")
	private int startPoint;
	
	@Column(name = "POINT_OF_DESTINATION")
	private int destinationPoint;
	
	@Column(name = "BASIC_PAYMENT")
	private double basicPayment;
	
	public int getIdRoute() {
		return idRoute;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public int getRouteLength() {
		return routeLength;
	}

	public void setRouteLength(int routeLength) {
		this.routeLength = routeLength;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getDestinationPoint() {
		return destinationPoint;
	}

	public void setDestinationPoint(int destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public double getBasicPayment() {
		return basicPayment;
	}

	public void setBasicPayment(double basicPayment) {
		this.basicPayment = basicPayment;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(basicPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + destinationPoint;
		result = prime * result + routeLength;
		result = prime * result
				+ ((routeName == null) ? 0 : routeName.hashCode());
		result = prime * result + startPoint;
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
		if (!(obj instanceof Route)) {
			return false;
		}
		Route other = (Route) obj;
		if (Double.doubleToLongBits(basicPayment) != Double
				.doubleToLongBits(other.basicPayment)) {
			return false;
		}
		if (destinationPoint != other.destinationPoint) {
			return false;
		}
		if (routeLength != other.routeLength) {
			return false;
		}
		if (routeName == null) {
			if (other.routeName != null) {
				return false;
			}
		} else if (!routeName.equals(other.routeName)) {
			return false;
		}
		if (startPoint != other.startPoint) {
			return false;
		}
		return true;
	}
	
	
}
