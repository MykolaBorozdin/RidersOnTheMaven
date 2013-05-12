package validators;

import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="salaryDateValidator")
public class SalaryDateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Map<String, Object> map = component.getAttributes();
		UIInput startInput = (UIInput) map.get("startSalaryDate");
        Date startDate = (Date) startInput.getValue();
        Date endDate = (Date) value;
        int start = (int) (startDate.getTime() / 86400000);
        int end = (int) (endDate.getTime() / 86400000);
        if (end - start < 0) {
            FacesMessage message = new FacesMessage("Date_error");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
	

	}

}
