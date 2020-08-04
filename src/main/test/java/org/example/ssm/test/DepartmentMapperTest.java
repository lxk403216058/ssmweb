package org.example.ssm.test;

import org.example.ssm.dao.DepartmentMapper;
import org.example.ssm.pojo.Department;
import org.example.ssm.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml"})
public class DepartmentMapperTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void insertTest(){
        Department department =new Department(null,"刘六","办公室");
        int res = departmentService.insertDept(department);
        System.out.println(res);
    }

    @Test
    public void findAllTest(){
        List<Department> departmentList = departmentMapper.findAll();
        for (Department department : departmentList) {
            System.out.println(department.toString());
        }
        System.out.println(departmentMapper.countDepts());
    }
    @Test
    public void deleteTest(){
        System.out.println(departmentMapper.deleteDeptById(9));
    }
    @Test
    public void findTest(){
        System.out.println(departmentMapper.findDeptById(1).toString());
        System.out.println(departmentMapper.findByDeptName("测试部"));
        System.out.println(departmentMapper.findByDepLeader("zhangsan"));
        List<Department> departmentList = departmentMapper.findDeptsByLimitAndOfferset(0, 5);
        for (Department department : departmentList) {
            System.out.println(department.toString());
        }
    }
    @Test
    public void updateTest(){
        Department department = new Department(null,"zhangsan","java");
        System.out.println(departmentMapper.updateById(1, department));
    }
}
