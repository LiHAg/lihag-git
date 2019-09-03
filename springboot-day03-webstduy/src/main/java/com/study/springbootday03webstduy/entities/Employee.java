package com.study.springbootday03webstduy.entities;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public class Employee {
    private Integer id;
    private String lastname;
    private Integer gender;
    private String email;
    private Integer departid;

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    private Department department;
    private Date birth;

    public Employee() {
    }

    public Employee(Integer id, String lastname, Integer gender, String email, Department department, Date birth) {
        super();
        this.id = id;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.department = department;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", departid=" + departid +
                ", department=" + department +
                ", birth=" + birth +
                '}';
    }
}
