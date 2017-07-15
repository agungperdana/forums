/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import java.time.LocalDate;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.DateField;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class DateValidator extends AbstractValidator<LocalDate>
{
	public DateValidator(String errorMessage)
	{
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ValidationResult apply(LocalDate value, ValueContext context)
	{
		DateField field = (DateField)context.getComponent().get();
		if(field.getValue() != null)
			return ValidationResult.ok();
		
		return ValidationResult.error("Date cannot be emtpy");
	}

}
