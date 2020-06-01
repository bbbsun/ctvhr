package com.bbbsun.ctvhr.mapper;

import com.bbbsun.ctvhr.model.Politicsstatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliticsstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    List<Politicsstatus> getAllpoliticsstatus();
}