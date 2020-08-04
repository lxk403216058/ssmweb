package org.example.ssm.service;

import org.example.ssm.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    int deleteEmp(Integer id);
    int updateEmp(Integer id, Employee employee);
    int insertEmp(Employee employee);
    Employee findEmpById(Integer id);
    Employee findEmpByName(String name);
    Employee findEmpwithDept(Integer id);
    List<Employee> findEmpsByLimitAndOffset(Integer offset, Integer limit);
    int countEmps();
}
