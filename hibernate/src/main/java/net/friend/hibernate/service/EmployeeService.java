package net.friend.hibernate.service;

import java.util.List;
import net.friend.hibernate.dao.EmployeeDao;
import net.friend.hibernate.model.Employee;

public class EmployeeService {

  private EmployeeDao employeeDao;

  public EmployeeService() {
    this.employeeDao = new EmployeeDao();
  }

  public void persist(Employee entity){
    //business

    employeeDao.openCurrentSessionWithTransaction();
    employeeDao.persist(entity);
    employeeDao.closeCurrentSessionWithTransaction();
  }

  public List<Employee> findByName(String name){
    employeeDao.openCurrentSession();
    List<Employee> employees = employeeDao.findByName(name);
    employeeDao.closeCurrentSession();
    return employees;
  }

  public void delete(Employee entity) {
    employeeDao.openCurrentSessionWithTransaction();
    employeeDao.delete(entity);
    employeeDao.closeCurrentSessionWithTransaction();
  }
}
