package com.example.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.mapper.UserMapper;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yyh
 * @since 2022-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询用户，同时查询用户具有的角色的角色名
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @Override
    public Page<User> pageAndList(Long page, Long limit, String name) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.like(StrUtil.isNotBlank(name), User::getName, name);
        Page<User> userPage = page(new Page<>(page, limit), lqw);
        //查询该用户分配的角色
        userPage.getRecords().stream().forEach(user -> {
            //根据用户id查询角色id
            List<Integer> roleIds = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUId, user.getId()))
                    .stream().map(UserRole::getRId).collect(Collectors.toList());
            //根据角色id查询角色名
            if(!ObjectUtils.isEmpty(roleIds)) {
                List<String> roleNames = roleService.listByIds(roleIds).stream().map(Role::getName).collect(Collectors.toList());
                user.setRoleName(roleNames);
            }
        });

        return userPage;
    }
}
