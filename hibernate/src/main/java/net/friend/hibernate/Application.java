package net.friend.hibernate;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.friend.hibernate.model.Employee;
import net.friend.hibernate.service.EmployeeService;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Slf4j
public class Application {

  public static void main(String[] args) throws MappingException {
//    basicUsage();
    advanceUsage();
  }

  private static void advanceUsage(){
    EmployeeService employeeService = new EmployeeService();
    List<Employee> employees = employeeService.findByName("Joe");
    log.info("Employees found: " + employees.size());
    for(Employee employee: employees){
      employeeService.delete(employee);
      log.info("Deleted " + employee);
    }

    Employee employee = new Employee();
    employee.setName("Joe");
    employee.setContact("joe@gmail.com");
    employeeService.persist(employee);

    List<Employee> employeeList = employeeService.findByName("Joe");
    log.info("List all employees: " + employeeList);
  }

  private static void basicUsage(){
    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction tx = session.getTransaction();
    try {
      tx.begin();
      Query query = session.createQuery("from Employee where name='Joe'");
      List empList = query.list();
      log.info("Employees found: " + empList.size());
      for(Object emp: empList) {
        session.delete(emp);
        log.info("Deleted " + emp);
      }
      tx.commit();

      log.info("Create new employee");
      tx = session.getTransaction();
      tx.begin();
      Employee emp = new Employee();
      emp.setName("Joe");
      session.saveOrUpdate(emp);
      tx.commit();

      query = session.createQuery("from Employee where name='Joe'");
      log.info("List all employees: " + query.list());
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;

    } finally {
      session.close();
    }
  }
}
