package org.example.ssm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.example.ssm.pojo.Employee;
import org.example.ssm.service.EmployeeService;
import org.example.ssm.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/hrms/emp")
@Api(value = "工人信息Controller", tags = {"工人信息操作接口"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 删除员工
     * @param empId
     * @return
     */
    @RequestMapping(value = "/deleteEmp/{empId}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "根据ID删除员工信息",httpMethod = "DELETE",notes = "根据ID删除员工信息")
    public JsonMsg deleteEmp(@ApiParam(name = "empid", value = "员工ID",required = true)@PathVariable("empId") Integer empId){
        int res = 0;
        if (empId > 0){
            res = employeeService.deleteEmp(empId);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("emp_delete_error","员工删除失败");
        }
        return JsonMsg.success();
    }

    /**
     * 更改员工信息
     * @param empId
     * @param employee
     * @return
     */
    @RequestMapping(value = "/updateEmp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "根据ID修改员工信息",httpMethod = "PUT",notes = "根据ID修改员工信息")
    public JsonMsg updateEmp(@ApiParam(name = "empid", value = "员工ID",required = true)@PathVariable("empId") Integer empId, Employee employee){
        int res = employeeService.updateEmp(empId,employee);
        if (res != 1){
            return JsonMsg.fail().addInfo("emp_update_error", "更改异常");
        }
        return  JsonMsg.success();
    }

    /**
     * 查询员工是姓名是否重复
     * @param empName
     * @return
     */
    @RequestMapping(value = "/checkEmpExists", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据姓名查询员工信息是否重复",httpMethod = "GET",notes = "根据姓名查询员工信息是否重复")
    public JsonMsg checkEmpExists(@ApiParam(name = "empName", value = "员工姓名",required = true)@RequestParam("empName") String empName){
        //对输入的姓名与邮箱格式进行验证
        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regName)){
            return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Employee employee = employeeService.findEmpByName(empName);
        if (employee != null){
            return JsonMsg.fail().addInfo("name_reg_error", "用户名重复");
        }
        return JsonMsg.success();
    }

    /**
     * 新增记录后，查询最新的页数
     * @return
     */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取总页数",httpMethod = "GET",notes = "获取总页数")
    public JsonMsg getTotalPages(){
        int totalItems = employeeService.countEmps();
        int temp = totalItems/5;
        int totalPages = (totalItems%5 == 0) ? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    /**
     * 增加员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "增加员工信息",httpMethod = "POST",notes = "增加员工信息")
    public JsonMsg addEmp(@ApiParam(name = "employee", value = "员工信息",required = true)Employee employee){
        int res = employeeService.insertEmp(employee);
        if (res == 1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    /**
     * 根据ID查询员工信息
     * @param empId
     * @return
     */
    @RequestMapping(value = "/getEmpById/{empId}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据ID查询员工信息",httpMethod = "PUT",notes = "根据ID查询员工信息")
    public JsonMsg getEmpById(@ApiParam(name = "empid", value = "员工ID",required = true)@Param("empId") Integer empId){
        Employee employee = employeeService.findEmpById(empId);
        if (employee != null){
            return JsonMsg.success().addInfo("employee",employee);
        }else {
            return JsonMsg.fail();
        }
    }

    /**
     * 查询
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页查询",httpMethod = "PUT",notes = "分页查询")
    public ModelAndView getEmpList(@ApiParam(name = "pageNo", value = "页码",required = true)@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("employeePage");
        int limit = 5;
        int offset = (pageNo - 1)*limit;
        List<Employee> employees = employeeService.findEmpsByLimitAndOffset(offset,limit);
        int totalItems = employeeService.countEmps();
        int temp = totalItems/5;
        int totalPages = (totalItems%5 == 0) ? temp : temp+1;
        int curPage = pageNo;
        mv.addObject("employees", employees)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }

}
