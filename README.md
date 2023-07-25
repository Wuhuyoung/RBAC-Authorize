## RBAC 权限系统

只是最简单的权限系统 + 登录功能，项目来自课程大作业

### 项目介绍

使用 SpringAop 做权限校验

使用 Layuimini 作为后台管理模板

### 技术选型

JDK 1.8、Mysql 8、SpringBoot、layuimini

### 使用说明

管理员账号:han，密码:123

用户管理没有写业务逻辑，因此添加用户需要在表内添加。



权限列表：

![权限列表](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202306040031284.jpg)

授权：

![授权](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202306040031172.jpg)

分配角色：

![分配角色](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202306040031105.jpg)



### 使用说明

1. 从 sql 文件夹中导入数据库表
2. 启动项目
3. 访问：http://localhost:8080/page/login.html
4. 账号：han，密码：123
