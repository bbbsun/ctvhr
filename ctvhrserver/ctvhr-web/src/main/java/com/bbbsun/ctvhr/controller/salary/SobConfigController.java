package com.bbbsun.ctvhr.controller.salary;

import com.bbbsun.ctvhr.service.SalaryService;
import com.bbbsun.ctvhr.model.RespBean;
import com.bbbsun.ctvhr.model.RespPageBean;
import com.bbbsun.ctvhr.model.Salary;
import com.bbbsun.ctvhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工账套设置
 * @author Administrator
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SalaryService salaryService;

    /**
     * 分页获取所有员工账套
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespPageBean getAllEmployeesWithSalaryByPage(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getAllEmployeesWithSalaryByPage(page,size);
    }

    /**
     * 获取所有工资账套
     * @return List<Salary>
     */
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    /**
     * 更新员工工资账套
     * 有可能之前未设置
     * @param eid employee.id
     * @param sid salary.id
     * @return
     */
    @PutMapping("/")
    public RespBean updateEmployeeSalaryById(Integer eid, Integer sid) {
        Integer result = employeeService.updateEmployeeSalaryById(eid, sid);
        if (result == 1 || result == 2) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
