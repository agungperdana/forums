/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.kratonsolution.products.forums.dm.User;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
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
public class Signin extends Window
{
	private TextField email = new TextField();
	
	private PasswordField password = new PasswordField();
	
	private Button signin = new Button("Sign In");
	
	private Label label = new Label();
	
	private UserDetailsService service;
	
	private AuthenticationManager manager;
	
	public Signin(UserDetailsService service,AuthenticationManager manager)
	{
		setWidth("400px");
		setHeight("225px");
		setCaption("Sign In");
		setIcon(Icons.SIGN_IN);
		
		email.setPlaceholder("Email");
		email.setWidth("100%");
		
		password.setPlaceholder("Password");
		password.setWidth("100%");
		
		signin.setWidth("100%");
		signin.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	
		label.setSizeUndefined();
		label.setContentMode(ContentMode.HTML);
		
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.addComponent(email);
		layout.addComponent(password);
		layout.addComponent(signin);
		layout.addComponent(label);
		
		setContent(layout);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		
		center();
		
		Binder<User> binder = new Binder<>();
		binder.setBean(new User());
		binder.forField(email)
			  .withValidator(new StringLengthValidator("Email cannot be empty", 1, 100))
			  .bind(User::getEmail,User::setEmail);
		binder.forField(password)
			  .withValidator(new StringLengthValidator("Password cannot be empty", 8, 100))
			  .bind(User::getPassword,User::setPassword);
		
		signin.addClickListener(event->{
			if(service != null && manager != null)
			{
				if(binder.validate().isOk())
				{
					try
					{
						UserDetails details = service.loadUserByUsername(email.getValue());
						UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email.getValue(),password.getValue());
						token.setDetails(details);

						Authentication authentication = manager.authenticate(token);
						if(authentication.isAuthenticated())
						{
							signin.setEnabled(false);
							SecurityContextHolder.getContext().setAuthentication(authentication);
							
							if(email.getValue().equals("admin@mark3.com"))
								Page.getCurrent().setLocation("/Admin");
							else
								Page.getCurrent().setLocation("/Home");
						}
						else
							label.setValue("<font color='red'>Login fail, please try again!</font>");
					}
					catch (Exception e)
					{
						e.printStackTrace();
						label.setValue("<font color='red'>Login fail, please try again!</font>");
					}
				}
			}
		});
	}
}
