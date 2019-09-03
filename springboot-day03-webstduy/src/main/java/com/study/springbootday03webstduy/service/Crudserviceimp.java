package com.study.springbootday03webstduy.service;

import com.study.springbootday03webstduy.Mapper.Crudmapper;
import com.study.springbootday03webstduy.entities.Department;
import com.study.springbootday03webstduy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Crudserviceimp implements Crudservice {
    @Autowired
    Crudmapper crudmapper;

    @Override
    public List<Employee> findlist(Employee employee) {
        return crudmapper.getemployees(employee);
    }

    @Override
    public List<Department> finddepartments(Department department) {
        return crudmapper.getdepartments(department);
    }

    @Override
    public Employee findById(Integer id) {
        return crudmapper.getById(id);
    }

    @Override
    public Department getdepartmentsbyid(Integer id) {
        return crudmapper.getByid(id);
    }

    @Override
    public void update(String lastname, Integer gender, String email, Integer departid, Date birth, Integer id) {
        crudmapper.updateEmployee(lastname, gender, email, departid, birth, id);
    }

    @Override
    public void delete(Integer id) {
        crudmapper.deleteEmployee(id);
    }

    @Override
    public void insertb(Employee employee) {
        crudmapper.insertEmp(employee);
    }
}
