/**
 * 
 */
package com.kratonsolution.products.forums.ui.user;

import org.vaadin.dialogs.ConfirmDialog;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.svc.UserService;
import com.kratonsolution.products.forums.ui.Registration;
import com.kratonsolution.products.forums.ui.Table;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.TextRenderer;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserTable extends Table<User>
{
	private UserService service = Springs.get(UserService.class);
	
	public UserTable()
	{
		super();
		
		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addColumn(User::getName,new TextRenderer()).setCaption("Name").setExpandRatio(15);
		grid.addColumn(User::getEmail,new TextRenderer()).setCaption("Email").setExpandRatio(15);
		grid.addColumn(User::getBirthDate,new DateRenderer()).setCaption("Birth Date").setExpandRatio(1);
		grid.addColumn(User::getEnabledStatus).setCaption("Enabled").setExpandRatio(1);
		grid.addColumn(User::getActivatedStatus,new TextRenderer()).setCaption("Activated").setExpandRatio(1);
		grid.addColumn(servuce->"Edit", new ButtonRenderer<>(eClick->{
			Notification.show(eClick.getItem().toString());
			
			UserForm form = new UserForm((User)eClick.getItem());
			form.addCloseListener(close->{
				refresh();
			});
			
			UI.getCurrent().addWindow(form);
		})).setExpandRatio(1);
		
		grid.setDataProvider(new UserProvider());
		
		toolbar.getAdd().setCommand(event->{
			Registration registration = new Registration();
			registration.addCloseListener(close->{
				refresh();
			});
			
			UI.getCurrent().addWindow(registration);
		});
		
		toolbar.getRefresh().setCommand(event->{
			refresh();
		});
		
		toolbar.getRemove().setCommand(event->{
			ConfirmDialog.show(UI.getCurrent(),"Are you sure?removed data cannot be undelete",dialog->{
				if(dialog.isConfirmed())
				{
					service.delete(grid.getSelectedItems());
					refresh();
				}
			});
		});
	}
}
