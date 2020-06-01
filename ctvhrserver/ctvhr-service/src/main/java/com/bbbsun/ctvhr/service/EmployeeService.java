package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.EmployeeMapper;
import com.bbbsun.ctvhr.model.Employee;
import com.bbbsun.ctvhr.model.RespPageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EmployeeMapper employeeMapper;

    SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthDateFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size,keyword);
        Long total = employeeMapper.getTotal(null,null);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(data);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    public Integer addEmployee(List<Employee> list){
        return employeeMapper.addEmployee(list);
    }

    public Integer addEmployee(Employee employee) {
        Date endContract = employee.getEndContract();
        Date beginContract = employee.getBeginContract();
        double month = (Double.parseDouble(yearDateFormat.format(endContract)) - Double.parseDouble(monthDateFormat.format(beginContract))) * 12
                + ((Double.parseDouble(monthDateFormat.format(endContract))) - (Double.parseDouble(monthDateFormat.format(beginContract))));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        int result = employeeMapper.insertSelective(employee);
        if (result==1){
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            logger.info(emp.toString());
            rabbitTemplate.convertAndSend("bbbsun.mail.welcome",emp);
        }
        return result;
    }

    public Integer maxWorkID() {
        return employeeMapper.maxWorkID();
    }

    public Integer deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public RespPageBean getAllEmployeesWithSalaryByPage(Integer page, Integer size) {
        if (page!=null&&size!=null){
            page = (page-1)*size;
        }
        RespPageBean result = new RespPageBean();
        List<Employee> list = employeeMapper.getAllEmployeesWithSalaryByPage(page,size);
        result.setData(list);
        result.setTotal(employeeMapper.getTotal(null,null));
        return result;
    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }
}
