package com.example.service.impl;

import com.example.entity.UserRole;
import com.example.mapper.UserRoleMapper;
import com.example.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
