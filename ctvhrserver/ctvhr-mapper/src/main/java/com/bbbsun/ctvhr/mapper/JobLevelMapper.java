package com.bbbsun.ctvhr.mapper;

import com.bbbsun.ctvhr.model.JobLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    int deteleMany(@Param(("ids")) int[] ids);

    List<JobLevel> getAllJobLevel();
}