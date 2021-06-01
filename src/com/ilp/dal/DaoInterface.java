package com.ilp.dal;

import java.util.ArrayList;

public interface DaoInterface<T> {
	
	ArrayList<T> findAll();
	
	T findById(int id);
	
	void update(T obj);
	
	T save(T obj);
	
	void delete(int id);

}
