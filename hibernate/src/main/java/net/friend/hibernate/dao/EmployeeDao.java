package net.friend.hibernate.dao;

import java.util.List;
import lombok.Data;
import net.friend.hibernate.model.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Data
public class EmployeeDao implements MyDaoInterface<Employee, Long> {

  private Session currentSession;

  private Transaction currentTransaction;

  public Session openCurrentSession() {
    currentSession = getSessionFactory().openSession();
    return currentSession;
  }

  public Session openCurrentSessionWithTransaction() {
    currentSession = getSessionFactory().openSession();
    currentTransaction = currentSession.beginTransaction();
    return currentSession;
  }

  public void closeCurrentSession() {
    currentSession.close();
  }

  public void closeCurrentSessionWithTransaction() {
    currentTransaction.commit();
    currentSession.close();
  }

  private static SessionFactory getSessionFactory() {
    Configuration configuration = new Configuration().configure();
//    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//        .applySettings(configuration.getProperties());
//    SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    return sessionFactory;
  }

  public void persist(Employee entity) {
    getCurrentSession().save(entity);
  }

  public List<Employee> findByName(String name) {
    List<Employee> books = (List<Employee>) getCurrentSession().createQuery("from Employee where name='" + name + "'").list();
    return books;
  }

  public void delete(Employee entity) {
    getCurrentSession().delete(entity);
  }
}
