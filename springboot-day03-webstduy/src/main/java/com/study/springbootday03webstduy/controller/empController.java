package com.study.springbootday03webstduy.controller;

import com.study.springbootday03webstduy.dao.EmployeeDao;
import com.study.springbootday03webstduy.entities.Department;
import com.study.springbootday03webstduy.entities.Employee;
import com.study.springbootday03webstduy.service.Crudservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class empController {
    //    @Autowired
//    EmployeeDao employeeDao;
    @Autowired
    Crudservice crudservice;

    @GetMapping("/emps")
    public String list(Model model, Employee e) {
//        Collection<Employee> all = employeeDao.getAll();
//        model.addAttribute("emps", all);
        List<Employee> list = crudservice.findlist(e);
        model.addAttribute("emps", list);
        return "emp/list";
    }
}
