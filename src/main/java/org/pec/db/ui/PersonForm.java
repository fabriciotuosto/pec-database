package org.pec.db.ui;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PersonForm extends Form {

	private static final long serialVersionUID = 1L;
	private final ComboBox cities = new ComboBox("Neighborhood");
	private HorizontalLayout footer;
	private Button save = new Button("Guardar");
	private Button cancel = new Button("Cancelar");
	private Button edit = new Button("Editar");

	public PersonForm() {

		/* Allow the user to enter new cities */
		cities.setNewItemsAllowed(true);
		/* We do not want to use null values */
		cities.setNullSelectionAllowed(false);
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
		if (newDataSource != null) {
			List<String> orderedProperties = Arrays
					.asList(PersonList.NATURAL_COL_ORDER);
			super.setItemDataSource(newDataSource, orderedProperties);
			setReadOnly(true);
			getFooter().setVisible(true);
		} else {
			super.setItemDataSource(null);
			getFooter().setVisible(false);
		}
	}

	@SuppressWarnings("serial")
	private void customizeButtons(final PersonForm form) {
		save.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				form.commit();
				form.setReadOnly(true);
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
				form.discard();
				form.setReadOnly(true);
			}
		});
	}
}
