package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dao.DAOFactory;
import dao.SalaryTaxDAO;

import entities.SalaryTax;

public class SalaryTaxConverter implements Converter{
	private DAOFactory factory = DAOFactory.getFactory(DAOFactory.JPA);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Integer id = Integer.decode(value);
		Object result = null;
		try {
			SalaryTaxDAO dao = factory.getSalaryTaxDAO();
			result = dao.find(id);
		} catch (IllegalArgumentException ex) {
			context.addMessage("err", new FacesMessage("ConvertionError"));
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		SalaryTax res = (SalaryTax) value;
		return Integer.toString(res.getIdTax());
	}

}
