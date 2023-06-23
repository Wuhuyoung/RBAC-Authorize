package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.Authority;
import com.example.entity.Role;
import com.example.entity.RolePermission;
import com.example.entity.Tree;
import com.example.entity.UserRole;
import com.example.entity.vo.AssignRoleVO;
import com.example.entity.vo.AuthorityVO;
import com.example.service.RolePermissionService;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 分页查询列表
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @GetMapping("/list")
    @Authority("role:list")
    public R list(Long page, Long limit, @RequestParam(required = false) String name) {
        Page<Role> rolePage = roleService.listAndPage(page, limit, name);

        return R.ok().data("data", rolePage.getRecords()).count(rolePage.getTotal());
    }

    /**
     * 新增角色
     * @return
     */
    @PostMapping("/add")
    @Authority("role:add")
    public R add(@RequestBody Role role) {
        roleService.save(role);
        return R.ok();
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PutMapping("/update")
    @Authority("role:update")
    public R update(@RequestBody Role role) {
        roleService.updateById(role);
        return R.ok();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    @Authority("role:delete")
    public R delete(@PathVariable Integer id) {
        roleService.removeById(id);
        //角色权限表中该角色的记录也要删除
        rolePermissionService.remove(new LambdaQueryWrapper<RolePermission>()
                .eq(RolePermission::getRId, id));
        return R.ok();
    }

    /**
     * 查询角色，树形表示
     * @return
     */
    @GetMapping("/treeList")
    public List<Tree> treeList() {
        List<Tree> data = roleService.tree();
        return data;
    }

    /**
     * 给角色授权
     * @param authorityVO
     * @return
     */
    @PostMapping("/authority")
    @Transactional
    @Authority("role:authority")
    public R authority(@RequestBody AuthorityVO authorityVO) {
        //分配权限之前先把该角色具有的权限删除，避免重复授权
        rolePermissionService.remove(new LambdaQueryWrapper<RolePermission>()
                .eq(RolePermission::getRId, authorityVO.getRid()));

        List<RolePermission> list = new ArrayList<>();
        Integer rid = authorityVO.getRid();

        for (Integer pId : authorityVO.getPid()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRId(rid);
            rolePermission.setPId(pId);
            list.add(rolePermission);
        }
        rolePermissionService.saveBatch(list);
        return R.ok();
    }

    /**
     * 查询角色的权限pid集合
     * @param rid
     * @return
     */
    @GetMapping("/getPermission/{rid}")
    @Authority("role:getPermission")
    public List<Integer> getPermission(@PathVariable Integer rid) {
        List<Integer> list = rolePermissionService.list(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRId, rid))
                .stream().map(RolePermission::getPId).collect(Collectors.toList());
        return list;
    }

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/initRole")
    @Authority("role:initRole")
    public List<Map<String, Object>> initRole() {
        List<Map<String, Object>> list = roleService.selectAllRole();
        return list;
    }

    /**
     * 给用户分配角色
     * @param assignRoleVO
     * @return
     */
    @PostMapping("/assignRole")
    @Authority("user:assignRole")
    public R assignRole(@RequestBody AssignRoleVO assignRoleVO) {
        roleService.assignRole(assignRoleVO);

        return R.ok();
    }

    /**
     * 根据 uid查询该用户已分配的角色
     * @param uid
     * @return
     */
    @GetMapping("/getRole/{uid}")
    @Authority("role:getRole")
    public List<Integer> getRole(@PathVariable Integer uid) {
        List<Integer> list = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUId, uid))
                .stream().map(UserRole::getRId).collect(Collectors.toList());
        return list;
    }
}

