package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.RoleMapper;
import com.bbbsun.ctvhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public int addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insert(role);
    }

    public int deleteRole(int rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
