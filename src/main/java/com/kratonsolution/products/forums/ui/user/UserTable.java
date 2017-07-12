/**
 * 
 */
package com.kratonsolution.products.forums.ui.user;

import org.vaadin.dialogs.ConfirmDialog;

import com.kratonsolution.products.forums.Springs;
import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.svc.UserService;
import com.kratonsolution.products.forums.ui.Registration;
import com.kratonsolution.products.forums.ui.Table;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;
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
		grid.addColumn(User::getName,new TextRenderer()).setCaption("Name");
		grid.addColumn(User::getEmail,new TextRenderer()).setCaption("Email");
		grid.addColumn(User::getBirthDate,new DateRenderer()).setCaption("Birth Date").setWidthUndefined();
		grid.addColumn(User::getEnabledStatus).setCaption("Enabled").setWidthUndefined();
		grid.addColumn(User::getActivatedStatus,new TextRenderer()).setCaption("Activated").setWidthUndefined();
		grid.setDataProvider(new UserProvider());
		
		toolbar.getAdd().setCommand(event->{
			Registration registration = new Registration();
			registration.addCloseListener(close->{
				grid.setDataProvider(new UserProvider());
			});
			
			UI.getCurrent().addWindow(registration);
		});
		
		toolbar.getRefresh().setCommand(event->{
			grid.setDataProvider(new UserProvider());
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
