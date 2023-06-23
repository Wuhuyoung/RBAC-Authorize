package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yyh
 * @since 2022-12-22
 */
public interface UserService extends IService<User> {

    Page<User> pageAndList(Long page, Long limit, String name);

}
