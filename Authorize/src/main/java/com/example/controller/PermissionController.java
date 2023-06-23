package com.example.controller;


import com.example.authority.Authority;
import com.example.entity.Permission;
import com.example.service.PermissionService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/treeSelect")
    @Authority("permission:treeSelect")
    public List<Permission> treeSelect() {
        List<Permission> data = permissionService.treeSelect();
        return data;
    }

    @GetMapping("/list")
    @Authority("permission:list")
    public List<Permission> list() {
        List<Permission> list = permissionService.list();
        return list;
    }

    /**
     * 初始化菜单
     * @return
     */
    @GetMapping("/initMenu")
    public Map<String, Object> initMenu(HttpServletRequest request) {
        Map<String, Object> data = permissionService.toTree(request);
        return data;
    }

    @PostMapping("/add")
    @Authority("permission:add")
    public R addPermission(@RequestBody Permission permission) {
        permission.setIcon("fa " + permission.getIcon());
        permissionService.save(permission);
        return R.ok();
    }

    @PutMapping("/update")
    @Authority("permission:update")
    public R updatePermission(@RequestBody Permission permission) {
        permission.setIcon("fa " + permission.getIcon());
        permissionService.updateById(permission);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    @Authority("permission:delete")
    public R delete(@PathVariable Integer id) {
        permissionService.removeMenu(id);
        return R.ok().message("删除成功");
    }

}

