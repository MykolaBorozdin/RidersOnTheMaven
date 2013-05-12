package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dao.DAOFactory;
import dao.SalaryBonusDAO;
import entities.SalaryBonus;

public class SalaryBonusConverter implements Converter{
private DAOFactory factory = DAOFactory.getFactory(DAOFactory.JPA);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Integer id = Integer.decode(value);
		Object result = null;
		try {
			SalaryBonusDAO dao = factory.getSalaryBonusDAO();
			result = dao.find(id);
		} catch (IllegalArgumentException ex) {
			context.addMessage("err", new FacesMessage("ConvertionError"));
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		SalaryBonus res = (SalaryBonus)value;
		return Integer.toString(res.getIdBonus());
	}

}
