package com.study.springbootday03webstduy.entities;

public class Department {
    private Integer departid;
    private String departmentname;

    public Department() {
    }

    public Department(String departmentname, Integer departid) {
        super();
        this.departmentname = departmentname;
        this.departid = departid;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departid=" + departid +
                ", departmentname='" + departmentname + '\'' +
                '}';
    }
}
