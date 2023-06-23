package com.example.entity.vo;

import lombok.Data;

@Data
public class AssignRoleVO {
    private Integer uid; //用户id
    private Integer[] rid; //要给用户分配的角色id集合
}
