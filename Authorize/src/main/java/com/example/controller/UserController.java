package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.Authority;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询用户，同时查询用户具有的角色的角色名
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @GetMapping("/list")
    @Authority("user:list")
    public R list(Long page, Long limit, @RequestParam(required = false) String name) {
        Page<User> userPage = userService.pageAndList(page, limit, name);

        return R.ok().data("data", userPage.getRecords()).count(userPage.getTotal());
    }

}

