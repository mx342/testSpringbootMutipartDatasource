# testSpringbootMutipartDatasource
SpringBoot配置多数据源druidDataSource完整解决方案

本项目是搭建的一个dubbo项目，采用的是分布式的部署，其他模块就不贴上来了

如果需要具体探讨：
  请加QQ：9950488

配置该项目还是踩了很多坑，一步一个坑上来的

配置步骤如下：


1.数据源参数配置，username/password/url请改为自己的两个服务器的数据，此处偷了个懒，直接在spring.datasource后面加了个三级菜单来做第二个数据源数据"spring.datasource.secondary"
参考项目src/main/resources/config/application.yml

2.配置两个独立的DataSource: DataSourceOneConfig,DataSourceTwoConfig  参考项目: src/main/java/com/cyjz/config下面的两个项目

3.解释：注意MapperScan（非常重要）
	（1）这里的配置了basePackages,这个参数表示扫描哪个包里面的dao层作为本数据源的dao
	（2）sqlSessionTemplateRef,这个参数是引用下面的bean
	（3）第一个DataSourceOneConfig这里的第一个bean这里加上@Primary注解，表示是主数据源也就是不需要带name可以直接访问到的
	（4）在配置sqlSessionFactoryOne的时候，一定！一定！一定！要记得配置configLocation与mapperLocations(踩过的坑啊，辛酸泪啊！)
	（5）configLocation配置的是mybatis的配置，此处配置了一个驼峰自动映射代码贴出来(如果想要可以加我qq，我想应该会吧，不会的话qq聊)
	（6）mapperLocations这个是配置的本数据源的mybatis的xml文件的目录位置，两个数据源分别不同用于区分两个项目的mapper.xml文件
	
4.配置算是完了，只是可能还有细节没有描述清楚，具体不清楚的联系我吧
