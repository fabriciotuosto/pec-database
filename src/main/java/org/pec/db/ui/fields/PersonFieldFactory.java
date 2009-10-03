package org.pec.db.ui.fields;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class PersonFieldFactory  extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	private Map<String,DefaultFieldFactory> handlers;
	private Map<String,String> captions = ImmutableMap.of("lastName","Apellido",
			                                              "firstName","Nombre",
			                                              "birthDate","Fecha de nacimiento");
	@Inject
	public PersonFieldFactory(DniFieldFactory field,
			                  CuilFieldFactory field2,
			                  NeighborhoodFieldFactory field3,
			                  StatusFieldFactory field4) {
		handlers = ImmutableMap.
		of(DniFieldFactory.HANDLED, field,
		   CuilFieldFactory.HANDLE, field2,
		   NeighborhoodFieldFactory.HANDLED, field3,
		   StatusFieldFactory.HANDLED, field4);
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		DefaultFieldFactory handler = handlers.get(String.valueOf(propertyId));
		Field field = null;
		if (handler != null){
			field = handler.createField(item, propertyId, uiContext);
		}else{
			field = defaultHandle(item, propertyId, uiContext);
		}
		return field;
	}
	
	public Field defaultHandle(Item item, Object propertyId, Component uiContext) {
		Field field = super.createField(item, propertyId, uiContext);
		field.setCaption(captions.get(String.valueOf(propertyId)));
		if(field instanceof TextField){
			((TextField) field).setNullRepresentation("");
		}
    	return field;
	}
	

}
