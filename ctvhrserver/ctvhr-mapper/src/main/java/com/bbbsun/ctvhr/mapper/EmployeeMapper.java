package com.bbbsun.ctvhr.mapper;

import com.bbbsun.ctvhr.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    int getEmpCountByDeptId(Integer id);

    List<Employee> getEmployeeByPage(Integer page, Integer size, String keyword);

    Long getTotal(@Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Integer maxWorkID();

    Integer addEmployee(List<Employee> list);

    Employee getEmployeeById(Integer id);

    List<Employee> getAllEmployeesWithSalaryByPage(Integer page, Integer size);

    Integer updateEmployeeSalaryById(Integer eid, Integer sid);
}