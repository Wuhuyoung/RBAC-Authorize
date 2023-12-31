package com.example;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.example.entity.Permission;
import com.example.entity.User;
import com.example.service.PermissionService;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizeApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	@Test
	void testGenerator() {
		// 1、创建代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 2、全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		// 指定生成的.java文件路径，最好使用绝对路径
		gc.setOutputDir("D:\\program\\project\\homework\\权限系统RBAC\\authorize\\Authorize\\src\\main\\java");

		gc.setAuthor("yyh");
		gc.setOpen(false); //生成后是否打开资源管理器
		gc.setFileOverride(false); //重新生成时文件是否覆盖

		//UserServie
		gc.setServiceName("%sService");	//去掉Service接口的首字母I

		gc.setIdType(IdType.AUTO); //主键策略
		gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
		//gc.setSwagger2(true);//开启Swagger2模式

		mpg.setGlobalConfig(gc);

		// 3、数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/authorize");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("491001");
		dsc.setDbType(DbType.MYSQL);
		mpg.setDataSource(dsc);

		// 4、包配置
		PackageConfig pc = new PackageConfig();
		// 模块名指定的是controller,entity,service,mapper的上级目录
		pc.setModuleName("example"); //模块名
		//包  com.atguigu.eduservice
		pc.setParent("com");
		//包  com.atguigu.eduservice.controller
		pc.setController("controller");
		pc.setEntity("entity");
		pc.setService("service");
		pc.setMapper("mapper");
		mpg.setPackageInfo(pc);

		// 5、策略配置
		StrategyConfig strategy = new StrategyConfig();

		strategy.setInclude("role", "user_role", "role_permission");// 对应表名
		// NamingStrategy.underline_to_camel = 下划线转驼峰命名
		strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
		strategy.setTablePrefix(); //生成实体时去掉表前缀
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
		strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

		strategy.setRestControllerStyle(true); //restful api风格控制器
		strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

		mpg.setStrategy(strategy);


		// 6、执行
		mpg.execute();

	}

	@Test
	void testMp() {
		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

		//分页查询
		Page<User> page = userService.page(new Page<User>(1, 10));
		System.out.println(page.getRecords());
		//保存
		User user = new User();
		user.setId(1);
		user.setName("han");
		user.setPassword("123");
//		userService.save(user);

//		userService.updateById(user);
//		userService.removeById(1);

	}
	@Test
	void test() {
//		Permission permission = permissionService.getById(121);
//		System.out.println(permission);
	}
}
