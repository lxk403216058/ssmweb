package org.example.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.example.ssm.dao.EmployeeMapper;
import org.example.ssm.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml"})
public class EmployeeMapperTest {
    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 批量插入
     */
    @Test
    public void insertEmpsBatchTest(){
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 1; i < 200; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5);
            employeeMapper.insertEmp(new Employee(i, "name_"+uid, uid+"@qq.com",  i%2==0? "F":"M", i%6+1));

        }
    }

    @Test
    public void updateTest(){
        Employee employee = new Employee(null,"张三","2545591@qq.com","M",2);
        System.out.println(employeeMapper.updateEmpById(1,employee));
    }
    @Test
    public void deleteTest(){
        System.out.println(employeeMapper.deleteEmp(199));
    }
    @Test
    public void selectTest(){
       /* System.out.println(employeeMapper.countEmps());
        System.out.println(employeeMapper.findEmpById(1));
        System.out.println(employeeMapper.findEmpByName("name_85fbd"));
        System.out.println(employeeMapper.findEmpwithDept(1));*/
        List<Employee> employeeList = employeeMapper.findEmpsByLimitAndOffset(1, 5);
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
        }
    }
}
