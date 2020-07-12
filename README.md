# activiti 7.0 安装部署

[TOC]

## 创建activiti所需的数据库表

本地安装了MySQL 8.0.20版本，需要执行以下操作：
* 更新maven依赖到8.0.18版本
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.18</version>
</dependency>
```
* 将`activiti.cfg.xml`连接配置改为以下方式，注意`driverClassName`和`url`，username和password根据实际情况配置
```xml
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/activiti?serverTimezone=UTC" />
    <property name="username" value="root" />
    <property name="password" value="aaaaaa" />
    <property name="maxActive" value="3" />
    <property name="maxIdle" value="1" />
</bean>
```

执行`testGenTable()`