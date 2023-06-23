package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Tree;
import com.example.entity.vo.AssignRoleVO;

import java.util.List;
import java.util.Map;


public interface RoleService extends IService<Role> {

    List<Tree> tree();

    List<Map<String, Object>> selectAllRole();

    void assignRole(AssignRoleVO assignRoleVO);

    Page<Role> listAndPage(Long page, Long limit, String name);
}
