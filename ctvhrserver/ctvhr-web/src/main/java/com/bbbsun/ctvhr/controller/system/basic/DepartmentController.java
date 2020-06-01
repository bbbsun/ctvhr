package com.bbbsun.ctvhr.controller.system.basic;

import com.bbbsun.ctvhr.model.Department;
import com.bbbsun.ctvhr.model.RespBean;
import com.bbbsun.ctvhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门设置controller
 * @author
 */
@RestController
@RequestMapping("/system/basic/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments(-1);
    }
    @PostMapping("/")
    public RespBean addDeps(@RequestBody Department dep){
        try {
            departmentService.addDeps(dep);
        }catch (Exception e){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功",dep);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDeptById(@PathVariable Integer id){
        try {
            departmentService.deleteDeptById(id);
        }catch (Exception e){
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("删除成功");
    }
}
