package com.example.service;

import com.example.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface PermissionService extends IService<Permission> {

    Map<String, Object> toTree(HttpServletRequest request);

    List<Permission> treeSelect();

    void removeMenu(Integer id);
}
