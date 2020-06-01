package com.bbbsun.ctvhr.controller.salary;

import com.bbbsun.ctvhr.service.SalaryService;
import com.bbbsun.ctvhr.model.RespBean;
import com.bbbsun.ctvhr.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工资账套controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    /**
     * 获取所有工资账套
     * @return List<Salary>
     */
    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    /**
     * 添加工资账套
     * @param salary 工资账套
     * @return RespBean
     */
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if (salaryService.addSalary(salary)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 删除工资账套
     * @param id id
     * @return RespBean
     */
    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        if (salaryService.deleteSalaryById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 修改工资账套
     * @param salary 工资账套
     * @return RespBean
     */
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        if (salaryService.updateSalary(salary)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
