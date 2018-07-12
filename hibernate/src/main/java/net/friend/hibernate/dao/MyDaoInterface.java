package net.friend.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface MyDaoInterface<T, Id extends Serializable> {

  public void persist(T entity);

  List<T> findByName(String name);

  void delete(T entity);
}
