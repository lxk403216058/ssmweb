package org.example.ssm.service;

import org.example.ssm.dao.EmployeeMapper;
import org.example.ssm.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteEmp(Integer id) {
        return employeeMapper.deleteEmp(id);
    }

    @Override
    public int updateEmp(Integer id, Employee employee) {
        return employeeMapper.updateEmpById(id,employee);
    }

    @Override
    public int insertEmp(Employee employee) {
        return employeeMapper.insertEmp(employee);
    }

    @Override
    public Employee findEmpById(Integer id) {
        return findEmpById(id);
    }

    @Override
    public Employee findEmpByName(String name) {
        return findEmpByName(name);
    }

    @Override
    public Employee findEmpwithDept(Integer id) {
        return employeeMapper.findEmpwithDept(id);
    }

    @Override
    public List<Employee> findEmpsByLimitAndOffset(Integer offset, Integer limit) {
        return employeeMapper.findEmpsByLimitAndOffset(offset,limit);
    }

    @Override
    public int countEmps() {
        return employeeMapper.countEmps();
    }
}
