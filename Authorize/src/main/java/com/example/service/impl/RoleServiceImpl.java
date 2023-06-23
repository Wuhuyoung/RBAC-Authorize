package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.entity.Tree;
import com.example.entity.UserRole;
import com.example.entity.vo.AssignRoleVO;
import com.example.mapper.RoleMapper;
import com.example.service.PermissionService;
import com.example.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Tree> tree() {
        List<Tree> data = new ArrayList<>();
        List<Tree> trees = new ArrayList<>();
        //获取所有权限，转成Tree结构放入trees中
        permissionService.list().stream().forEach(permission -> {
            Tree tree = new Tree();
            BeanUtil.copyProperties(permission, tree);
            tree.setTitle(permission.getName());
            tree.setSpread(true);
            trees.add(tree);
        });

        //找到根节点
        for (Tree tree : trees) {
            if (tree.getPId() == 0) {
                tree.setChildren(new ArrayList<Tree>());
                data.add(tree); //将根节点放入data中
            }
        }
        //找到根节点的子节点
        for (Tree datum : data) {
            datum.getChildren().add(findChildren(datum, trees));
        }

        return data;
    }

    @Override
    public List<Map<String, Object>> selectAllRole() {
        //查询所有角色
        List<Map<String, Object>> list = list().stream().map(role -> {
            Map<String, Object> map = new HashMap<>();
            map.put("value", role.getId());
            map.put("title", role.getName());
            return map;
        }).collect(Collectors.toList());

        return list;
    }

    /**
     * 给用户分配角色
     * @param assignRoleVO
     */
    @Override
    @Transactional
    public void assignRole(AssignRoleVO assignRoleVO) {
        //为用户分配角色之前要先删除该用户的角色，避免重复分配
        Integer uid = assignRoleVO.getUid();
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUId, uid));

        List<UserRole> userRoles = new ArrayList<>();
        for (Integer rid : assignRoleVO.getRid()) {
            UserRole userRole = new UserRole();
            userRole.setRId(rid);
            userRole.setUId(uid);
            userRoles.add(userRole);
        }
        //批量保存
        userRoleService.saveBatch(userRoles);
    }

    //分页查询角色
    @Override
    public Page<Role> listAndPage(Long page, Long limit, String name) {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();

        lqw.like(StrUtil.isNotEmpty(name), Role::getName, name);
        Page<Role> rolePage = page(new Page<Role>(page, limit), lqw);
        return rolePage;
    }

    //找到datum节点的子节点并返回
    private Tree findChildren(Tree datum, List<Tree> trees) {
        datum.setChildren(new ArrayList<>());
        for (Tree tree : trees) {
            if(tree.getPId() == datum.getId()) {
                datum.getChildren().add(findChildren(tree, trees));
            }
        }
        return datum;
    }
}
