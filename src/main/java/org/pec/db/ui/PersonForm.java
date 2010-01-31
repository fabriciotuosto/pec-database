package org.pec.db.ui;

import java.util.Arrays;
import java.util.List;

import org.pec.db.Repository;
import org.pec.db.entities.Person;
import org.pec.db.ui.actions.ShowNotification;
import org.pec.db.ui.fields.PersonFieldFactory;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SessionScoped
public class PersonForm extends Form {

	private static final long serialVersionUID = 1L;
	private boolean isNewPerson = false;
	private Person newPerson;
	private HorizontalLayout footer;
	private Button save = new Button("Guardar");
	private Button cancel = new Button("Cancelar");
	private Button edit = new Button("Editar");
	private final ShowNotification showNotificationCmd;
	private final Repository repository;
	
	@Inject
	public PersonForm(Repository repository,PersonFieldFactory fieldFactory,ShowNotification showNotification) {
		this.repository = repository;
		this.showNotificationCmd = showNotification;
		setFormFieldFactory(fieldFactory);
		setWriteThrough(false);
		footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		footer.addComponent(edit);
		setFooter(footer);
		footer.setVisible(false);
		customizeButtons(this);
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		save.setVisible(!readOnly);
		cancel.setVisible(!readOnly);
		edit.setVisible(readOnly);
	}

	public void setItemDataSource(Item newDataSource) {
		isNewPerson = false;
		if (newDataSource != null) {
			List<String> orderedProperties = Arrays
					.asList(PersonList.NATURAL_COL_ORDER);
			setItemDataSource(newDataSource, orderedProperties);
			setReadOnly(true);
			getFooter().setVisible(true);
		} else {
			super.setItemDataSource(null);
			getFooter().setVisible(false);
		}
	}

	public void addPerson(){
		newPerson = new Person();
		BeanItem person = new BeanItem(newPerson);
		setItemDataSource(person);
		isNewPerson = true;
		setReadOnly(false);
	}
	
	@SuppressWarnings("serial")
	private void customizeButtons(final PersonForm form) {
		save.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String message = "Persona ";
				String action  = "actualizada";
				form.commit();
				Person p = newPerson;
				if(isNewPerson){
					action = "creada";
					setItemDataSource(new BeanItem(newPerson));
				}else{
					p = (Person) ((BeanItem)getItemDataSource()).getBean();
				}
				repository.save(p);
				form.setReadOnly(true);
				showNotificationCmd.show(message+action);
			}
		});

		edit.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				setReadOnly(false);
			}
		});
		cancel.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				isNewPerson = false;
				form.discard();
				form.setReadOnly(true);
				form.setItemDataSource(null);
			}
		});
	}
}
