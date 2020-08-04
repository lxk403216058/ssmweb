package org.example.ssm.dao;

import org.apache.ibatis.annotations.*;
import org.example.ssm.pojo.Employee;

import java.util.List;

//@Mapper
public interface EmployeeMapper {
    String INSERT_FIELDS = "emp_name as 'empName', emp_email as 'empEmail', gender, department_id as 'departmentId'";
    String SELECT_FIELDS = "emp_id as 'empId', " + INSERT_FIELDS;

    /**
     * ##############################删除###################################
     */
    /**
     * 删除员工
     * @param empId
     * @return
     */
    @Delete("delete from tbl_emp where emp_id=#{empId}")
    int deleteEmp(@Param("empId") Integer empId);
    /**
     * ##############################修改###################################
     */
    /**
     * 更改员工信息
     * @param empId
     * @param employee
     * @return
     */
    @Update("update tbl_emp set emp_email=#{employee.empEmail,jdbcType=VARCHAR},gender=#{employee.gender,jdbcType=VARCHAR}," +
            "department_id=#{employee.departmentId,jdbcType=VARCHAR} where  emp_id=#{empId,jdbcType=INTEGER}")
    int updateEmpById(@Param("empId") Integer empId,@Param("employee") Employee employee);

    /**
     * ##############################新增###################################
     */
    /**
     * 新增员工信息
     * @param employee
     * @return
     */
    @Insert("insert into tbl_emp (emp_name, emp_email, gender, department_id) values(#{empName},#{empEmail},#{gender},#{departmentId})")
    int insertEmp(Employee employee);

    /**
     * ##############################查询###################################
     */
    /**
     * 按员工ID查询
     * @param empId
     * @return
     */
    @Select({"select ",SELECT_FIELDS,"from tbl_emp where emp_id=#{empId}"})
    Employee findEmpById(@Param("empId") Integer empId);

    /**
     * 按员工名称查询
     * @param empName
     * @return
     */
    @Select({"select ",SELECT_FIELDS,"from tbl_emp where emp_name=#{empName}"})
    Employee findEmpByName(@Param("empName") String empName);

    /**
     * 查询带有部门信息的员工信息
     * @param empId
     * @return
     */
    /*@Select({"select "," emp_id, emp_name as 'empName', emp_email as 'empEmail', gender, department_id as 'departmentId', dept_id as ‘deptId’," +
            " dept_leader as ‘deptLeader’, dept_name as 'deptName'","from tbl_emp e,tbl_dept d where" +
            " e.department_id=d.dept_id emp_id=#{empId}"})*/
    @Select("select * from tbl_emp where emp_id = #{empId}")
    @Results({
            @Result(id=true,property = "empId",column = "emp_id"),
            @Result(property = "empName",column = "emp_name"),
            @Result(property = "empEmail",column = "emp_email"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "departmentId",column = "department_id"),
            @Result(property = "department",column = "department_id",one = @One(select = "org.example.ssm.dao.DepartmentMapper.findDeptById"))
    })
    Employee findEmpwithDept(@Param("empId") Integer empId);

    /**
     * 分页查询
     * @param offset
     * @param limit
     * @return
     */
    /*@Select({"select "," emp_id as 'empId, emp_name, emp_email, gender, department_id, dept_id, dept_leader, dept_name","from tbl_emp e left join tbl_dept d" +
            " on e.department_id=d.dept_id order by e.emp_id limit #{offset},#{limit}"})*/
    @Select("select * from tbl_emp order by emp_id limit #{offset},#{limit}")
    @Results({
            @Result(id=true,property = "empId",column = "emp_id"),
            @Result(property = "empName",column = "emp_name"),
            @Result(property = "empEmail",column = "emp_email"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "departmentId",column = "department_id"),
            @Result(property = "department",column = "department_id",one = @One(select = "org.example.ssm.dao.DepartmentMapper.findDeptById"))
    })
    List<Employee> findEmpsByLimitAndOffset(@Param("offset") Integer offset,@Param("limit") Integer limit);

    /**
     * 查询总员工数
     * @return
     */
    @Select("select count(*) from tbl_emp")
    int countEmps();
}
