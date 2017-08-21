/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import org.vaadin.dialogs.ConfirmDialog;

import com.kratonsolution.products.forums.common.DateUtil;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.dm.TribeStatus;
import com.kratonsolution.products.forums.dm.TribeStatusType;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.ui.Table;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.TextRenderer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeTable extends Table<Tribe>
{
	private TribeService service = Springs.get(TribeService.class);
	
	public TribeTable()
	{
		super();
		
		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addColumn(Tribe::getLastStatus,new TextRenderer()).setCaption("Status").setExpandRatio(2);
		grid.addColumn(Tribe::getName,new TextRenderer()).setCaption("Name").setExpandRatio(15);
		grid.addColumn(Tribe::getDescription,new HtmlRenderer()).setCaption("Description").setExpandRatio(15);
		grid.addColumn(Tribe::getGoal,new HtmlRenderer()).setCaption("Description").setExpandRatio(15);
		grid.addColumn(Tribe::getCreator,new TextRenderer()).setCaption("Creator").setExpandRatio(1);
		grid.setDataProvider(new TribeProvider());
		
		toolbar.getAdd().setCommand(event->{});
		
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
		
		grid.addItemClickListener(selected->{
			UI.getCurrent().addWindow(new Show(selected.getItem()));
		});
	}
	
	private class Show extends Window
	{
		public Show(Tribe tribe)
		{
			setCaption(tribe.getName().toUpperCase());
			setIcon(VaadinIcons.BOOK);
			setWidth("55%");
			setHeight("80%");
			setClosable(true);
			setResizable(false);
			center();
			setModal(true);
			
			GridLayout layout = new GridLayout(1,2);
			layout.setWidth("100%");
			layout.setHeight("100%");
			layout.setMargin(true);
			layout.setSpacing(true);
			
			TextField title = new TextField("Name");
			title.setWidth("100%");
			title.setEnabled(false);
			title.setValue(tribe.getName());
			
			RichTextArea description = new RichTextArea("Description");
			description.setWidth("100%");
			description.setEnabled(false);
			description.setValue(tribe.getDescription());
			
			RichTextArea goal = new RichTextArea("Goal");
			goal.setWidth("100%");
			goal.setEnabled(false);
			goal.setValue(tribe.getGoal());
			
			Button button = new Button("APPROVE");
			button.setWidth("50%");
			button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			button.addClickListener(click->{
				TribeStatus status = new TribeStatus();
				status.setCreatedTime(DateUtil.now());
				status.setType(TribeStatusType.APPROVED);
				
				tribe.setLastStatus(status);
				tribe.getStatuses().add(status);
				
				service.approve(tribe);
			
				UI.getCurrent().removeWindow(Show.this);

				refresh();
			});
			
			if(!tribe.getLastStatus().getType().equals(TribeStatusType.CREATED))
				button.setEnabled(false);
				
			FormLayout form = new FormLayout();
			form.setWidth("95%");
			form.addComponent(title);
			form.addComponent(description);
			form.addComponent(goal);
			
			layout.addComponent(form);
			layout.addComponent(button);
			layout.setColumnExpandRatio(1, 4f);
			layout.setColumnExpandRatio(2, 1f);
			layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
			layout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
			
			setContent(layout);
		}
	}
}
