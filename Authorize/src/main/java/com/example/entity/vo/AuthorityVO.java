package com.example.entity.vo;

import lombok.Data;

@Data
public class AuthorityVO {
    private Integer rid; //角色id
    private Integer[] pid; //要给角色授权的权限id集合
}
