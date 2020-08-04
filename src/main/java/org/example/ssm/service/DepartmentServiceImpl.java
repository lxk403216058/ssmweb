package org.example.ssm.service;

import org.example.ssm.dao.DepartmentMapper;
import org.example.ssm.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public int insertDept(Department department) {
        return departmentMapper.insertDept(department);
    }

    @Override
    public List<Department> findAllDept() {
        return departmentMapper.findAll();
    }

    @Override
    public int countDept() {
        return departmentMapper.countDepts();
    }

    @Override
    public int deleteDept(Integer deptId) {
        return departmentMapper.deleteDeptById(deptId);
    }

    @Override
    public Department findByDeptId(Integer deptId) {
        return departmentMapper.findDeptById(deptId);
    }

    @Override
    public Department findByDeptName(String deptName) {
        return departmentMapper.findByDeptName(deptName);
    }

    @Override
    public Department findByDeptLeader(String deptLeader) {
        return departmentMapper.findByDepLeader(deptLeader);
    }

    @Override
    public List<Department> findDeptsByLimitAndOffset(Integer offset, Integer limit) {
        return departmentMapper.findDeptsByLimitAndOfferset(offset,limit);
    }

    @Override
    public int updateByDeptId(Integer id, Department department) {
        return departmentMapper.updateById(id,department);
    }
}
