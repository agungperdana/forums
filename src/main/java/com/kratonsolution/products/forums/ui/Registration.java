/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import java.sql.Date;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.svc.UserService;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
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

	private Button signin = new Button("Register");
	
	private UserService service = Springs.get(UserService.class);
	
	private boolean done = false;

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
		signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);

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
		
		Binder<User> binder = new Binder<>();
		binder.setBean(new User());
		binder.forField(email)
		  	  .withValidator(new StringLengthValidator("Name cannot be empty", 1, 150))
		  	  .bind(User::getName,User::setName);
		binder.forField(email)
			  .withValidator(new StringLengthValidator("Email cannot be empty", 1, 150))
			  .bind(User::getEmail,User::setEmail);
		binder.forField(password)
			  .withValidator(new StringLengthValidator("Password cannot be empty", 8, 100))
			  .bind(User::getPassword,User::setPassword);
		binder.forField(repassword)
		  	  .withValidator(new StringLengthValidator("Retype - Password cannot be empty", 8, 100))
		  	  .bind(User::getPassword,User::setPassword);
		
		signin.addClickListener(event->{
			if(service != null)
			{
				if(binder.validate().isOk())
				{
					if(!password.getValue().equals(repassword.getValue()))
					{
						Notification.show("Password not equals", Type.WARNING_MESSAGE);
						return;
					}
					
					if(birth.getValue() == null)
					{
						Notification.show("Birth Date cannot be empty", Type.WARNING_MESSAGE);
						return;
					}
					
					binder.getBean().setBirthDate(Date.valueOf(birth.getValue()));
					
					try
					{
						service.add(binder.getBean());
					} 
					catch (Exception e)
					{
						Notification.show(e.getMessage(),Type.ERROR_MESSAGE);
						return;
					}
					
					done = true;
					
					Notification.show("Your account successfully created, please check your email for verification", Type.WARNING_MESSAGE);
					
					UI.getCurrent().removeWindow(Registration.this);
				}
			}
		});
	}
}
