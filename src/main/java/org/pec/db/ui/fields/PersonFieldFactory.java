package org.pec.db.ui.fields;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class PersonFieldFactory  extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	private Map<String,DefaultFieldFactory> handlers = ImmutableMap.
								of(DniFieldFactory.HANDLED, new DniFieldFactory(),
								   CuilFieldFactory.HANDLE, new CuilFieldFactory(),
								   NeighborhoodFieldFactory.HANDLED, new NeighborhoodFieldFactory(),
								   StatusFieldFactory.HANDLED,new StatusFieldFactory());
	private Map<String,String> captions = ImmutableMap.of("lastName","Apellido",
			                                              "firstName","Nombre",
			                                              "birthDate","Fecha de nacimiento");
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
