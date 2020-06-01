package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.DepartmentMapper;
import com.bbbsun.ctvhr.mapper.EmployeeMapper;
import com.bbbsun.ctvhr.mapper.EmployeeecMapper;
import com.bbbsun.ctvhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Department> getAllDepartments(Integer parentId) {

        return departmentMapper.getAllDepartmentsByParentId(parentId);
    }

    @Transactional
    public void addDeps(Department dep) {
        dep.setParent(false);
        departmentMapper.insert(dep);
        Integer id = dep.getId();
        Department parentDep = departmentMapper.selectByPrimaryKey(dep.getParentId());
        dep.setDepPath(parentDep.getDepPath() + "." + id);
        departmentMapper.updateByPrimaryKeySelective(dep);
        parentDep.setParent(true);
        departmentMapper.updateByPrimaryKeySelective(parentDep);
    }

    @Transactional
    public void deleteDeptById(Integer id) {
        Department dept = departmentMapper.selectByPrimaryKey(id);
        //有子部门不能删
        if (dept.getParent()){
            throw new RuntimeException("该部门下有子部门，不能删除");
        }
        //部门下有员工，不能删
        if (employeeMapper.getEmpCountByDeptId(id)!=0){
            throw new RuntimeException("该部门下有员工，不能删除");
        }
        //删除
        departmentMapper.deleteByPrimaryKey(id);
        //父部门的isParent字段要更新
        Department parDept = departmentMapper.selectByPrimaryKey(dept.getParentId());
        if (departmentMapper.getDeptCountByPid(parDept.getId())==0){
            parDept.setParent(false);
            departmentMapper.updateByPrimaryKeySelective(parDept);
        }
    }

    public List<Department> getAllDepartmentsWithoutChildren() {
        return departmentMapper.getAllDepartmentsWithoutChildren();
    }
}
