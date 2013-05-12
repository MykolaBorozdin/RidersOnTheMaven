package validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="requestDateValidator")
public class RequestDateValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
			UIOutput startInput = (UIOutput) component.getAttributes().get("startRequestDate");
	        Date startDate = (Date) startInput.getValue();
	        Date endDate = (Date) value;
	        if ((int) (endDate.getTime() / 86400000) - (int) (startDate.getTime() / 86400000) < 0) {
	            FacesMessage message = new FacesMessage("Date_error");
	            message.setSeverity(FacesMessage.SEVERITY_ERROR);
	            throw new ValidatorException(message);
	        }
		
	}

}
