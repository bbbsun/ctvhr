package com.bbbsun.ctvhr.controller.config;

import com.bbbsun.ctvhr.model.Menu;
import com.bbbsun.ctvhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统配置controller
 * @author Administrator
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    private MenuService menuService;

    /**
     * 通过当前登录的hr.id获取其所能访问的资源
     * @return List<menu>
     */
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId(){
        return menuService.getMenusByHrId();
    }

}
