package com.study.springbootday03webstduy.controller;

import com.study.springbootday03webstduy.dao.DepartmentDao;
import com.study.springbootday03webstduy.dao.EmployeeDao;
import com.study.springbootday03webstduy.entities.Department;
import com.study.springbootday03webstduy.entities.Employee;
import com.study.springbootday03webstduy.service.Crudservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class addCustomerController {
    //    @Autowired
//    DepartmentDao departmentDao;
//    @Autowired
//    EmployeeDao employeeDao;
    @Autowired
    Crudservice crudservice;

    /*点击添加，进入添加页面*/
    @GetMapping("/emp")
    public String add(Model model, Department department) {
//        Collection<Department> departments = departmentDao.getDepartments();
//        model.addAttribute("depts", departments);
        List<Department> list2 = crudservice.finddepartments(department);
        model.addAttribute("depts", list2);
        return "emp/add";
    }

    /*点击添加，保存信息成功后，跳到员工列表页面*/
    @PostMapping("/emp")
    public String addsave(Employee employee) {
//        employeeDao.save(employee);
//        System.out.println(employee);
        crudservice.insertb(employee);
        return "redirect:/emps";
    }

    //点击编辑，来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model, Department department) {
//        Employee employee = employeeDao.get(id);
////        model.addAttribute("emp", employee);
////
////        //页面要显示所有的部门列表
////        Collection<Department> departments = departmentDao.getDepartments();
////        model.addAttribute("depts", departments);
////        //回到修改页面(add是一个修改添加二合一的页面);
        Employee employee = crudservice.findById(id);
        model.addAttribute("emp", employee);
        System.out.println("employee数据:" + employee);
        List list3 = crudservice.finddepartments(department);
        model.addAttribute("depts", list3);
        System.out.println("depts数据:" + list3);
        return "emp/add";
    }

    //员工修改；需要提交员工id；点击修改，保存员工信息，返回员工列表页面
    @PutMapping("/emp")
    public String updateEmployee(String lastname,
                                 Integer gender,
                                 String email,
                                 Integer departid,
                                 Date birth,
                                 Integer id) {
//        employeeDao.save(employee);
        crudservice.update(lastname, gender, email, departid, birth, id);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id) {
//        employeeDao.delete(id);
        crudservice.delete(id);
        return "redirect:/emps";
    }
}
