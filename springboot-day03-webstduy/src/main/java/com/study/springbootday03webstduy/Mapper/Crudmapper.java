package com.study.springbootday03webstduy.Mapper;

import com.study.springbootday03webstduy.entities.Department;
import com.study.springbootday03webstduy.entities.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

@Mapper
public interface Crudmapper {
    /**
     *  增、删、查、改
     * @param LiHAg
     * @return
     */
    /**
     * 通过外键departid查询，所有employee的信息以及每个员工所在的部门
     *
     * @ @Result(column="lastname",property="lastname") 配置表内字段与实体类里的所对应的属性
     * column =“表内字段名”  property=“实体类里对应的属性名”
     * 外键为departid
     */
    @Select("select * from employee")
    @Results({@Result(column = "id", property = "id", id = true),//id=true，表示为主键
            @Result(column = "lastname", property = "lastname"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "email", property = "email"),
            @Result(column = "birth", property = "birth"),
            @Result(column = "departid", property = "departid"),
            @Result(column = "departid", property = "department", one = @One(select = "com.study.springbootday03webstduy.Mapper.Crudmapper.getbyid", fetchType = FetchType.EAGER))})
    public List<Employee> getemployees(Employee employee);

    @Results({
            @Result(column = "departid", property = "departid"),
            @Result(column = "departmentname", property = "departmentname")})
    @Select(" select * from department d WHERE d.departid=#{departid};")
    public Department getbyid(Integer departid);

    //查询所有部门（部门列表）
    @Select("select * from department")
    public List<Department> getdepartments(Department department);

    //修改页面查询员工的所有信息

    @Select("select * from employee where id=#{id}")
    @Results({@Result(column = "id", property = "id", id = true),//id=true，表示为主键
            @Result(column = "lastname", property = "lastname"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "email", property = "email"),
            @Result(column = "birth", property = "birth"),
            @Result(column = "departid", property = "departid"),
            @Result(column = "departid", property = "department", one = @One(select = "com.study.springbootday03webstduy.Mapper.Crudmapper.getbyid", fetchType = FetchType.EAGER))})
    public Employee getById(Integer id);

    //查询员工的所以信息
    @Select("select * from department where id=#{id}")
    public Department getByid(Integer id);

    //添加员工功能
    @Insert("insert into employee(lastname,gender,email,departid,birth) values(#{lastname},#{gender},#{email},#{departid},#{birth})")
    public void insertEmp(Employee employee);

    //修改更新功能
    @Update("update employee set lastname=#{lastname},gender=#{gender},email=#{email},departid=#{departid},birth=#{birth} where id=#{id}")
    public void updateEmployee(@Param(value = "lastname") String lastname, @Param(value = "gender") Integer gender, @Param(value = "email") String email, @Param(value = "departid") Integer departid, @Param(value = "birth") Date birth, @Param(value = "id") Integer id);

    //删除功能
    @Delete("delete from employee where id=#{id}")
    public void deleteEmployee(Integer id);

}
