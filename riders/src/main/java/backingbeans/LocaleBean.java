package backingbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "locales")
@SessionScoped
public class LocaleBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2388500626447154222L;
	
	
	private Map<String, Locale> localeMap;
	private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();;
	
	@PostConstruct
	public void init() {
		setLocaleMap(new HashMap<String, Locale>());
		getLocaleMap().put("EN", new Locale("en"));
		getLocaleMap().put("RU", new Locale("ru"));
		getLocaleMap().put("UK", new Locale("uk"));
	}
	
	
	public void changeLocale(String newLocale) {
		Locale loc = getLocaleMap().get(newLocale);
		currentLocale = loc;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(loc);
	}
	
	
	
	public Locale getCurrentLocale() {
		return currentLocale;
	}
	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}


	public Map<String, Locale> getLocaleMap() {
		return localeMap;
	}


	public void setLocaleMap(Map<String, Locale> localeMap) {
		this.localeMap = localeMap;
	}
	
}
