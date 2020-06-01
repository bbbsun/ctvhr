package com.bbbsun.ctvhr.mapper;

import com.bbbsun.ctvhr.model.MenuRole;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteByRid(Integer rid);

    Integer insertRecord(Integer rid, Integer[] mids);
}