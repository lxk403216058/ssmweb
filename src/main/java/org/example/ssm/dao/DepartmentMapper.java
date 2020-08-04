package org.example.ssm.dao;

import org.apache.ibatis.annotations.*;
import org.example.ssm.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    String SELECT_FIELDS = "dept_id as 'deptId', " +
            "dept_name as 'deptName', " +
            "dept_leader as 'deptLeader'";

    /**
     *#############################更改###################################
     */
    @Update("update tbl_dept set dept_name=#{department.deptName, jdbcType=VARCHAR},dept_leader=#{department.deptLeader, jdbcType=VARCHAR} where dept_id=#{deptId}")
    int updateById(@Param("deptId") Integer deptId,@Param("department") Department department);


    /**
     *#############################删除###################################
     */
    /**
     * 根据Id删除部门
     * @param deptId 部门编号
     * @return 删除是否成功
     */
    @Delete("delete from tbl_dept where dept_id = #{deptId}")
    int deleteDeptById(@Param("deptId") int deptId);
    /**
     *#############################插入###################################
     */
    @Insert("insert into tbl_dept (dept_name, dept_leader) values(#{department.deptName},#{department.deptLeader})")
    int insertDept(@Param("department") Department department);

    /**
     *#############################查询###################################
     */

    /**
     * 按部门ID查询
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Select({"select",SELECT_FIELDS,"from tbl_dept where dept_id=#{deptId}"})
    Department findDeptById(@Param("deptId") Integer deptId);
    /**
     * 按部门名称查询
     * @param deptLeader 部门领导
     * @return
     */
    @Select({"select",SELECT_FIELDS,"from tbl_dept where dept_leader=#{deptLeader}"})
    Department findByDepLeader(@Param("deptLeader") String deptLeader);
    /**
     * 按部门名称查询
     * @param deptName 部门名称
     * @return
     */
    @Select({"select",SELECT_FIELDS,"from tbl_dept where dept_name=#{deptName}"})
    Department findByDeptName(@Param("deptName") String deptName);
    /**
     * 查询所有的部门
     * @return
     */
    @Select({"select",SELECT_FIELDS ,"from tbl_dept"})
    List<Department> findAll();

    @Select({"select",SELECT_FIELDS,"from tbl_dept limit #{offset},#{limit}"})
    List<Department> findDeptsByLimitAndOfferset(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Select("select count(*) from tbl_dept")
    int countDepts();

}
