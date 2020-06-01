package com.bbbsun.ctvhr.controller.emp;

import com.bbbsun.ctvhr.model.*;
import com.bbbsun.ctvhr.service.*;
import com.bbbsun.ctvhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 基本资料controller
 * @author Administrator
 */
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private NationService nationService;
    @Autowired
    private PoliticsstatusService politicsstatusService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private JobLevelService jobLevelService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 导出员工资料excel表格
     * @return ResponseEntity
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null, null).getData();
        return POIUtils.employee2Excel(list);
    }

    /**
     * 导入员工资料
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file,nationService.getAllNations(),
                politicsstatusService.getAllpoliticsstatus(),departmentService.getAllDepartmentsWithoutChildren(),
               positionService.getAllPosition(),jobLevelService.getAllJobLevel() );
        if (employeeService.addEmployee(list)==list.size()){
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }

    /**
     * 分页获取员工列表
     * @param page
     * @param size
     * @param keyword
     * @return RespPageBean
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        return employeeService.getEmployeeByPage(page, size, keyword);
    }

    /**
     * 添加员工
     * @param employee 员工
     * @return RespBean
     */
    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        if (employeeService.addEmployee(employee)==1){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    /**
     * 删除员工
     * @param id id
     * @return RespBean
     */
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if (employeeService.deleteEmp(id)==1){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    /**
     * 修改员工信息
     * @param employee 员工
     * @return RespBean
     */
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if (employeeService.updateEmp(employee)==1){
            return RespBean.ok("更新成功");
        }else{
            return RespBean.error("更新失败");
        }
    }

    /**
     * 获取所有部门信息
     * @return List<Department>
     */
    @GetMapping("/deps")
    public List<Department> getAllDeps() {
        return departmentService.getAllDepartments(-1);
    }

    /**
     * 获取所有民族
     * @return List<Nation>
     */
    @GetMapping("/nation")
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }

    /**
     * 获取所有政治面貌
     * @return List<Politicsstatus>
     */
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllpoliticsstatus(){
        return politicsstatusService.getAllpoliticsstatus();
    }

    /**
     * 获取所有职位
     * @return List<Position>
     */
    @GetMapping("/position")
    public List<Position> getAllPositions(){
        return positionService.getAllPosition();
    }

    /**
     * 获取所有职称
     * @return List<JobLevel>
     */
    @GetMapping("/joblevel")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevel();
    }

    /**
     * 员工添加时，获取最大工号
     * @return RespBean
     */
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        RespBean respBean = RespBean.build().setMsg("").setStatus(200)
                .setObj(String.format("%08d", employeeService.maxWorkID() + 1));
        return respBean;
    }
}
