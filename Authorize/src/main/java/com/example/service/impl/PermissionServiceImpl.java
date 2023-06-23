package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.authority.AuthorityUtils;
import com.example.entity.*;
import com.example.mapper.PermissionMapper;
import com.example.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.RolePermissionService;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 初始化菜单
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> toTree(HttpServletRequest request) {
        //创建返回结果Map
        HashMap<String, Object> data = new HashMap<>();
        ArrayList<Menu> menus = new ArrayList<>();
        ArrayList<Menu> parentMenu = new ArrayList<>();
        //封装权限集合
        Set<String> set = new HashSet<>();

        //根据当前用户获取菜单
        //从request中获得token，从token中获取用户id
        String uId = JwtUtils.getMemberIdByJwtToken(request);

        //根据用户id查询对应的角色id
        List<Integer> roleIds = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUId, uId))
                .stream().map(UserRole::getRId).collect(Collectors.toList());

        //根据角色id查询对应的权限id
        List<Integer> permissionIds = null;
        if(!ObjectUtils.isEmpty(roleIds)) {
            permissionIds = rolePermissionService.list(
                    new LambdaQueryWrapper<RolePermission>().in(RolePermission::getRId, roleIds))
                    .stream().map(RolePermission::getPId).collect(Collectors.toList());
        }

        //查出所有权限 -> 转成对应的菜单对象 -> 存放入menus中
        List<Permission> list = list(new QueryWrapper<Permission>()
                .in(!ObjectUtils.isEmpty(permissionIds), "id", permissionIds)); //根据id查权限
        list.stream().forEach(permission -> {
            Menu menu = new Menu();
            BeanUtil.copyProperties(permission, menu, false);
            menu.setTitle(permission.getName());
            menus.add(menu);
        });

        //list转树形结构
        //1、先找到根节点
        for (Menu menu : menus) {
            if(menu.getPId() == 0) {
                menu.setChild(new ArrayList<Menu>());
                parentMenu.add(menu);
            }
        }
        //2、根据根节点找到子节点(深度优先搜索)
        for (Menu menu : parentMenu) {
            menu.getChild().add(findChild(menu, menus, set));
        }

        //保存用户权限
        AuthorityUtils.setAuthority(uId, set);

        MenuKey menuKey1 = new MenuKey();
        MenuKey menuKey2 = new MenuKey();
        menuKey1.setTitle("首页");
        menuKey1.setHref("page/welcome.html?t=1");
        menuKey2.setTitle("后台管理");
        menuKey2.setImage("images/logo.png");
        data.put("menuInfo", parentMenu);
        data.put("homeInfo", menuKey1);
        data.put("logoInfo", menuKey2);
        return data;
    }

    @Override
    public List<Permission> treeSelect() {
        //返回结果
        ArrayList<Permission> data = new ArrayList<>();

        //查出所有非按钮的权限
        List<Permission> permissions = list(new LambdaQueryWrapper<Permission>().ne(Permission::getIsMenu, 1));

        //找到根节点
        for (Permission permission : permissions) {
            if(permission.getPId() == 0) {
                permission.setChildren(new ArrayList<Permission>());
                data.add(permission);
            }
        }

        //根据根节点找到子节点
        for (Permission permission : data) {
            permission.getChildren().add(findTreeSelectChildren(permission, permissions));
        }

        return data;
    }

    @Override
    @Transactional
    public void removeMenu(Integer id) {
        //封装id集合
        List<Integer> ids = new ArrayList<>();
        findPermissionId(id, ids);
        ids.add(id);
        //删除权限
        removeByIds(ids);
        //删除角色权限表中具有该权限的记录
        rolePermissionService.remove(new LambdaQueryWrapper<RolePermission>()
                .in(RolePermission::getPId, ids));
    }

    //寻找该permission的所有子菜单的id,放入ids中
    private void findPermissionId(Integer id, List<Integer> ids) {
        List<Permission> sonPermission = list(new QueryWrapper<Permission>().eq("p_id", id));
        sonPermission.stream().forEach(permission -> {
            ids.add(permission.getId());
            //再寻找该菜单的子菜单,放入ids
            findPermissionId(permission.getId(), ids);
        });
    }

    private Permission findTreeSelectChildren(Permission permission, List<Permission> permissions) {
        permission.setChildren(new ArrayList<Permission>());
        for (Permission p : permissions) {

            if(p.getPId() == permission.getId()) {
                permission.getChildren().add(findTreeSelectChildren(p, permissions));
            }
        }
        return permission;
    }

    /**
     * 寻找 menu的子节点，加入menu的child中
     * @param menu
     * @param menus
     * @return
     */
    private Menu findChild(Menu menu, ArrayList<Menu> menus, Set<String> set) {
        menu.setChild(new ArrayList<Menu>());
        for (Menu m : menus) {
            if(!ObjectUtils.isEmpty(m.getPath())) {
                set.add(m.getPath());
            }
            if(m.getIsMenu() != 1) {
                if (m.getPId() == menu.getId()) {
                    //递归寻找m的子节点
                    menu.getChild().add(findChild(m, menus, set));
                }
            }
        }
        return menu;
    }
}
