package org.example.ssm.service;

import org.example.ssm.pojo.Department;

import java.util.List;

public interface DepartmentService {
    int insertDept(Department department);
    List<Department> findAllDept();
    int countDept();
    int deleteDept(Integer deptId);
    Department findByDeptId(Integer deptId);
    Department findByDeptName(String deptName);
    Department findByDeptLeader(String deptLeader);
    List<Department> findDeptsByLimitAndOffset(Integer offset,Integer limit);
    int updateByDeptId(Integer id,Department department);
}
