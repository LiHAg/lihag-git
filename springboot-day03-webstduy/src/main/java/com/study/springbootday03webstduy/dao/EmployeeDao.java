package com.study.springbootday03webstduy.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.study.springbootday03webstduy.entities.Department;
import com.study.springbootday03webstduy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

//    private static Map<Integer, Employee> employees = null;
//
//    @Autowired
//    private DepartmentDao departmentDao;
//
//    static {
//        employees = new HashMap<Integer, Employee>();
//
//        employees.put(1001, new Employee(1001, "E-AA", 1, "aa@163.com", new Department("D-AA", 101), new Date()));
//        employees.put(1002, new Employee(1002, "E-BB", 1, "bb@163.com", new Department("D-BB", 102), new Date()));
//        employees.put(1003, new Employee(1003, "E-CC", 0, "cc@163.com", new Department("D-CC", 103), new Date()));
//        employees.put(1004, new Employee(1004, "E-DD", 0, "dd@163.com", new Department("D-DD", 104), new Date()));
//        employees.put(1005, new Employee(1005, "E-EE", 1, "ee@163.com", new Department("D-EE", 105), new Date()));
//    }
//
//    private static Integer initId = 1006;
//
//    public void save(Employee employee) {
//        if (employee.getId() == null) {
//            employee.setId(initId++);
//        }
//
//        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
//        employees.put(employee.getId(), employee);
//    }
//
//    public Collection<Employee> getAll() {
//        return employees.values();
//    }
//
//    public Employee get(Integer id) {
//        return employees.get(id);
//    }
//
//    public void delete(Integer id) {
//        employees.remove(id);
//    }
}
