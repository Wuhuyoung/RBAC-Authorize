package com.example.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyDateConfig implements MetaObjectHandler {

    // 创建时间的方法
    @Override
    public void insertFill(MetaObject metaObject) {
        // 自动填充创建时间： "createTime" 为实体类创建时间的属性
        // DateUtil.getDate()： 为创建的时间
        // metaObject: 不知道，照着填就对了
        this.setFieldValByName("gmtCreate", new Date(), metaObject);

        // 为实体类中的更新时间创建初始化时间
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }


    // 更新时间的方法
    @Override
    public void updateFill(MetaObject metaObject) {
        // 自动填充更新时间： "updataTime"为实体类更新时间的属性
        // 其他俩参数同上
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}

