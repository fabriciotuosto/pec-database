package org.pec.db;

import java.util.Collections;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.google.inject.Singleton;

@Singleton
public class Repository {

	private final ODB odb;
	
	public Repository() throws Exception {
		odb = ODBFactory.open("pecdb.odb");
	}
	
	public <E> E save(E entity){
		E _entity = entity;
		try{
			odb.store(_entity);
			odb.commit();
		}catch(Exception e){
			throw new Error(e);
		}
		return _entity;
	}
	
	public <E> void remove(E entity){
		try{
			odb.delete(entity);
			odb.commit();
		}catch(Exception e){
			throw new Error(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> getAllByClass(Class<E> type){
		List<E> result = Collections.emptyList();
		try{
			result = (List<E>) odb.getObjects(new CriteriaQuery(type));
		}catch(Exception e){
			throw new Error(e);
		}
		return result; 
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> getByQuery(IQuery query){
		List<E> result = Collections.emptyList();
		try{
			result = (List<E>) odb.getObjects(query);
			odb.commit();
		}catch(Exception e){
			throw new Error(e);
		}
		return result; 
	}
}
