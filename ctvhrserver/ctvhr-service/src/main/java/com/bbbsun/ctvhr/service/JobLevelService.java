package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.JobLevelMapper;
import com.bbbsun.ctvhr.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobLevelService {
    @Autowired
    private JobLevelMapper jobLevelMapper;
    public List<JobLevel> getAllJobLevel() {
        return jobLevelMapper.getAllJobLevel();
    }

    public int addJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public int updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public int deteleJobLevel(int id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public int deteleMany(int[] ids) {
        return jobLevelMapper.deteleMany(ids);
    }
}
