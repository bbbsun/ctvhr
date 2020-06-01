package com.bbbsun.ctvhr.mapper;

import com.bbbsun.ctvhr.model.Employeeec;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    int insertSelective(Employeeec record);

    Employeeec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);
}