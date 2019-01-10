# testSpringbootMutipartDatasource
SpringBoot配置多数据源druidDataSource完整解决方案

本项目是搭建的一个dubbo项目，采用的是分布式的部署，其他模块就不贴上来了

如果需要具体探讨：
  请加QQ：9950488

配置该项目还是踩了很多坑，一步一个坑上来的

配置步骤如下：


1.数据源参数配置，username/password/url请改为自己的两个服务器的数据，此处偷了个懒，直接在spring.datasource后面加了个三级菜单来做第二个数据源数据"spring.datasource.secondary"

spring:
  datasource:
    #第一个数据源
    username: root
    password: mysql
    #此处配置第一个数据源地址
    url: jdbc:mysql://localhost2:3306/tianyou?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000 
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    #第二个数据源
    secondary:
      username: root
      password: root、
      #此处配置第二个数据源
      url: jdbc:mysql://localhost:3306/tianyou?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true
      driver-class-name: com.mysql.jdbc.Driver
      type: com.alibaba.druid.pool.DruidDataSource
      initialSize: 5
      minIdle: 5
      maxActive: 20
      maxWait: 60000 
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      poolPreparedStatements: true
    

    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true  
  redis:
    database: 0
    #host: 120.26.216.73
    host: 127.0.0.1
    port: 6379
    password: 
    timeout: 60000
    jedis:
      pool:
        max-active: 200
        max-idle: 8
        max-wait: -1
        min-idle: 0

dubbo:
  application:
    name: personnel-management
  registry:
    address: zookeeper://127.0.0.1:2181
  protocol:
    name: dubbo
    port: 20885
  provider:
    filter: validation
    timeout: 6000
    delay: -1
    retires: 0
server:
  port: 8081
  
mapper:
  mappers: com.cyjz.dao.base.IBaseMapper
  not-empty: false
  identity: MYSQL
