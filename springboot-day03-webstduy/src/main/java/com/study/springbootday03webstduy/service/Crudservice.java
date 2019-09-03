package com.study.springbootday03webstduy.service;

import com.study.springbootday03webstduy.entities.Department;
import com.study.springbootday03webstduy.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface Crudservice {
    //员工信息列表
    public List<Employee> findlist(Employee employee);

    //部门列表
    public List<Department> finddepartments(Department department);

    //通过id查询员工信息
    public Employee findById(Integer id);

    //通过id查询部门信息
    public Department getdepartmentsbyid(Integer id);

    //修改更新
    public void update(String lastname, Integer gender, String email, Integer departid, Date birth, Integer id);

    //删除
    public void delete(Integer id);

    //添加
    public void insertb(Employee employee);
}
