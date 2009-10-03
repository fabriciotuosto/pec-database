package org.pec.db.ui.fields;

import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

class CuilFieldFactory extends DefaultFieldFactory{

	private static final long serialVersionUID = 1L;
	public final static String HANDLE = "cuil";
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Field field =  super.createField(item, propertyId, uiContext);
		field.addValidator(new CuilValidator());
		field.setRequired(true);
		((TextField) field).setNullRepresentation("");
		return field;
	}
	
	private static class CuilValidator implements Validator{
		private static final long serialVersionUID = 1L;
		@Override
		public boolean isValid(Object value) {
			return String.valueOf(value).matches("\\d{2}-\\d{8}-\\d{1}");
		}

		@Override
		public void validate(Object value) throws InvalidValueException {
			if(!isValid(value)){
				throw new InvalidValueException("El Formato del CUIL es incorrecto");
			}
		}
		
	}
}
