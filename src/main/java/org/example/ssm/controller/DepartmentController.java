package org.example.ssm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.example.ssm.pojo.Department;
import org.example.ssm.service.DepartmentService;
import org.example.ssm.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/hrms/dept")
@Api(value = "部门信息Controller", tags = {"部门信息操作接口"})
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/delDept/{deptId}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "根据ID删除部门",httpMethod = "DELETE",notes = "根据ID删除部门")
    public JsonMsg deletDept(@ApiParam(name = "deptid", value = "部门ID",required = true) @PathVariable("deptId") Integer deptId){
        int res = 0;
        if (deptId > 0){
            res = departmentService.deleteDept(deptId);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("del_dept_error", "删除异常");
        }
        return JsonMsg.success();
    }

    /**
     * 更改部门
     * @return
     */
    @RequestMapping(value = "/updateDept/{deptId}",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "根据ID修改部门信息",httpMethod = "PUT",notes = "根据ID修改部门信息")
    public JsonMsg updateDept(@ApiParam(name = "部门ID", value = "部门ID",required = true)@PathVariable("deptId") Integer deptId, Department department){
        int res = 0;
        if (deptId > 0){
            res = departmentService.updateByDeptId(deptId,department);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("update_dept_error", "更改异常");
        }
        return JsonMsg.success();
    }

    /**
     * 新增部门
     * @param department
     * @return
     */
    @RequestMapping(value = "/addDept", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "增加部门",httpMethod = "PUT",notes = "增加部门")
    public JsonMsg addDept(@ApiParam(name = "部门", value = "部门",required = true)Department department){
        int res = departmentService.insertDept(department);
        if (res != 1){
            return JsonMsg.fail().addInfo("add_dept_error", "添加异常");
        }
        return JsonMsg.success();
    }

    /**
     * 查询部门信息的总页码数
     * @return
     */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询部门信息的总页码数",httpMethod = "GET",notes = "查询部门信息的总页码数")
    public JsonMsg getTotalPages(){
        //每页显示的记录数
        int limit = 5;
        //总记录数
        int totalItems = departmentService.countDept();
        int temp = totalItems/limit;
        int totalPages = (totalItems%limit == 0)?temp:temp+1;

        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    /**
     * 按部门id查询
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/getDeptById/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按部门id查询部门信息",httpMethod = "GET",notes = "按部门id查询部门信息")
    public JsonMsg getDeptById(@ApiParam(name = "部门ID", value = "部门ID",required = true)@PathVariable("deptId") Integer deptId){
        Department department = null;
        if (deptId > 0){
            department = departmentService.findByDeptId(deptId);
        }
        if (department != null){
            return JsonMsg.success().addInfo("department",department);
        }
        return JsonMsg.fail();
    }

    /**
     * 分页查询，返回指定页数对应的数据
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getDeptList",method = RequestMethod.GET)
    @ApiOperation(value = "分页查询，返回指定页数对应的数据",httpMethod = "GET",notes = "分页查询，返回指定页数对应的数据")
    public ModelAndView getDeptList(@ApiParam(name = "pageNo", value = "页码",required = true)@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("departmentPage");
        int limit = 5;
        int totalItems = departmentService.countDept();
        int temp = totalItems/limit;
        int totalPages = (totalItems%limit == 0)?temp:temp+1;
        int offset = (pageNo-1)*limit;
        List<Department> departments = departmentService.findDeptsByLimitAndOffset(offset, limit);
        mv.addObject("departments",departments)
                .addObject("totalItems",totalItems)
                .addObject("totalPages",totalPages)
                .addObject("curPageNo",pageNo);
        return mv;
    }

    /**
     * 查询所有部门名称
     * @return
     */
    @RequestMapping(value = "/getDeptName", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有部门名称",httpMethod = "GET",notes = "查询所有部门名称")
    public JsonMsg getDeptName(){
        List<Department> departmentList = departmentService.findAllDept();
        if (departmentList != null){
            return JsonMsg.success().addInfo("departmentList",departmentList);
        }
        return JsonMsg.fail();
    }


}
