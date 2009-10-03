package org.pec.db.ui;

import java.util.Random;

import org.pec.db.entities.Person;
import org.pec.db.entities.Status;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.data.util.BeanItemContainer;

@SessionScoped
public class PersonContainer extends BeanItemContainer<Person> {

	private static final long serialVersionUID = 1L;

//	public PersonContainer() {
//		super(Person.class);
//	}

	@Inject @Deprecated
	public PersonContainer() {
		super(Person.class);
		final String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
				"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
				"Lisa", "Marge" };
		final String[] lnames = { "Smith", "Gordon", "Simpson", "Brown",
				"Clavel", "Simons", "Verne", "Scott", "Allison", "Gates",
				"Rowling", "Barks", "Ross", "Schneider", "Tate" };
		final String dnis[] = { "29000000", "11222333", "23000444"};
		final Status[] status = Status.values();

		Random r = new Random(0);
		for (int i = 0; i < 10; i++) {
			Person p = new Person();
			p.setFirstName(fnames[r.nextInt(fnames.length)]);
			p.setLastName(lnames[r.nextInt(lnames.length)]);
			String dni = dnis[r.nextInt(dnis.length)];
			p.setDni(dni);
			p.setCuil("20-"+dni+"-0");
			p.setStatus(status[r.nextInt(status.length)]);
			addItem(p);
		}
	}

}
