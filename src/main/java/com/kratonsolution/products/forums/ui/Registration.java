/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class Registration extends Window
{
	private TextField email = new TextField("Email");
	
	private TextField name = new TextField("Name");
	
	private DateField birth = new DateField("Birth Date");

	private PasswordField password = new PasswordField("Password");
	
	private PasswordField repassword = new PasswordField("Retype-Password");

	private Button signin = new Button("Submit");

	public Registration()
	{
		setWidth("400px");
		setHeight("500px");
		setCaption("New Account");
		setIcon(Icons.CREATE_ACCOUNT);

		email.setPlaceholder("Email");
		email.setWidth("100%");
		
		name.setPlaceholder("Name");
		name.setWidth("100%");

		password.setPlaceholder("Password");
		password.setWidth("100%");
		
		repassword.setPlaceholder("Password");
		repassword.setWidth("100%");

		signin.setWidth("100%");
		signin.setStyleName(ValoTheme.BUTTON_FRIENDLY);

		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.addComponent(email);
		layout.addComponent(name);
		layout.addComponent(birth);
		layout.addComponent(password);
		layout.addComponent(repassword);
		layout.addComponent(signin);

		setContent(layout);
		setModal(true);
		setResizable(false);
		setDraggable(false);

		center();
	}
}
