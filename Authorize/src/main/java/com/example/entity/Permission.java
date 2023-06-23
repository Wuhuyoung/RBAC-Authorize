package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pId;

    private String path;

    private String href;

    private String icon;

    private String name;

    // -1:目录 0:菜单 1:按钮
    private Integer isMenu;

    private String target;

    @TableField(exist = false)
    private List<Permission> children;

    private Integer state;
    @TableLogic
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModifeld;


}
