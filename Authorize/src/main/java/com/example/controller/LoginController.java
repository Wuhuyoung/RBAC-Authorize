package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录校验
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R login(String name, String password) {
        //根据姓名查询用户
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        User user = userService.getOne(lqw.eq(User::getName, name));
        //用户为空，返回
        if(ObjectUtils.isEmpty(user)) {
            return R.error().message("账号错误");
        }
        //密码不一致，返回
        if(!user.getPassword().equals(password)) {
            return R.error().message("密码不一致");
        }
        //登录成功，生成token
        String token = JwtUtils.getJwtToken(String.valueOf(user.getId()), name);

        return R.ok().data("token", token).data("name", name);
    }
}
